package com.project.snave.sombraproject.activities;

import android.app.Activity;
import android.os.Bundle;

import com.jmedeisis.bugstick.Joystick;
import com.jmedeisis.bugstick.JoystickListener;

import com.project.snave.sombraproject.R;
import com.project.snave.sombraproject.socket.ClientSocket;
import com.project.snave.sombraproject.socket.Transmit;

/**
 * Created by Snave on 29/12/2016.
 */
public class ControlActivity extends Activity {
    private Joystick hackJoystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        //Joysticks
        hackJoystick = (Joystick) findViewById(R.id.hackJoystick);
        hackJoystick.setJoystickListener(setupJoystickListener());
    }

    //Joysticks
    private JoystickListener setupJoystickListener(){
        return new JoystickListener(){
            @Override
            public void onDown(){

            }

            @Override
            public void onDrag(float degrees, float offset){
                offset *= 100.0;
                byte x = (byte) (Math.cos(Math.toRadians(degrees)) * offset);
                byte y = (byte) (Math.sin(Math.toRadians(degrees)) * offset);

                //ClientSocket.getInstance().addToSendQueue(x, y);
            }

            @Override
            public void onUp(){
                //ClientSocket.getInstance().addToSendQueue((byte) 0, (byte) 0);
            }
        };
    }
}
