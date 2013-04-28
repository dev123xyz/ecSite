package ec.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orders extends AbEntity {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "ORDER_ID" )
    public String orderId;

    @Column( name = "ORDER_USR_ID" )
    public String orderUsrId;

    @Temporal( TemporalType.DATE )
    @Column( name = "ORDER_DATE" )
    public Date orderDate;
    
    @ManyToOne
    @JoinColumn(name="ORDER_USR_ID")
    Usr usr;
}
