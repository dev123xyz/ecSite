package ec.action;

import org.seasar.struts.annotation.Execute;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>初期ページアクション</DD>
 * </DL>
 */
public class IndexAction {

    @Execute( validator = false )
    public String index() {
        return "index.jsp";
    }
}
