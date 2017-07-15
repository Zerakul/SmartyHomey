package com.homey.smarty.smartyhomey.Managers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;


import com.homey.smarty.smartyhomey.ConstantValues;

import java.util.HashMap;
import java.util.Locale;

/**
 * Text To Speech Manager/Wrapper class
 */
public class TTSManager {


    private Context appCon;
    private TextToSpeech tts;
    private SharedPreferences sharedPreferences;

    /**
     * CTOR
     * @param applicationContext
     */
    public TTSManager(Context applicationContext) {
        this.appCon = applicationContext;

        tts = new TextToSpeech(applicationContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);

    }

    /**
     * Interrupts the current utterance (whether played or rendered to file) and discards other
     * utterances in the queue.
     */
    public void stop() {

        tts.stop();
    }

    /**
     * Releases the resources used by the TextToSpeech engine.
     * It is good practice for instance to call this method in the onDestroy() method of an Activity
     * so the TextToSpeech engine can be cleanly stopped.
     */
    public void shutDown() {

        tts.shutdown();
    }


    /**
     * TTS out flush the que
     * @param string
     */
    public void talk(String string) {

        if(sharedPreferences.getBoolean(ConstantValues.UI_VOICE_SWITCH,false))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(string, TextToSpeech.QUEUE_FLUSH);
        } else {
            ttsUnder20(string ,TextToSpeech.QUEUE_FLUSH );
        }


    }

    /**
     * TTS out add to the que and flush later.
     * @param s
     */
    public void talkLater(String s){

        if(sharedPreferences.getBoolean(ConstantValues.UI_VOICE_SWITCH,false))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(s, TextToSpeech.QUEUE_ADD);
        } else {
            ttsUnder20(s ,TextToSpeech.QUEUE_ADD );
        }


    }

    /**
     * Mehtod for high api's
     * @param string
     * @param que
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String string ,int que) {
        String utteranceId=this.hashCode() + "";
        tts.speak(string, que, null, utteranceId);

    }


    /**
     * Depricated method for low apis'
     * @param string
     * @param queue
     */
    @SuppressWarnings("deprecation")
    private void ttsUnder20(String string, int queue) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");

        tts.speak(string, queue, map);
    }
}
