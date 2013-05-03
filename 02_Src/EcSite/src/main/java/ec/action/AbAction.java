package ec.action;

import ec.entity.AbEntity;
import ec.form.cmn.AbFormItem;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>基底アクション</DD>
 * </DL>
 */
abstract class AbAction< FORM_ITEM extends AbFormItem, ENTITY extends AbEntity > {

    /**
     * アクション : ホーム
     */
    protected static final String ACT_HOME = "/";

    /**
     * アクション : 一覧
     */
    protected static String ACT_LIST = "/usr/list";

    /**
     * JSP : 一覧
     */
    protected static final String JSP_LIST = "list.jsp";

    /**
     * JSP : 詳細
     */
    protected static final String JSP_DTL = "detail.jsp";

    /**
     * JSP : 登録編集
     */
    protected static final String JSP_REGIST_EDT = "registEdt.jsp";

    /**
     * JSP : 登録確認
     */
    protected static final String JSP_REGIST_CFM = "registCfm.jsp";

    /**
     * インデックス
     * 
     * @return インデックスページ
     */
    public abstract String index();

    /**
     * 一覧画面を表示する.
     * 
     * @return 一覧画面
     */
    public abstract String list();

    /**
     * 詳細画面を表示する.
     * 
     * @return 詳細画面
     */
    public abstract String detail();
    
    /**
     * 登録(追加)画面を表示する.
     * 
     * @return 登録(追加)画面
     */
    public abstract String registAdd();

    /**
     * 登録(編集)画面を表示する.
     * 
     * @return 登録(編集)画面
     */
    public abstract String registEdt();

    /**
     * 登録(追加・編集)確認画面を表示する.
     * 
     * @return 登録(追加・編集)確認画面
     */
    public abstract String registCfm();

    /**
     * 登録(追加・編集)を実行する.
     * 
     * @return 一覧画面
     */
    public abstract String registExe();

    /**
     * 削除を実行する.
     * 
     * @return 一覧画面
     */
    public abstract String delete();

    /**
     * エンティティからフォーム項目をコピーする.
     * 
     * @param エンティティ
     * @return フォーム項目
     */
    protected abstract AbFormItem copyFromItemEntityToForm( ENTITY pEntity );
}