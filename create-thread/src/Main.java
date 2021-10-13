public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            int x = 10;
            x +=1;
            System.out.println("Hello World from : "+Thread.currentThread().getName() + x);
        });

        thread.setName("Worker thread 1");
        thread.start();
        //Thread.sleep(1000);
        System.out.println("End of program!");
    }
}
