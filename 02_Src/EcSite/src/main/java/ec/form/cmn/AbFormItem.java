package ec.form.cmn;

import java.io.Serializable;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>基底フォーム項目</DD>
 * </DL>
 */
public abstract class AbFormItem implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 更新ユーザID
     */
    public String updUserId = "";

    /**
     * 更新日時
     */
    public String updDate = "";
}