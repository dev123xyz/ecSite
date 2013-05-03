package ec.cmn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.seasar.framework.beans.util.Beans;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>共通ユーティリティ</DD>
 * </DL>
 */
public class CmnUtil {

    /**
     * クラス名称
     */
    private static final String CLASS = CmnUtil.class.getSimpleName();

    /**
     * コピーリストを取得する.
     * 
     * @param pClass コピー先クラス
     * @param pObjList コピー元リスト
     * @return コピー先リスト
     */
    public static final < T, C > List< T > getCopyList( Class< T > pClass,
            List< C > pObjList ) {

        if ( pObjList.isEmpty() == true ) {
            return Collections.emptyList();
        }

        ArrayList< T > list = new ArrayList<>();

        for ( C obj : pObjList ) {

            T item = CmnUtil.getCopy( pClass, obj );

            list.add( item );
        }

        return list;
    }

    /**
     * コピーを取得する.
     * 
     * @param pClass コピー先クラス
     * @param pObj コピー元
     * @return コピー先
     */
    public static final < T, C > T getCopy( Class< T > pClass, C pObj ) {

        if ( pObj == null ) {
            return null;
        }

        T item = Beans.createAndCopy( pClass, pObj )
                .dateConverter( "yyyy-MM-dd hh:mm:ss" ).execute();

        return item;
    }

    /**
     * キーワードをあいまい検索用に作成する.
     * 
     * @param pKeyword キーワード
     * @param pKeywordAndOr キーワード条件AND/OR
     * @return キーワード検索SQL
     */
    public static final String makeFuzzyKeyword( String pKeyword,
            String pKeywordAndOr ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".makeFuzzyKeyword" );
        }

        List< String > keywordList = splitFuzzyKeyword( pKeyword );

        if ( keywordList == null ) {
            return null;
        }

        StringBuilder sqlSb = new StringBuilder();

        for ( String keyword : keywordList ) {
            sqlSb.append( escapeFuzzyKeyword( keyword ) );
            sqlSb.append( " " );
            sqlSb.append( pKeywordAndOr );
            sqlSb.append( " " );
        }

        // 最後のAND/OR文字列を削除
        sqlSb.delete( sqlSb.length() - ( pKeywordAndOr.length() + 1 ),
                sqlSb.length() );

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".makeFuzzyKeyword : あいまい検索SQL="
                    + sqlSb.toString() );
        }

        return sqlSb.toString();
    }

    /**
     * キーワードをあいまい検索用に変換する.
     * 
     * @param pKeyword キーワード
     * @return 分割後キーワード
     */
    private static final List< String > splitFuzzyKeyword( String pKeyword ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".splitFuzzyKeyword" );
        }

        if ( pKeyword == null || pKeyword.length() == 0 ) {
            return null;
        }

        // 全角スペースを半角スペースへ変換
        String keyword = pKeyword.replaceAll( "　", " " );

        keyword = keyword.trim();

        // スペースで分割
        List< String > keyList = Arrays.asList( keyword.split( "[ ]+" ) );

        return keyList;
    }

    /**
     * あいまい検索用にキーワードをエスケープする.
     * 
     * @param pKeyword
     * @return
     */
    private static final String escapeFuzzyKeyword( String pKeyword ) {

        // 特定の文字列を無効化する
        String keyword = pKeyword.replaceAll( "[<>&\"']", "" );

        StringBuilder keySb = new StringBuilder();

        // 各文字の頭に\を挿入する
        for ( char keyChar : keyword.toCharArray() ) {

            keySb.append( "\\" );
            keySb.append( keyChar );
        }

        return keySb.toString();
    }

    /**
     * 文字列表現
     */
    public String toString() {

        return CLASS;
    }
}
