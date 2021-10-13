package com.dk;

import com.dk.hacker.AscendingHackerThread;
import com.dk.hacker.DescendingHackerThread;
import com.dk.secure.Police;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(Vault.MAX_PASSWORD));
        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new Police());

        for(Thread thread:threads){
            thread.start();
        }
    }
}
