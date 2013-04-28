package ec.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Usr extends AbEntity {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "USR_ID" )
    public String usrId;

    @Column( name = "USR_NAME" )
    public String usrName;

    @Column( name = "USR_PWD" )
    public String usrPwd;

    @Column( name = "DEL_FLG" )
    public String delFlg;
    
    @OneToMany(mappedBy = "usr")
    public List<Orders> ordersList;
}