package ec.service;

import java.util.Date;
import java.util.List;

import org.seasar.extension.jdbc.service.S2AbstractService;

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
public abstract class AbService<ENTITY extends AbEntity> extends
        S2AbstractService<ENTITY> {

    /**
     * クラス名称
     */
    private static final String CLASS = AbService.class.getSimpleName();

    /**
     * 全て選択する.
     * 
     * @param pOrderBy ソート列
     * @param pOrderAscDesc ソート順(ASC/DESC)
     * @return エンティティーリスト
     */
    public abstract List<ENTITY> selectAll( String pOrderBy,
            String pOrderAscDesc );

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
            return super.insert( pEntity );
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * 更新する.
     * 
     * @param pOldEntity 旧エンティティ
     * @param pNewEntity 新エンティティ
     * @return 操作数
     */
    public int update( ENTITY pOldEntity, ENTITY pNewEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".update" );
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
            return super.update( pNewEntity );
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }

    /**
     * 削除する.
     * 
     * @param pOldEntity 旧エンティティ
     * @param pNewEntity 新エンティティ
     * @return 操作数
     */
    public int delete( ENTITY pOldEntity, ENTITY pNewEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".delete" );
        }

        if ( pOldEntity.updDate.equals( pNewEntity.updDate ) == false ) {
            // 競合発生
            throw new EcConflictException();
        }

        // 更新ユーザID・日時
        pNewEntity.updUserId = IcCmnConst.LOGIN_USER_ID;
        pNewEntity.updDate = new Date();

        try {
            // 削除
            return super.delete( pNewEntity );
        } finally {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputExecSql();
            }
        }
    }
}