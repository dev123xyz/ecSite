package ec.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import ec.cmn.util.CmnUtil;
import ec.cmn.util.CmnUtilDebug;
import ec.entity.Usr;
import ec.form.UsrForm;
import ec.form.UsrFormItem;
import ec.service.UsrService;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>ユーザ情報アクション</DD>
 * </DL>
 */
public class UsrAction extends AbAction {

    /**
     * クラス名称
     */
    private static final String CLASS = UsrAction.class.getSimpleName();

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
     * @return　一覧画面
     */
    @Execute( validator = false, urlPattern = "list/" )
    public String list() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".list" );
        }

        try {

            List<Usr> entityList = usrService.selectAll( "userId", "DESC" );
            usrForm.usrFormItemList = CmnUtil.getCopyList( UsrFormItem.class,
                    entityList );

        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".list : DBエラー" );
            }
        }

        return LIST;
    }

    /**
     * 詳細画面を表示する.
     * 
     * @return　詳細画面
     */
    @Execute( validator = false, urlPattern = "detail/{usrFormItem.usrId}" )
    public String detail() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".detail" );
        }

        try {

            Usr entity = usrService.selectById( usrForm.usrFormItem.usrId );
            usrForm.usrFormItem = CmnUtil.getCopy( UsrFormItem.class, entity );

        } catch ( Exception e ) {
            if ( CmnUtilDebug.DEBUG_FLG == true ) {
                CmnUtilDebug.outputDebug( CLASS + ".detail : 例外", e );
            }
        }

        return DTL;
    }

    /**
     * 登録(追加・編集)編集画面を表示する.
     * 
     * @return　登録(追加・編集)編集画面
     */
    @Execute( validator = false, urlPattern = "registEdt/{usrId}" )
    public String registEdt() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registEdt" );
        }

        return REGIST_EDT;
    }

    /**
     * 登録(追加・編集)確認画面を表示する.
     * 
     * @return　登録(追加・編集)確認画面
     */
    @Execute( validator = false, urlPattern = "registCfm/{usrId}" )
    public String registCfm() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registCfm" );
        }

        return REGIST_CFM;
    }

    /**
     * 登録(追加・編集)を実行する.
     * 
     * @return　一覧画面
     */
    @Execute( validator = false, urlPattern = "registExe/{usrId}", redirect = true )
    public String registExe() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".registExe" );
        }

        return list();
    }

    /**
     * 削除画面を実行する.
     * 
     * @return　一覧画面
     */
    @Execute( validator = false, urlPattern = "delete/{usrId}", redirect = true )
    public String delete() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".delete" );
        }

        Usr entity = Beans.createAndCopy( Usr.class, usrForm )
                .dateConverter( "yyyy-MM-dd" ).execute();
        usrService.delete( entity );

        return list();
    }
}