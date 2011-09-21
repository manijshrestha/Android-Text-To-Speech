package com.manijshrestha.texttospeech;

import android.app.Activity;
import android.os.Bundle;

/**
 * Sample application to show text to speech Example.
 * User can type in any text and click on Speak and 
 * the device should Speak up.
 * @author manijshrestha
 *
 */
public class TextToSpeechActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}