package ec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Goods extends AbEntity {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "GOODS_ID" )
    public String goodsId;

    @Column( name = "GOODS_NAME" )
    public String goodsName;
}
