package com.dk;

import java.math.BigInteger;

public class Calculate2PowMain {
    public static void main(String[] args) {
        Calculate2Pow calculate2Pow = new Calculate2Pow();

        System.out.println(String.format("Result: %s", calculate2Pow.calculateResult(BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(3), BigInteger.valueOf(2))));
    }
}
