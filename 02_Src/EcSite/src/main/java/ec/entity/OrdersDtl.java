package ec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrdersDtl extends AbEntity {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "ORDER_ID" )
    public String orderId;

    @Id
    @GeneratedValue
    @Column( name = "ORDER_DTL_NO" )
    public Integer orderDtlNo;

    @Column( name = "ORDER_GOODS_ID" )
    public String orderGoodsId;

    @Column( name = "ORDER_GOODS_NUM" )
    public Integer orderGoodsNum;

    @Column( name = "ORDER_GOODS_UNIT_PRICE" )
    public Long orderGoodsUnitPrice;
}
