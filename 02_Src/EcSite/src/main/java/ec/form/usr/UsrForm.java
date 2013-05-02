package ec.form.usr;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ec.cmn.util.CmnUtilDebug;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>ユーザ情報フォーム</DD>
 * </DL>
 */
public class UsrForm {

    /**
     * クラス名称
     */
    private static final String CLASS = UsrForm.class.getSimpleName();

    /**
     * ユーザ情報フォーム項目(検索)
     */
    public UsrFormItemSearch usrFormItemSearch = new UsrFormItemSearch();

    /**
     * ユーザ情報フォーム項目
     */
    public UsrFormItem usrFormItem = new UsrFormItem();

    /**
     * ユーザ情報フォーム項目リスト
     */
    public List< UsrFormItem > usrFormItemList = new ArrayList< UsrFormItem >();

    /**
     * 編集時のバリデーション
     * 
     * @return アクションメッセージ
     */
    public ActionMessages validateWhenEdit() {

        if ( CmnUtilDebug.DEBUG_FLG == true ) {
            CmnUtilDebug.outputDebug( CLASS + ".validateWhenEdit" );
        }

        ActionMessages retValue = new ActionMessages();

        // ユーザ名称
        if ( usrFormItem.usrName == null || usrFormItem.usrName.length() == 0 ) {
            // 必須チェック
            retValue.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "errors.required", "ユーザ名称" ) );
        } else if ( this.usrFormItem.usrName.length() > 20 ) {
            // 最大長チェック
            retValue.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "errors.maxlength", "ユーザ名称", "20" ) );
        }

        // ユーザPWD
        if ( usrFormItem.usrId == null || usrFormItem.usrId.length() == 0 ) {
            // 追加の場合

            if ( usrFormItem.usrPwd == null || usrFormItem.usrPwd.length() == 0 ) {
                // 必須チェック
                retValue.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "errors.required", "ユーザPWD" ) );
            } else if ( this.usrFormItem.usrPwd.length() < 4 ) {
                // 最小長チェック
                retValue.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "errors.minlength", "ユーザPWD", "4" ) );
            } else if ( this.usrFormItem.usrPwd.length() > 8 ) {
                // 最大長チェック
                retValue.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "errors.maxlength", "ユーザPWD", "8" ) );
            }

        } else {
            // 編集の場合

            if ( usrFormItem.usrPwd != null && usrFormItem.usrPwd.length() != 0 ) {

                if ( this.usrFormItem.usrPwd.length() < 4 ) {
                    // 最小長チェック
                    retValue.add( ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage( "errors.minlength", "ユーザPWD",
                                    "4" ) );
                } else if ( this.usrFormItem.usrPwd.length() > 8 ) {
                    // 最大長チェック
                    retValue.add( ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage( "errors.maxlength", "ユーザPWD",
                                    "8" ) );
                }
            }
        }

        return retValue;
    }
}