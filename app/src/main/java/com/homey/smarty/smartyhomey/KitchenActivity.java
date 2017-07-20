package com.homey.smarty.smartyhomey;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.homey.smarty.smartyhomey.Managers.NotifierManager;
import com.homey.smarty.smartyhomey.Managers.PopUpManager;
import com.homey.smarty.smartyhomey.Managers.TTSManager;

/**
 * Created by Seiran on 17/07/2017.
 */

public class KitchenActivity extends FragmentActivity {
    public FABToolbarLayout layout;
    public TTSManager tts;
    public PopUpManager pm;
    public NotifierManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_activity);
        pm = new PopUpManager(this, KitchenActivity.this);

        tts = new TTSManager(this);

        nm = new NotifierManager(this);

        setFab();
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

    public void waterGlassTap (View v){

        ImageView wg = (ImageView) findViewById(R.id.water_glass);
        wg.setImageResource(R.drawable.water_glass_empty);

        tts.talkLater("Water Drinked");




    }


    public void drinkWaterTap(View v) {
        ImageView wg = (ImageView) findViewById(R.id.water_glass);
        wg.setImageResource(R.drawable.waterglass);
        wg.setVisibility(View.VISIBLE);


        tts.talkLater("Water Served");


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

    public void sendToStore(View v){

        tts.talkLater("Groceries list sent to shop");




    }

    public void shopingCartTap(View v){

        pm.makePopUp(v,R.layout.shopinglist_popup);

    }

    public void frideTap (View v){
        ImageView wg = (ImageView) findViewById(R.id.shopingCart);
        wg.setVisibility(View.VISIBLE);

        tts.talkLater("Shopping cart ready");

    }
}
