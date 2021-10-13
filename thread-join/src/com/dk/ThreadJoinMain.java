package com.dk;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreadJoinMain {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> values = Arrays.asList(99, 999, 9999, 99999,499999, 799999, 999999);
        List<FibonacciRunnable> threads = new LinkedList<>();

        values.forEach(val->{
            threads.add(new FibonacciRunnable(val));
        });

        threads.forEach(val->{
            val.setDaemon(true);
            val.start();
        });

        for(Thread val: threads){
            long before = System.currentTimeMillis();
            val.join(1000);
            long after = System.currentTimeMillis();
            System.out.println(String.format("Diff: %s", after-before));
        }

        threads.forEach(val->{
            if(val.isFinished()){
                System.out.println(String.format("FINISHED | fib(%s) = %s", val.getNumber(), val.getResult()));
            }else {
                System.out.println(String.format("NOT FINISHED | fib(%s) = %s | FINISHED: ", val.getNumber(), val.getResult()));
            }
        });

    }

    public static class FibonacciRunnable extends Thread{
        private int number;
        private BigInteger result;
        private boolean finished;
        public FibonacciRunnable(int number) {
            this.number = number;
            result = BigInteger.ONE;
        }

        @Override
        public void run() {
            BigInteger prev = BigInteger.ZERO;
            for(int i=0; i<number; i++){
                BigInteger temp = result;
                result = result.add(prev);
                prev = temp;
            }
            finished = true;
        }

        private long getFibonacci(long number) {
            if(number<=1L){
                return 1L;
            }else{
                return getFibonacci(number-1L)+getFibonacci(number-2L);
            }
        }

        public BigInteger getResult() {
            return result;
        }

        public int getNumber() {
            return number;
        }

        public boolean isFinished() {
            return finished;
        }
    }
}
