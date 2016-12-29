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

import java.util.List;
import java.util.Locale;

/**
 * Created by Snave on 26/12/2016.
 */
public class CommunicationActivity extends Activity {

    private ImageButton btnMic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        SpeechProcessing.initiatePattern();

        btnMic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displaySpeechRecognizer();
                //myHandler.postDelayed(UpdateSongTime, 100);
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
            SpeechProcessing.processAction(results.get(0).toString());
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
