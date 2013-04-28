package ec.cmn.util;

import java.util.ArrayList;
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
     * @param pClass クラス
     * @param pObjList コピー元リスト
     * @return コピー先リスト
     */
    public static <T, C> List<T> getCopyList( Class<T> pClass, List<C> pObjList ) {

        if ( pObjList.isEmpty() == true ) {
            return Collections.emptyList();
        }

        ArrayList<T> list = new ArrayList<>();

        for ( C obj : pObjList ) {

            T item = Beans.createAndCopy( pClass, obj )
                    .dateConverter( "yyyy-MM-dd hh:mm:ss" ).execute();

            list.add( item );
        }

        return list;
    }

    /**
     * コピーを取得する.
     * 
     * @param pClass クラス
     * @param pObj コピー元
     * @return コピー先
     */
    public static <T, C> T getCopy( Class<T> pClass, C pObj ) {

        T item = Beans.createAndCopy( pClass, pObj )
                .dateConverter( "yyyy-MM-dd hh:mm:ss" ).execute();

        return item;
    }

    /**
     * 文字列表現
     */
    public String toString() {

        return CLASS;
    }
}
