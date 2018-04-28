package you_name_is_yu.threadlocal.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import you_name_is_yu.threadlocal.dto.ADto;
import you_name_is_yu.threadlocal.vo.where.AWhereVo;

public class ThreadLocalUtil {

    private static final Logger logger = Logger.getLogger(ThreadLocalUtil.class);
    private static final ThreadLocal<HashMap<AWhereVo, List<ADto>>> THREAD_LOCAL = new ThreadLocal<HashMap<AWhereVo, List<ADto>>>();

    private ThreadLocalUtil() {
    }

    public static void initThreadLocalUtil() {
        clear();
        THREAD_LOCAL.set(new HashMap<AWhereVo, List<ADto>>());
    }

    public static void put(AWhereVo key, List<ADto> val) {
        Map<AWhereVo, List<ADto>> map = THREAD_LOCAL.get();
        map.put(key, val);
    }

    public static List<ADto> get(AWhereVo key) {
        List<ADto> result = null;
        Map<AWhereVo, List<ADto>> map = THREAD_LOCAL.get();
        if (map.containsKey(key)) {
            logger.info("あったよ");
            result = map.get(key);
        }

        return result;
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}