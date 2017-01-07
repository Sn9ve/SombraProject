package com.project.snave.sombraproject.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.project.snave.sombraproject.R;
import com.project.snave.sombraproject.SpeechProcessing;
import com.project.snave.sombraproject.socket.ClientSocket;

import java.util.List;
import java.util.Locale;

/**
 * Created by Snave on 26/12/2016.
 */
public class CommunicationActivity extends Activity {

    private ImageButton btnMic;
    private static byte[] stop, analyze, front, back, right, left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        SpeechProcessing.initiatePattern();
        initiateParameter();

        btnMic = (ImageButton) findViewById(R.id.mic);
        btnMic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displaySpeechRecognizer();
            }
        });
    }

    private static final int SPEECH_REQUEST_CODE = 100;

    /**
     * Showing google speech input dialog
     * */
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.speech_prompt));

        // Start the activity, the intent will be populated with the speech text
        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Toast.makeText(getBaseContext(), spokenText, Toast.LENGTH_SHORT).show();
            processAction(spokenText);
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void processAction(String voice) {
        SpeechProcessing.processing(voice);
        switch (SpeechProcessing.resultOrder){
            case "stop":
                //do something in this case, add the function to stop Sombra
                ClientSocket.getInstance().addToSendQueue(stop[0], stop[1]);
                Toast.makeText(getBaseContext(), "stop", Toast.LENGTH_LONG).show();
                break;
            case "analyze":
                //add the function to start analyze
                //ClientSocket.getInstance().addToSendQueue(x, y);
                Toast.makeText(getBaseContext(), "analyse", Toast.LENGTH_LONG).show();
                break;
            case "front":
                //add the function to move in front
                ClientSocket.getInstance().addToSendQueue(front[0], front[1]);
                Toast.makeText(getBaseContext(), "front", Toast.LENGTH_LONG).show();
                break;
            case "back":
                //add the function to move backward
                ClientSocket.getInstance().addToSendQueue(back[0], back[1]);
                Toast.makeText(getBaseContext(), "back", Toast.LENGTH_LONG).show();
                break;
            case "right":
                //add the function to move in right
                ClientSocket.getInstance().addToSendQueue(right[0], right[1]);
                Toast.makeText(getBaseContext(), "right", Toast.LENGTH_LONG).show();
                break;
            case "left":
                //add the function to move in left
                ClientSocket.getInstance().addToSendQueue(left[0], left[1]);
                Toast.makeText(getBaseContext(), "left", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void initiateParameter(){
        stop = new byte[2];
        stop[0] = 0;
        stop[1] = 0;

        analyze = stop;

        front = new byte[2];
        front[0] = 0;
        front[1] = 100;

        back = new byte[2];
        back[0] = 0;
        back[1] = -100;

        right = new byte[2];
        right[0] = 50;
        right[1] = 100;

        left = new byte[2];
        left[0] = -50;
        left[1] = 100;
    }
}
