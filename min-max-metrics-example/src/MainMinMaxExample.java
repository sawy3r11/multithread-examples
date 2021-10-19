import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainMinMaxExample {
    public static final int GENERATE_AMOUNT = 100000;
    public static void main(String[] args) {
        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
        List<DataGenerator> dataGenerators = new LinkedList<>();

        for(int i=0; i<10;i++){
            dataGenerators.add(new DataGenerator(GENERATE_AMOUNT, minMaxMetrics));
        }

        dataGenerators.stream().forEach(val->val.start());
        dataGenerators.stream().forEach(val-> {
            try {
                val.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(String.format("MIN=%s | MAX=%s", minMaxMetrics.getMin(), minMaxMetrics.getMax()));
    }

    public static class DataGenerator extends Thread{
        private Random random;
        private int numberOfData;
        private MinMaxMetrics minMaxMetrics;

        public DataGenerator(int numberOfData, MinMaxMetrics minMaxMetrics){
            this.random = new Random();
            this.numberOfData = numberOfData;
            this.minMaxMetrics = minMaxMetrics;
        }

        @Override
        public void run(){
            for(int i=0; i<numberOfData; i++){
                minMaxMetrics.addSample(random.nextLong());
            }
        }
    }
}
