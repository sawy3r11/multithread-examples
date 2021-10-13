import java.math.BigInteger;

public class ThreadTerminationMain {
    public static void main(String[] args) throws InterruptedException {
        Thread pow = new Thread(new Runnable1(new BigInteger("2"), new BigInteger("999999")));

        pow.setDaemon(true);
        pow.start();
        pow.interrupt();
        System.out.println("AFTER INTERRUPT!");
        Thread.sleep(1000);

    }

    public static class Runnable1 implements Runnable{
        private BigInteger base;
        private BigInteger power;

        public Runnable1(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            BigInteger result = BigInteger.ONE;

            for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!=0; i=i.add(BigInteger.ONE)){
                result = result.multiply(base);
            }

            System.out.println(String.format("%s^%s = %s", base, power, result));
        }
    }

    public static class Runnable2 implements Runnable{
        private BigInteger base;
        private BigInteger power;

        public Runnable2(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            BigInteger result = BigInteger.ONE;

            for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!=0; i=i.add(BigInteger.ONE)){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Terminated!");
                    break;
                }
                result = result.multiply(base);
            }

            System.out.println(String.format("%s^%s = %s", base, power, result));
        }
    }
}
