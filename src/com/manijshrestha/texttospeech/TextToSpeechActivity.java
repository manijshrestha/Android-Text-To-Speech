package com.manijshrestha.texttospeech;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Sample application to show text to speech Example.
 * User can type in any text and click on Speak and 
 * the device should Speak up.
 * @author manijshrestha
 *
 */
public class TextToSpeechActivity extends Activity implements OnInitListener{
    
	/**
	 * UI Components
	 */
	private EditText mEditText;
	private Button mBtnSpeak;
	
	private TextToSpeech mTts;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mEditText = (EditText) findViewById(R.id.wordToSpeak);
        mBtnSpeak = (Button) findViewById(R.id.btnSpeak);
        
        mTts = new TextToSpeech(this, this);
        
        mBtnSpeak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               String textToSpeak = mEditText.getText().toString();
               speak(textToSpeak);
            }
        });
    }

    private void speak(String textToSpeak){
       mTts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);	
    }
    
	@Override
	public void onInit(int status) {
		
		// status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.US);
            
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
               // Lanuage data is missing or the language is not supported.
                Log.e("404","Language is not available.");
            }
        } else {
            // Initialization failed.
            Log.e("404", "Could not initialize TextToSpeech.");
            // May be its not installed so we prompt it to be installed
            Intent installIntent = new Intent();
            installIntent.setAction(
                TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
        }
		
	}
	
	@Override
    public void onDestroy() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
}