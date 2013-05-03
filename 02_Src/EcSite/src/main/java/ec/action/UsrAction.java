package ec.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import ec.cmn.util.CmnUtil;
import ec.cmn.util.CmnUtilDebug;
import ec.entity.Goods;
import ec.entity.Orders;
import ec.entity.OrdersDtl;
import ec.entity.Usr;
import ec.form.orders.OrdersDtlFormItem;
import ec.form.orders.OrdersFormItem;
import ec.form.usr.UsrForm;
import ec.form.usr.UsrFormItem;
import ec.service.UsrService;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>ユーザ情報アクション</DD>
 * </DL>
 */
public class UsrAction extends AbAction< UsrFormItem, Usr > {

    /**
     * クラス名称
     */
    private static final String CLASS = UsrAction.class.getSimpleName();

    /**
     * 標準ソート列
     */
    private static String DEF_ORDER_BY_COL = "usrId";

    /**
     * 標準ソートASC/DESC
     */
    private static String DEF_ORDER_BY_ASC_DESC = "ASC";

    /**
     * フォーム
     */
    @ActionForm
    @Resource
    protected UsrForm usrForm;

    /**
     * サービス
     */
    @Resource
    protected UsrService usrService;

    /**
     * インデックス
     * 
     * @return インデックスページ
     */
    @Execute( validator = false )
    public String index() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".index" );
        }

        return list();
    }

    /**
     * 一覧画面を表示する.
     * 
     * @return 一覧画面
     */
    @Execute( validator = false, urlPattern = "list" )
    public String list() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".list" );
        }

        try {

            List< Usr > entityList = usrService.selectAll( DEF_ORDER_BY_COL,
                    DEF_ORDER_BY_ASC_DESC, usrForm.usrFormItemSearch.keyword,
                    usrForm.usrFormItemSearch.keywordAndOr );
            usrForm.usrFormItemList = CmnUtil.getCopyList( UsrFormItem.class,
                    entityList );

        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".list : DBエラー", e );
            }
        }

        return JSP_LIST;
    }

    /**
     * 詳細画面を表示する.
     * 
     * @return 詳細画面
     */
    @Execute( validator = false, urlPattern = "detail/{usrFormItem.usrId}" )
    public String detail() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".detail" );
        }

        try {

            Usr entity = usrService.selectById( usrForm.usrFormItem.usrId );
            usrForm.usrFormItem = copyFromItemEntityToForm( entity );

        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".detail : 例外", e );
            }
        }

        return JSP_DTL;
    }

    /**
     * 登録(追加)画面を表示する.
     * 
     * @return 登録(追加)画面
     */
    @Execute( validator = false, urlPattern = "registAdd" )
    public String registAdd() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registAdd" );
        }

        return JSP_REGIST_EDT;
    }

    /**
     * 登録(編集)画面を表示する.
     * 
     * @return 登録(編集)画面
     */
    @Execute( validator = false, urlPattern = "registEdt/{usrFormItem.usrId}" )
    public String registEdt() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registEdt" );
        }

        try {

            Usr entity = usrService.selectById( usrForm.usrFormItem.usrId );
            usrForm.usrFormItem = copyFromItemEntityToForm( entity );

        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".registEdt : 例外", e );
            }
        }

        return JSP_REGIST_EDT;
    }

    /**
     * 登録(追加・編集)確認画面を表示する.
     * 
     * @return 登録(追加・編集)確認画面
     */
    @Execute( validate = "validateWhenEdit", input = JSP_REGIST_EDT, urlPattern = "registCfm" )
    public String registCfm() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registCfm" );
        }

        return JSP_REGIST_CFM;
    }

    /**
     * 登録(追加・編集)を実行する.
     * 
     * @return 一覧画面
     */
    @Execute( validator = false, urlPattern = "registExe", redirect = true )
    public String registExe() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registExe" );
        }

        String returnUrl = null;

        try {
            Usr entity = CmnUtil.getCopy( Usr.class, usrForm.usrFormItem );

            if ( entity.usrId == null || entity.usrId.length() == 0 ) {
                // 追加の場合
                returnUrl = ACT_HOME;
                usrService.insert( entity );
            } else {
                // 編集の場合
                returnUrl = ACT_LIST;
                usrService.update( entity );
            }
        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".registExe : 例外", e );
            }
        }

        return returnUrl;
    }

    /**
     * 削除を実行する.
     * 
     * @return 一覧画面
     */
    @Execute( validator = false, urlPattern = "delete/{usrFormItem.usrId}", redirect = true )
    public String delete() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".delete" );
        }

        Usr entity = new Usr();
        entity.usrId = usrForm.usrFormItem.usrId;

        try {
            usrService.delete( entity );
        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".delete : 例外", e );
            }
        }

        return ACT_LIST;
    }

    /**
     * エンティティからフォーム項目をコピーする.
     * 
     * @param エンティティ
     * @return フォーム項目
     */
    protected UsrFormItem copyFromItemEntityToForm( Usr pEntity ) {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".copyFromItemEntityToForm" );
        }

        UsrFormItem fmItem = null;

        // ユーザ情報をコピー
        {
            fmItem = CmnUtil.getCopy( UsrFormItem.class, pEntity );

            if ( fmItem == null ) {
                return new UsrFormItem();
            }
        }

        // 注文情報をコピー
        {
            fmItem.ordersFormItemList = CmnUtil.getCopyList(
                    OrdersFormItem.class, pEntity.ordersList );

            if ( fmItem.ordersFormItemList == null ) {
                return fmItem;
            }
        }

        // 注文明細情報をコピー
        for ( int i = 0; i < pEntity.ordersList.size(); i++ ) {

            Orders orders = pEntity.ordersList.get( i );
            OrdersFormItem ordersFormItem = fmItem.ordersFormItemList.get( i );

            if ( orders.ordersDtlList == null ) {
                continue;
            }

            ordersFormItem.ordersDtlFormItemList = CmnUtil.getCopyList(
                    OrdersDtlFormItem.class, orders.ordersDtlList );

            // 商品情報をコピー
            for ( int j = 0; j < orders.ordersDtlList.size(); j++ ) {

                OrdersDtl ordersDtl = orders.ordersDtlList.get( j );
                OrdersDtlFormItem ordersDtlFormItem = ordersFormItem.ordersDtlFormItemList
                        .get( j );

                if ( ordersDtl == null ) {
                    continue;
                }

                ordersDtlFormItem.goods = CmnUtil.getCopy( Goods.class,
                        ordersDtl.goods );
            }
        }

        return fmItem;
    }
}