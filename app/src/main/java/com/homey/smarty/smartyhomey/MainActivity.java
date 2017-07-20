package com.homey.smarty.smartyhomey;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.homey.smarty.smartyhomey.Managers.NotifierManager;
import com.homey.smarty.smartyhomey.Managers.TTSManager;
import com.homey.smarty.smartyhomey.Settings.SettingsActivity;


public class MainActivity extends FragmentActivity {
    //TODO add settings screen (Seiran will do)
    //TODO shopping list
    //TODO Washing machine Windows
    //TODO actions to the Icons
    //TODO code cleanup
    //TODO kitchen living room settings activity (Seiran)


    public FABToolbarLayout layout;
    public TTSManager tts ;
    public NotifierManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        tts = new TTSManager(this);
        nm = new NotifierManager(this);

        setFab();
    }

    private void setFab() {
        View fab;
        layout = (FABToolbarLayout) findViewById(R.id.fabtoolbar);
        fab = findViewById(R.id.fabtoolbar_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.show();
            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.hide();
                return false;
            }
        });
    }


    public void onKitchenBtnTap(View V) {
        Intent i = new Intent(MainActivity.this, KitchenActivity.class);
        startActivity(i);
    }

    public void onBathroomBtnTap(View V) {
        Intent i = new Intent(MainActivity.this, BathRoomActivity.class);
        startActivity(i);
    }

    public void onSleepRoomBtnTap(View V) {
        Intent i = new Intent(MainActivity.this, SleepRoomActivity.class);
        startActivity(i);
    }


    public void onHomeLocTap (View v){
        tts.talkLater("Going Home.  Starting tracking procedure");


    }

    public void onGroceryCartTap (View v){
        tts.talkLater("Groceries arrived");
        nm.newNotification("Groceries","New Groceries Arrived ");

        ImageView gr = (ImageView) findViewById(R.id.grocery);

        gr.setVisibility(View.VISIBLE);


    }


    public void onGroceryTap (View v){
        tts.talkLater("Groceries accepted");


        ImageView gr = (ImageView) findViewById(R.id.grocery);

        gr.setVisibility(View.GONE);


    }


    public void onSettingsTap (View v){

        Intent myIntent = new Intent(this, SettingsActivity.class);
        MainActivity.this.startActivity(myIntent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.shutDown();
    }
}
