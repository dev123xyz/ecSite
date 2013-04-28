package ec.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>基底エンティティ</DD>
 * </DL>
 */
@MappedSuperclass
public class AbEntity implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Column( name = "UPD_USER_ID" )
    public String updUserId;

    @Temporal( TemporalType.DATE )
    @Column( name = "UPD_DATE" )
    public Date updDate;
}
