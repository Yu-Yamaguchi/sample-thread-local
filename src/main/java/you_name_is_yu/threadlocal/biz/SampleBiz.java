package you_name_is_yu.threadlocal.biz;

import java.util.List;

import org.apache.log4j.Logger;

import you_name_is_yu.threadlocal.dao.ADao;
import you_name_is_yu.threadlocal.dto.ADto;
import you_name_is_yu.threadlocal.dto.ParamDto;

/**
 * トランザクションスクリプトでの実装であるため、ビジネスロジックを表すクラス。
 * 単純にDaoを呼んで、SELECTした結果のリストをログ出力している。
 *
 * @author You_name_is_YU
 *
 */
public class SampleBiz {

    private static final Logger logger = Logger.getLogger(SampleBiz.class);

    /**
     * ビジネスロジック。
     * １つのビジネスロジックで、何回も同じ条件でSELECTを呼び、その取得結果を利用してログ出力を行っている。
     * 同一メソッド内でこのように何度も同じことをすることは少ないと思いますが、
     * 例えばこのビジネスロジッククラスから他のクラスのメソッドを複数クラス、複数メソッド実行し、
     * それぞれの中で、同じマスタの値を取得するのに、別々にSELECTしているようなケースがあったとした場合などを無理やり考えてください。
     *
     * @param param パラメータ
     */
    public void doBiz(ParamDto param) {

        long start = 0;
        long end = 0;

        ADao dao = new ADao();
        try {
            logger.info("＝＝＝1回目 開始＝＝＝");
            start = System.currentTimeMillis();
            List<ADto> list = dao.selAList(param);
            for (ADto dto : list) {
                logger.info(dto);
            }
            end = System.currentTimeMillis();
            logger.info((end - start) + "ms");

            logger.info("＝＝＝2回目 開始＝＝＝");
            start = System.currentTimeMillis();
            List<ADto> list2 = dao.selAList(param);
            for (ADto dto : list2) {
                logger.info(dto);
            }
            end = System.currentTimeMillis();
            logger.info((end - start) + "ms");

            logger.info("＝＝＝3回目 開始＝＝＝");
            start = System.currentTimeMillis();
            List<ADto> list3 = dao.selAList(param);
            for (ADto dto : list3) {
                logger.info(dto);
            }
            end = System.currentTimeMillis();
            logger.info((end - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
