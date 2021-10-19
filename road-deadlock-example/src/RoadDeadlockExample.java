import java.util.Random;

public class RoadDeadlockExample {
    public static void main(String[] args) {
        //Intersection intersection = new Intersection();
        IntersectionFixed intersection = new IntersectionFixed();
        Thread roadA = new Thread(()->{
            Random random = new Random();
           while (true){
               try {
                   Thread.sleep(random.nextInt(7));
                   intersection.takeRoadA();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread roadB = new Thread(()->{
            Random random = new Random();
            while (true){
                try {
                    Thread.sleep(random.nextInt(5));
                    intersection.takeRoadB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        roadA.start();
        roadB.start();
    }

    public static class Intersection{
        private Object roadA;
        private Object roadB;

        public Intersection(){
            roadA=new Object();
            roadB=new Object();
        }

        public void takeRoadA() throws InterruptedException {
            synchronized (roadB){
                System.out.println("LOCKED ROAD B AND WAIT FOR ROAD A");
                synchronized (roadA){
                    System.out.println("ROAD A TAKEN!");
                }
            }
        }

        public void takeRoadB() throws InterruptedException {
            synchronized (roadA){
                System.out.println("LOCKED ROAD A AND WAIT FOR ROAD B");
                synchronized (roadB){
                    System.out.println("ROAD B TAKEN!");
                }
            }
        }

    }

    public static class IntersectionFixed{
        private Object roadA;
        private Object roadB;

        public IntersectionFixed(){
            roadA=new Object();
            roadB=new Object();
        }

        public void takeRoadA() throws InterruptedException {
            synchronized (roadA){
                System.out.println("LOCKED ROAD B AND WAIT FOR ROAD A");
                synchronized (roadB){
                    System.out.println("ROAD A TAKEN!");
                }
            }
        }

        public void takeRoadB() throws InterruptedException {
            synchronized (roadA){
                System.out.println("LOCKED ROAD A AND WAIT FOR ROAD B");
                synchronized (roadB){
                    System.out.println("ROAD B TAKEN!");
                }
            }
        }

    }
}
