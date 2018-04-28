package you_name_is_yu.threadlocal.dao;

import java.util.ArrayList;
import java.util.List;

import you_name_is_yu.threadlocal.dto.ADto;
import you_name_is_yu.threadlocal.dto.ParamDto;
import you_name_is_yu.threadlocal.util.ThreadLocalUtil;
import you_name_is_yu.threadlocal.vo.where.AWhereVo;

/**
 * Aテーブルに対する操作を行うDao。
 * @author You_name_is_YU
 *
 */
public class ADao {

    public List<ADto> selAList(ParamDto param) throws InterruptedException {

        // Where句の条件用Voの生成
        AWhereVo where = new AWhereVo(param.getId());
        where.setId(param.getId());

        List<ADto> result = null;

        // ThreadLocalに同一条件のオブジェクトが既に格納されている場合、
        // その結果セットを再利用してそのまま返却する。
        if (param.isEnableThreadLocal()) {
            result = ThreadLocalUtil.get(where);

            if (result != null) {
                return result;
            }
        }

        result = new ArrayList<>();
        {
            // SELECT文を実行して取得結果を生成するブロック
            // sleepしているのは、SQLがある程度重くて、その結果を取得することを疑似的に表現するため。
            Thread.sleep(param.getSleepTime());
            result.add(new ADto(param.getId(), "test"));
            result.add(new ADto(param.getId() + "+1", "test1"));
            result.add(new ADto(param.getId() + "+2", "test2"));
        }

        if (param.isEnableThreadLocal()) {
            ThreadLocalUtil.put(where, result);
        }

        return result;
    }
}
