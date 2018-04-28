package you_name_is_yu.threadlocal.action;

import you_name_is_yu.threadlocal.biz.SampleBiz;
import you_name_is_yu.threadlocal.dto.ParamDto;

/**
 * 既存システムがStruts1.X系で構築されたレガシーなシステムを想定した、
 * Actionクラスに該当するクラス。
 *
 * @author You_name_is_YU
 *
 */
public class SampleAction {

    public void doAction(ParamDto param) {
        SampleBiz biz = new SampleBiz();
        biz.doBiz(param);
    }
}
