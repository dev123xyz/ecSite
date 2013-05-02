package ec.form.orders;

import ec.entity.Goods;
import ec.form.cmn.AbFormItem;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>注文詳細情報フォーム項目</DD>
 * </DL>
 */
public class OrdersDtlFormItem extends AbFormItem {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 注文ID
     */
    public String orderId = "";

    /**
     * 注文詳細番号
     */
    public String orderDtlNo = "";

    /**
     * 注文商品ID
     */
    public String orderGoodsId = "";

    /**
     * 注文商品数量
     */
    public String orderGoodsNum = "";

    /**
     * 注文商品単価
     */
    public String orderGoodsUnitPrice = "";

    /**
     * 商品情報
     */
    public Goods goods = new Goods();

}