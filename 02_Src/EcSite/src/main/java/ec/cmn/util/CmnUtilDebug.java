package ec.cmn.util;

import org.seasar.extension.jdbc.SqlLog;
import org.seasar.extension.jdbc.SqlLogRegistry;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;

/**
 * <DL>
 * <DD>＜クラス名称＞</DD>
 * <DD>共通ユーティリティ</DD>
 * </DL>
 */
public class CmnUtilDebug {

    /**
     * クラス名称
     */
    private static final String CLASS = CmnUtilDebug.class.getSimpleName();

    /**
     * デバッグフラグ
     * <DL>
     * <DD>false:デバッグOFF</DD>
     * <DD>true:デバッグON</DD>
     * </DL>
     * ※各メソッドからデバッグ出力メソッドをコールするときに、<BR>
     * 文字列結合をさせないために、
     */
    public static final boolean DEBUG_FLG = true;

    /**
     * 実行SQLを出力する.<BR>
     * ※本メソッドを多量にコールされる。<BR>
     * デバッグがOFFの場合であっても、呼出元は文字列結合処理をしてしまうため<BR>
     * パフォーマンスが悪くなることがある。<BR>
     * それを防ぐために、呼出元は「DEBUG_FLG」を判定した後に<BR>
     * デバッグ文字列を作成し本メソッドを呼び出すこととする。
     * 
     * @param pDebugStr デバッグ文字列
     */
    public static void outputDebug( String pDebugStr ) {

        System.out.println( pDebugStr );
    }

    /**
     * 実行SQLを出力する.
     */
    public static void outputExecSql() {

        outputDebug( CLASS + ".outputExecSql" );

        SqlLogRegistry registry = SqlLogRegistryLocator.getInstance();

        if ( registry != null ) {
            SqlLog sqlLog = registry.getLast();
            outputDebug( sqlLog.getCompleteSql() );
        }
    }
}
