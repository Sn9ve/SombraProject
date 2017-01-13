package com.project.snave.sombraproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.project.snave.sombraproject.R;
import com.project.snave.sombraproject.socket.Connection;

public class MainActivity extends Activity {

    private ImageButton btnControl, btnSpeech ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connection.getInstance().start();

        btnControl = (ImageButton) findViewById(R.id.btnControl);
        btnSpeech = (ImageButton) findViewById(R.id.btnSpeech);

        btnControl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ControlActivity.class);
                startActivity(intent);
            }
        });

        btnSpeech .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommunicationActivity.class);
                startActivity(intent);
            }
        });
    }
}
