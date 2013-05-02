package ec.form.usr;

import java.util.ArrayList;
import java.util.List;

import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

import ec.form.cmn.AbFormItem;
import ec.form.orders.OrdersFormItem;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>ユーザ情報フォーム項目</DD>
 * </DL>
 */
public class UsrFormItem extends AbFormItem {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ユーザID
     */
    public String usrId = "";

    /**
     * ユーザ名称
     */
    @Required
    @Minlength( minlength = 10 )
    public String usrName = "";

    /**
     * ユーザパスワード
     */
    public String usrPwd = "";

    /**
     * 無効フラグ
     */
    public String invalidFlg = "0";

    /**
     * 注文リスト
     */
    public List< OrdersFormItem > ordersFormItemList = new ArrayList< OrdersFormItem >();
}