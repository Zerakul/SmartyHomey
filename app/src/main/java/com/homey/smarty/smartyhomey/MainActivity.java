package com.homey.smarty.smartyhomey;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;



public class MainActivity extends FragmentActivity {
    //TODO add icons to fab with kitchen/bathroom and sleep room
    //TODO add settings screen (Seiran will do)
    //TODO shopping list
    //TODO notification class (Seiran will do)
    //TODO actions to the Icons
    //TODO timer class (Seiran will do)
    //TODO code cleanup
    //TODO change popup colors
    //TODO TTS on pause (shutdown)
    //TODO Bathrom/SleepRoom (DANA)
    //TODO kitchen living room settings activity (Seiran)


    public FABToolbarLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    public void onKitchenBtnTap(View V){

        Intent i = new Intent(MainActivity.this, KitchenActivity.class);
        startActivity(i);


    }

    public void onBathroomBtnTap(View V){

        Intent i = new Intent(MainActivity.this, BathRoomActivity.class);
        startActivity(i);


    }

    public void onSleepRoomBtnTap(View V){

        Intent i = new Intent(MainActivity.this, SleepRoomActivity.class);
        startActivity(i);


    }

}
