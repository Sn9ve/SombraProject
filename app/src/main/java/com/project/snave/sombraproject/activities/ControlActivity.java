package com.project.snave.sombraproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jmedeisis.bugstick.Joystick;
import com.jmedeisis.bugstick.JoystickListener;

import com.project.snave.sombraproject.R;
import com.project.snave.sombraproject.socket.Connection;

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
                int x = (int) (Math.cos(Math.toRadians(degrees)) * offset);
                int y = (int) (Math.sin(Math.toRadians(degrees)) * offset);
                Log.i("IEM : ", " ------> S0MBR4 : " + x + " " + y );

                Connection.getInstance().addToSendQueue(x, y);
            }

            @Override
            public void onUp(){
                Connection.getInstance().addToSendQueue(0, 0);
            }
        };
    }
}
