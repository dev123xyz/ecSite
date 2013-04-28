package ec.action;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>基底アクション</DD>
 * </DL>
 */
abstract class AbAction {

    /**
     * 一覧JSP
     */
    protected static String LIST = "list.jsp";

    /**
     * 詳細JSP
     */
    protected static String DTL = "detail.jsp";

    /**
     * 登録編集JSP
     */
    protected static String REGIST_EDT = "registEdt.jsp";

    /**
     * 登録確認JSP
     */
    protected static String REGIST_CFM = "registCfm.jsp";

    /**
     * インデックス
     * 
     * @return インデックスページ
     */
    public abstract String index();

    /**
     * 一覧画面を表示する.
     * 
     * @return　一覧画面
     */
    public abstract String list();

    /**
     * 詳細画面を表示する.
     * 
     * @return　詳細画面
     */
    public abstract String detail();

    /**
     * 登録(追加・編集)編集画面を表示する.
     * 
     * @return　登録(追加・編集)編集画面
     */
    public abstract String registEdt();

    /**
     * 登録(追加・編集)確認画面を表示する.
     * 
     * @return　登録(追加・編集)確認画面
     */
    public abstract String registCfm();

    /**
     * 登録(追加・編集)を実行する.
     * 
     * @return　一覧画面
     */
    public abstract String registExe();

    /**
     * 削除画面を実行する.
     * 
     * @return　一覧画面
     */
    public abstract String delete();
}