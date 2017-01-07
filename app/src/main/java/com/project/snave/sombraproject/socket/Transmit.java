package com.project.snave.sombraproject.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Snave on 07/01/2017.
 */
public class Transmit extends Thread {

    private Handler handler;
    private DataOutputStream out;
    public static final String X = "X";
    public static final String Y = "Y";

    public Transmit(DataOutputStream out) {
        this.out = out;
    }

    public void run() {
        Looper.prepare();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                int action = msg.what;
                processQueue(bundle.getByte(X), bundle.getByte(Y));
            }
        };
        Looper.loop();
    }

    public void addToSendQueue(byte x, byte y){
        if(handler != null){
            Message msg = handler.obtainMessage();
            //msg.what = SEND;
            Bundle bundle = new Bundle();
            bundle.putByte(X, x);
            bundle.putByte(Y, y);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    }

    private void processQueue(byte x, byte y){
        try {
            out.writeByte(x);
            out.writeByte(y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
