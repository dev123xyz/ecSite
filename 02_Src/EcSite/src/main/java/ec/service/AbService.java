package ec.service;

import java.util.Date;
import java.util.List;

import org.seasar.extension.jdbc.service.S2AbstractService;

import ec.cmn.util.CmnUtil;
import ec.cmn.util.CmnUtilDebug;
import ec.cmn.util.EcConflictException;
import ec.cmn.util.IcCmnConst;
import ec.entity.AbEntity;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>基底サービス</DD>
 * </DL>
 */
public abstract class AbService< ENTITY extends AbEntity > extends
        S2AbstractService< ENTITY > {

    /**
     * クラス名称
     */
    private static final String CLASS = AbService.class.getSimpleName();

    /**
     * 全て選択する.
     * 
     * @param pOrderByCol ソート列
     * @param pOrderAscDesc ソート順
     * @param pKeyword キーワード
     * @param pKeywordAndOr キーワード条件AND/OR
     * @return エンティティリスト
     */
    public abstract List< ENTITY > selectAll( String pOrderBy,
            String pOrderAscDesc, String pKeyword, String pKeywordAndOr );

    /**
     * 全て選択する（詳細）.
     * 
     * @param pOrderByCol ソート列
     * @param pOrderAscDesc ソート順
     * @param pEntityList エンティティリスト
     * @return エンティティリスト
     */
    protected abstract List< ENTITY > selectAllForDetail( String pOrderBy,
            String pOrderAscDesc, List< ENTITY > pEntityList );

    /**
     * 追加する.
     * 
     * @param pEntity エンティティ
     * @return 操作数
     */
    public int insert( ENTITY pEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".insert" );
        }

        try {
            // 追加
            return jdbcManager.insert( pEntity ).execute();
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * 更新する.<BR>
     * 各サブクラスは本メソッドの実装処理の中で、updateChkConflictを呼び出すこと.
     * 
     * @param pEntity エンティティ
     * @return 操作数
     */
    public abstract int update( ENTITY pEntity );

    /**
     * 競合チェックの後、更新する.
     * 
     * @param pOldEntity 旧エンティティ
     * @param pNewEntity 新エンティティ
     * @return 操作数
     */
    public int updateChkConflict( ENTITY pOldEntity, ENTITY pNewEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".updateChkConflict" );
        }

        if ( pOldEntity.updDate.equals( pNewEntity.updDate ) == false ) {
            // 競合発生
            throw new EcConflictException();
        }

        // 更新ユーザID・日時
        pNewEntity.updUserId = IcCmnConst.LOGIN_USER_ID;
        pNewEntity.updDate = new Date();

        try {
            // 更新
            return jdbcManager.update( pNewEntity ).execute();
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * 削除する.
     * 
     * @param pEntity エンティティ
     * @return 操作数
     */
    public int delete( ENTITY pEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".delete" );
        }

        try {
            // 削除
            return jdbcManager.delete( pEntity ).execute();
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * あいまい化対象列<BR>
     * ※あいまい列にはOracle Text用のINDEXを付与した列を設定する.
     * 
     * @param pSqlSb SQL文
     * @param pFuzzyCol あいまい列
     * @param pKeyword キーワード
     * @param pKeywordAndOr キーワード条件AND/OR
     * @return キーワードリスト
     */
    protected void setWhere( StringBuilder pSqlSb, List< Object > pPrmList,
            String pFuzzyCol, String pKeyword, String pKeywordAndOr ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".setWhere" );
        }

        String fuzzyKeyword = CmnUtil
                .makeFuzzyKeyword( pKeyword, pKeywordAndOr );

        if ( fuzzyKeyword == null ) {
            return;
        }

        // WHERE句
        StringBuilder whereSb = new StringBuilder();
        whereSb.append( "  WHERE " );
        whereSb.append( "  CONTAINS( " );
        whereSb.append( pFuzzyCol );
        whereSb.append( ", ? ) > 0 " );

        // キーワード値
        pPrmList.add( fuzzyKeyword );

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".setWhere : あいまい検索WHERE句="
                    + whereSb );
            CmnUtilDebug.outputDebug( CLASS + ".setWhere : あいまい検索値="
                    + fuzzyKeyword );
        }

        pSqlSb.append( whereSb );

        return;
    }
}