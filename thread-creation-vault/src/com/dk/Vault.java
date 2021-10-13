package com.dk;

import java.util.LinkedList;

public class Vault {
    public static final int MAX_PASSWORD=10000;
    private int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean isCorrectPassword(int guess){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return password==guess;
    }
}
