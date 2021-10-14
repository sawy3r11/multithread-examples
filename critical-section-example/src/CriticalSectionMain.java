public class CriticalSectionMain {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter counter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(counter);
        DecreasingThread decreasingThread = new DecreasingThread(counter);

        incrementingThread.start();
        decreasingThread.start();

        incrementingThread.join();
        decreasingThread.join();

        System.out.println(counter.items);
    }

    public static class DecreasingThread extends Thread{
        private InventoryCounter counter;
        public DecreasingThread(InventoryCounter counter) {
            this.counter = counter;
        }
        @Override
        public void run(){
            for(int i=0; i<10000; i++){
                counter.decrement();
            }
        }
    }

    public static class IncrementingThread extends Thread{
        private InventoryCounter counter;
        public IncrementingThread(InventoryCounter counter) {
            this.counter = counter;
        }
        @Override
        public void run(){
            for(int i=0; i<10000; i++){
                counter.increment();
            }
        }
    }

    public static class InventoryCounter{
        private int items = 0;
        public void increment(){
            items++;
        }
        public void decrement(){
            items--;
        }
    }
}
