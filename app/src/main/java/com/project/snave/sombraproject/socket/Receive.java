package com.project.snave.sombraproject.socket;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Snave on 07/01/2017.
 */
public class Receive extends Thread {
    private BufferedReader in;
    private String message = null;

    public Receive(BufferedReader in){
        this.in = in;
    }

    public void run() {
        while(true){
            try {
                message = in.readLine();
                System.out.println("Le serveur vous dit :" + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
