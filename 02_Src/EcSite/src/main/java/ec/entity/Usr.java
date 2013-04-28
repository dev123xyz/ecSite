package ec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import java.util.Date;


@Entity
public class Usr {

    @Id
    @GeneratedValue
    @Column(name="USR_ID")
    public String usrId;

    @Column(name="USR_NAME")
    public String usrName;

    @Column(name="USR_PWD")
    public String usrPwd;

    @Column(name="UPD_USER_ID")
    public String updUserId;

    @Column(name="DEL_FLG")
    public String delFlg;

    @Temporal(TemporalType.DATE)
    @Column(name="UPD_DATE")
    public Date updDate;

}