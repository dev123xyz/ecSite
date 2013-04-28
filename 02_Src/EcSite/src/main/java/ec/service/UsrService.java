package ec.service;

import java.util.List;

import org.seasar.extension.jdbc.AutoSelect;
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
public class UsrService extends AbService<Usr> {

    /**
     * クラス名称
     */
    private static final String CLASS = UsrService.class.getSimpleName();

    /**
     * 全て選択する.
     * 
     * @return エンティティーリスト
     */
    public List<Usr> selectAll( String pOrderBy, String pOrderAscDesc ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".selectAll : ソート列=" + pOrderBy
                    + " ソート順=" + pOrderAscDesc );
        }

        AutoSelect<Usr> as = jdbcManager.from( Usr.class ).orderBy(
                Operations.asc( "USR_ID" ) );

        // TODO
        // ID取得　→　10建のみ取得の流れ
        // ページング

        try {

            List<Usr> entityList = as.getResultList();
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

        AutoSelect<Usr> as = jdbcManager.from( Usr.class )
                .leftOuterJoin( "ordersList" )
                .where( new SimpleWhere().eq( "usrId", usrId ) );

        try {

            Usr entity = as.getSingleResult();
            return entity;

        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    // SELECT *
    // FROM USR
    // LEFT OUTER JOIN ORDERS
    // ON USR.USR_ID = ORDERS.ORDER_USR_ID
    // LEFT OUTER JOIN ORDERS_DTL
    // ON ORDERS.ORDER_ID = ORDERS_DTL.ORDER_ID
    // LEFT OUTER JOIN GOODS
    // ON ORDERS_DTL.ORDER_GOODS_ID = GOODS.GOODS_ID
}