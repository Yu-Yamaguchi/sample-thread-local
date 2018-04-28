package you_name_is_yu.threadlocal.dto;

public class ParamDto {

    private String executingUser;
    private String id;
    private long sleepTime;
    private boolean enableThreadLocal;

    public ParamDto(String executingUser, String id, long sleepTime, boolean enableThreadLocal) {
        this.executingUser = executingUser;
        this.id = id;
        this.sleepTime = sleepTime;
        this.enableThreadLocal = enableThreadLocal;
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
