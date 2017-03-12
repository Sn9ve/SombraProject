package com.project.snave.sombraproject.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Snave on 29/12/2016.
 */
public class Connection extends Thread{
    public static String IP2;
    public static String IP;
    private int PORT;
    public static DatagramSocket UDPsock;
    DatagramPacket dp;

    public Transmit TRANSMITTER;
    public Receive RECEIVER;

    private DataOutputStream out = null;
    private BufferedReader in = null;
    Handler handler;

    public static final String DATA = "DATA";
    public static final int SEND = 1;

    private static Connection instance = new Connection();

    private Connection() {
        this.UDPsock = null;
        this.TRANSMITTER = new Transmit();
        this.IP = "192.168.0.25";
        this.PORT = 999;
    }

    public static Connection getInstance() {
        return instance;
    }

    public void run() {
        try {
            Looper.prepare();

            System.out.println("Demande de connexion");
            UDPsock = new DatagramSocket();

            // Si le message s'affiche c'est que je suis connecté
            System.out.println("Connexion établie avec le serveur");

            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Bundle bundle = msg.getData();
                    int action = msg.what;
                    processQueue(bundle.getByteArray(DATA));
                }
            };
            Looper.loop();

        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port " + UDPsock.getLocalPort());
        }
    }

    public void addToSendQueue(int x, int y){
        TRANSMITTER.addToSendQueue(handler, ByteBuffer.allocate(8).putInt(x).putInt(y).order(ByteOrder.BIG_ENDIAN).array());
    }

    public void processQueue(byte[] buff){
        try {
            dp = new DatagramPacket(buff, buff.length, InetAddress.getByName(IP), PORT);
            UDPsock.send(dp);
            Log.i("IEM : ", " buffer length --> " + buff.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
