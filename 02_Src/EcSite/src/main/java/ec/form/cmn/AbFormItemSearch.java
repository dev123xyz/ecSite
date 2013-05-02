package ec.form.cmn;

import java.io.Serializable;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>基底フォーム項目(検索)</DD>
 * </DL>
 */
public abstract class AbFormItemSearch implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * キーワード
     */
    public String keyword = "";

    /**
     * キーワードAND/OR
     */
    public String keywordAndOr = "AND";

    // ページングのためのプロパティ
    //
    // public String offset = "0";
    //
    // public String count = "0";
    //
    // public String isNextPage = "true";
    //
    // public String isPrevPage = "true";
    //
    // public String totalNumber = "0";
    //
    // public String currentPageIndex = "0";
    //
    // public String totalPageIndex = "0";
}