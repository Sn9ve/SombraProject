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

    public void addToSendQueue(Handler handler, byte[] buff){
        if(handler != null){
            Message msg = handler.obtainMessage();
            msg.what = Connection.SEND;
            Bundle bundle = new Bundle();
            bundle.putByteArray(Connection.DATA, buff);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    }
}
