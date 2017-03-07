package com.project.snave.sombraproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.snave.sombraproject.R;
import com.project.snave.sombraproject.socket.Connection;

public class LaunchingActivity extends Activity {
    private ImageButton btnConfirm;
    private EditText edit_IP, edit_IP2, edit_IP3, edit_IP4;

    int blue = Color.parseColor("#04E0FF");
    int pink = Color.parseColor("#F56AFF");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        btnConfirm = (ImageButton) findViewById(R.id.btnConfirm);

        edit_IP = (EditText) findViewById(R.id.edit_IP);
        edit_IP2 = (EditText) findViewById(R.id.edit_IP2);
        edit_IP3 = (EditText) findViewById(R.id.edit_IP3);
        edit_IP4 = (EditText) findViewById(R.id.edit_IP4);

        edit_IP.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!edit_IP.getText().toString().isEmpty()) {
                    int ip = Integer.parseInt(edit_IP.getText().toString());
                    if (ip >= 0 && ip < 256) {
                        edit_IP.setBackgroundColor(blue);
                    } else {
                        edit_IP.setBackgroundColor(pink);
                    }
                }
            }
        });

        edit_IP2.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!edit_IP2.getText().toString().isEmpty()){
                    int ip = Integer.parseInt(edit_IP2.getText().toString());
                    if(ip >= 0 && ip < 256 ){
                        edit_IP2.setBackgroundColor(blue);
                    } else {
                        edit_IP2.setBackgroundColor(pink);
                    }
                }
            }
        });

        edit_IP3.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!edit_IP3.getText().toString().isEmpty()) {
                    int ip = Integer.parseInt(edit_IP3.getText().toString());
                    if (ip >= 0 && ip < 256) {
                        edit_IP3.setBackgroundColor(blue);
                    } else {
                        edit_IP3.setBackgroundColor(pink);
                    }
                }
            }
        });

        edit_IP4.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!edit_IP4.getText().toString().isEmpty()) {
                    int ip = Integer.parseInt(edit_IP4.getText().toString());
                    if (ip >= 0 && ip < 256) {
                        edit_IP4.setBackgroundColor(blue);
                    } else {
                        edit_IP4.setBackgroundColor(pink);
                    }
                }
            }
        });

        btnConfirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection.IP2 = edit_IP.getText() + "." + edit_IP2.getText() + "." + edit_IP3.getText() + "." + edit_IP4.getText();
                Intent intent = new Intent(LaunchingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
