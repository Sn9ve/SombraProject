package com.project.snave.sombraproject.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Snave on 07/01/2017.
 */
public class Connection implements Runnable {

    private Socket socket;
    public static Thread TRANSMISSION_THREAD, RECEPTION_THREAD;
    private DataOutputStream out = null;
    private BufferedReader in = null;

    public Connection(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            TRANSMISSION_THREAD = new Thread(new Transmit(out));
            TRANSMISSION_THREAD.start();
            RECEPTION_THREAD = new Thread(new Receive(in));
            RECEPTION_THREAD.start();

        } catch (IOException e) {
            System.err.println("Server does not respond ");
        }
    }
}
