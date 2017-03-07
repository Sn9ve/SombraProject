package com.project.snave.sombraproject.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Snave on 07/01/2017.
 */
public class Transmit {
    public Transmit() {

    }

    public void addToSendQueue(Handler handler, byte x, byte y){
        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.what = Connection.SEND;
            Bundle bundle = new Bundle();
            bundle.putByte(Connection.X, x);
            bundle.putByte(Connection.Y, y);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    }

    public void processQueue(DataOutputStream out, byte header, byte x, byte y){
        try {
            out.writeByte(header);
            out.writeByte(x);
            out.writeByte(y);
            Log.i("IEM : " + header, " ------> S0MBR4 : " + x + " " + y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
