package you_name_is_yu.threadlocal;

import java.util.concurrent.Callable;

import you_name_is_yu.threadlocal.action.SampleAction;
import you_name_is_yu.threadlocal.dto.ParamDto;
import you_name_is_yu.threadlocal.util.ThreadLocalUtil;

/**
 * ExecutorServiceを利用して実際に各スレッドの開始ポイントとなるクラス。
 * これが１ユーザーセッション（Request）に相当するイメージ。
 *
 * @author You_name_is_YU
 *
 */
public class SampleCallable implements Callable<Void> {

    /** 呼び出し実行ユーザ名 */
    private String executingUser;
    /** 画面からのパラメータで、SELECT対象を絞り込むIDの条件（仮） */
    private String id;
    /** SELECT文がどの程度重いかを表す時間（ミリ秒）※疑似的にSQLが重いことを表現するために利用 */
    private long sleepTime;
    /** ThreadLocalを利用するかどうかのフラグ */
    private boolean enableThreadLocal;

    public SampleCallable(String executingUser, String id, long sleepTime, boolean enableThreadLocal) {
        this.executingUser = executingUser;
        this.id = id;
        this.sleepTime = sleepTime;
        this.enableThreadLocal = enableThreadLocal;
    }

    @Override
    public Void call() throws Exception {

        // ThreadLocalを利用する場合、いったん初期化。
        if (this.enableThreadLocal) {
            ThreadLocalUtil.initThreadLocalUtil();
        }

        // StrutsでいうところのActionを実行
        // ※本来はアプリケーションサーバがRequestを受け付けた際に生成して実行するが、今回はそれを疑似的に再現。
        SampleAction action = new SampleAction();
        action.doAction(new ParamDto(this.executingUser, this.id, this.sleepTime, this.enableThreadLocal));
        return null;
    }

    public String getExecutingUser() {
        return executingUser;
    }

    public void setExecutingUser(String executingUser) {
        this.executingUser = executingUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public boolean isEnableThreadLocal() {
        return enableThreadLocal;
    }

    public void setEnableThreadLocal(boolean enableThreadLocal) {
        this.enableThreadLocal = enableThreadLocal;
    }
}
