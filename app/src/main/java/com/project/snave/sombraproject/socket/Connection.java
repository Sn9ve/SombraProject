package com.project.snave.sombraproject.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Snave on 29/12/2016.
 */
public class Connection extends Thread{
    private String IP;
    private short PORT;
    public static Socket socket;

    public Transmit TRANSMISSION;
    public Receive RECEPTION_THREAD;

    private DataOutputStream out = null;
    private BufferedReader in = null;
    Handler handler;

    public static final String X = "X";
    public static final String Y = "Y";

    private static Connection instance = new Connection();

    private Connection() {
        this.socket = null;
        this.TRANSMISSION = new Transmit();
        this.IP = "192.168.0.20";
        this.PORT = 23;
    }

    public static Connection getInstance() {
        return instance;
    }

    public void run() {
        try {
            Looper.prepare();

            System.out.println("Demande de connexion");
            socket = new Socket(IP, PORT);
            // Si le message s'affiche c'est que je suis connecté
            System.out.println("Connexion établie avec le serveur");

            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            /** Thread de transmission des données du joystick **/

            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Bundle bundle = msg.getData();
                    int action = msg.what;
                    TRANSMISSION.processQueue(out, bundle.getByte(X), bundle.getByte(Y));
                }
            };
            Looper.loop();

        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse " + socket.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port " + socket.getLocalPort());
        }
    }

    public void addToSendQueue(byte x, byte y){
        TRANSMISSION.addToSendQueue(handler, x, y);
    }
}
