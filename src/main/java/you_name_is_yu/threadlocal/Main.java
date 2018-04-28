package you_name_is_yu.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 複数のクライアントからほぼ同時にアクセスされたことを疑似的に再現するための処理クラス。
 * ExecutorServiceを使って複数スレッドを生成。
 *
 * @author You_name_is_YU
 *
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        // ThreadLocalを使った性能改善を有効化するかどうかのフラグ
        boolean enableThreadLocal = true;

        long start = System.currentTimeMillis();

        // ほぼ同時に３台のクライアントからRequestがあったと仮定したマルチスレッド処理の定義
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        List<Callable<Void>> processes = new ArrayList<>();

        processes.add(new SampleCallable("taro", "101", 700, enableThreadLocal));
        processes.add(new SampleCallable("jiro", "101", 300, enableThreadLocal));
        processes.add(new SampleCallable("saburo", "101", 500, enableThreadLocal));

        // マルチスレッドでの３台同時リクエストを実行
        try {
            threadPool.invokeAll(processes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        long end = System.currentTimeMillis();
        logger.info("■□■□■□合計実行時間（ms）■□■□■□");
        logger.info((end - start) + "ms");
    }
}
