package ec.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.SqlSelect;
import org.seasar.extension.jdbc.operation.Operations;
import org.seasar.extension.jdbc.where.SimpleWhere;

import ec.cmn.util.CmnUtilDebug;
import ec.cmn.util.IcCmnConst;
import ec.entity.Usr;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>ユーザ情報サービス</DD>
 * </DL>
 */
public class UsrService extends AbService< Usr > {

    /**
     * クラス名称
     */
    private static final String CLASS = UsrService.class.getSimpleName();

    /**
     * ソート列マップ
     */
    private static final HashMap< String, String > ORDER_BY_COL_MAP = new HashMap< String, String >() {

        /**
         * シリアルバージョンUID
         */
        private static final long serialVersionUID = 1L;

        {
            this.put( "usrId", "USR_ID" );
        }
    };

    /**
     * 全て選択する.
     * 
     * @param pOrderByCol ソート列
     * @param pOrderAscDesc ソート順
     * @param pKeyword キーワード
     * @param pKeywordAndOr キーワード条件AND/OR
     * @return エンティティリスト
     */
    public List< Usr > selectAll( String pOrderByCol, String pOrderAscDesc,
            String pKeyword, String pKeywordAndOr ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".selectAll : ソート列="
                    + pOrderByCol + " ソート順=" + pOrderAscDesc + " キーワード="
                    + pKeyword + " AND/OR=" + pKeywordAndOr );
        }

        ArrayList< Object > prmList = new ArrayList< Object >();

        StringBuilder sql = new StringBuilder();
        sql.append( "SELECT USR_ID FROM USR " );

        // あいまい検索
        setWhere( sql, prmList, "USR.UPD_USER_ID", pKeyword, pKeywordAndOr );

        // ORDER BY句
        {
            sql.append( "  ORDER BY " );
            sql.append( ORDER_BY_COL_MAP.get( pOrderByCol ) );
            sql.append( " " );
            sql.append( pOrderAscDesc );
        }

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".selectAll : SQL="
                    + sql.toString() );
            CmnUtilDebug.outputDebug( CLASS + ".selectAll : PRM=" + prmList );
        }

        SqlSelect< Usr > ss = jdbcManager
                .selectBySql( Usr.class, sql.toString(),
                        prmList.toArray( new Object[0] ) )
                .limit( IcCmnConst.LIST_SHOW_NUM ).offset( 0 );

        List< Usr > tmpEntityList = null;

        try {
            tmpEntityList = ss.getResultList();
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }

        List< Usr > entityList = selectAllForDetail( pOrderByCol,
                pOrderAscDesc, tmpEntityList );
        return entityList;
    }

    /**
     * 全て選択する(詳細).
     * 
     * @param pOrderByCol ソート列
     * @param pOrderAscDesc ソート順
     * @param pUsrList 詳細取得対象エンティティ
     * @return エンティティリスト
     */
    protected List< Usr > selectAllForDetail( String pOrderByCol,
            String pOrderAscDesc, List< Usr > pUsrList ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".selectAllForDetail : ソート列="
                    + pOrderByCol + " ソート順=" + pOrderAscDesc
                    + " 詳細取得対象エンティティリスト=" + pUsrList );
        }

        if ( pUsrList == null || pUsrList.size() == 0 ) {
            return Collections.emptyList();
        }

        // 条件を作成
        SimpleWhere sw = new SimpleWhere();

        {
            ArrayList< String > list = new ArrayList<>();

            for ( Usr usr : pUsrList ) {
                list.add( usr.usrId );
            }

            sw.in( "usrId", list );
        }

        AutoSelect< Usr > as = jdbcManager.from( Usr.class ).where( sw )
                .orderBy( pOrderByCol + " " + pOrderAscDesc );

        try {

            List< Usr > entityList = as.getResultList();
            return entityList;

        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * 単一を選択する.
     * 
     * @return エンティティーリスト
     */
    public Usr selectById( String usrId ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".selectById : ID=" + usrId );
        }

        AutoSelect< Usr > as = jdbcManager
                .from( Usr.class )
                .leftOuterJoin( "ordersList" )
                .leftOuterJoin( "ordersList.ordersDtlList" )
                .leftOuterJoin( "ordersList.ordersDtlList.goods" )
                .where( new SimpleWhere().eq( "usrId", usrId ) )
                .orderBy( Operations.desc( "ordersList.orderId" ),
                        Operations.asc( "ordersList.ordersDtlList.orderDtlNo" ) );

        try {

            Usr entity = as.getSingleResult();
            return entity;

        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * 追加する.
     * 
     * @param pEntity エンティティ
     * @return 操作数
     */
    public int insert( Usr pEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".insert" );
        }

        return super.insert( pEntity );
    }

    /**
     * 更新する.
     * 
     * @param pEntity エンティティ
     * @return 操作数
     */
    public int update( Usr pEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".update : ユーザID="
                    + ( pEntity == null ? "null" : pEntity.usrId ) );
        }

        Usr oldEntity = selectById( pEntity.usrId );

        return super.updateChkConflict( oldEntity, pEntity );
    }

    /**
     * 削除する.
     * 
     * @param pEntity エンティティ
     * @return 操作数
     */
    public int delete( Usr pEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".delete : ユーザID="
                    + ( pEntity == null ? "null" : pEntity.usrId ) );
        }

        return super.delete( pEntity );
    }
}