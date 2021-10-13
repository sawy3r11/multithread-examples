package com.dk;

import java.math.BigInteger;

public class Calculate2Pow {

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
        thread1.start();
        thread2.start();

        // my solution!!
//        while (!(thread1.isFinished() && thread2.isFinished())){
//            //wait
//        }

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return thread1.getResult().add(thread2.getResult());
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
        private boolean finished;
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!=0; i=i.add(BigInteger.ONE)){
              result = result.multiply(base);
            }
           finished = true;
        }

        public boolean isFinished() {
            return finished;
        }

        public BigInteger getResult() { return result; }
    }
}

