package ec.form.orders;

import java.util.ArrayList;
import java.util.List;

import ec.form.cmn.AbFormItem;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>注文情報フォーム項目</DD>
 * </DL>
 */
public class OrdersFormItem extends AbFormItem {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 注文ID
     */
    public String orderId = "";

    /**
     * 注文ユーザID
     */
    public String orderUsrId = "";

    /**
     * 注文日時
     */
    public String orderDate = "";

    /**
     * 注文明細リスト
     */
    public List< OrdersDtlFormItem > ordersDtlFormItemList = new ArrayList< OrdersDtlFormItem >();

}