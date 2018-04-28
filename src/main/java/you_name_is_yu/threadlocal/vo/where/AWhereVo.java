package you_name_is_yu.threadlocal.vo.where;

import org.apache.log4j.Logger;

public class AWhereVo {

    private static final Logger logger = Logger.getLogger(AWhereVo.class);

    private String id;

    public AWhereVo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj)
            result = true;
        if (obj == null)
            result = false;
        if (getClass() != obj.getClass())
            result = false;
        AWhereVo other = (AWhereVo) obj;
        if (id == null) {
            if (other.id != null)
                result = false;
        } else if (!id.equals(other.id)) {
            result = false;
        }
        result = true;

        if (result) {
            logger.info("[" + Thread.currentThread().getName() + "] 同じ！！");
        }
        return result;
    }

}
