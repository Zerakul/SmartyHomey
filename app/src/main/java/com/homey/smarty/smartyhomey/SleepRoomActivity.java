package com.homey.smarty.smartyhomey;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.homey.smarty.smartyhomey.Managers.PopUpManager;
import com.homey.smarty.smartyhomey.Managers.TTSManager;



public class SleepRoomActivity extends FragmentActivity {

    private FABToolbarLayout layout;
    private TTSManager mTtsManager;
    private PopUpManager mPopUpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.sleep_room_activity);

        mPopUpManager = new PopUpManager(this, SleepRoomActivity.this);
        mTtsManager = new TTSManager(this);

        setFab();
    }

    public void pijamaTaken(View v) {
        ImageView mPijaamaImg = (ImageView) findViewById(R.id.pijamaImg);
        if(mPijaamaImg.getVisibility() == View.VISIBLE) {
            mTtsManager.talkLater("The pijama was taken");
            mPopUpManager.customInfoToast("The pijama was taken");
            mPijaamaImg.setVisibility(View.INVISIBLE);
        } else {
            mTtsManager.talkLater("Ask Closet to choose pijama");
            mPopUpManager.customInfoToast("Ask Closet to choose pijama");
        }
    }


    public void bedTempreatureStatus(View v) {
        ImageView mBedImg = (ImageView) findViewById(R.id.bedTempreatureImg);
        if(mBedImg.getTag().equals("cold")) {
            mTtsManager.talkLater("The bed is cold");
            mPopUpManager.customInfoToast("The bed is cold");
        } else {
            mTtsManager.talkLater("The bed is warm");
            mPopUpManager.customInfoToast("The bed is warm");
        }
    }

    public void washingPijama(View v) {
        ImageView mPijaamaImg = (ImageView) findViewById(R.id.pijamaImg);
        mTtsManager.talkLater("Sending pijama to washing machine");
        mPopUpManager.customInfoToast("Sending pijama to washing machine");
        mPijaamaImg.setVisibility(View.INVISIBLE);
    }

    public void selectingPijama(View v) {
        ImageView mPijaamaImg = (ImageView) findViewById(R.id.pijamaImg);

        mTtsManager.talkLater("Choosing pijama");
        mPopUpManager.customInfoToast("Choosing pijama");

        mPijaamaImg.setVisibility(View.VISIBLE);
    }

    public void wearingPijama(View v) {
        ImageView mPijaamaImg = (ImageView) findViewById(R.id.pijamaImg);
        if(mPijaamaImg.getVisibility() == View.INVISIBLE) {
            mTtsManager.talkLater("No pijama to wear, ask closet to choose");
            mPopUpManager.customInfoToast("No pijama to wear, ask closet to choose");
        } else {
            mTtsManager.talkLater("Wearing pijama");
            mPopUpManager.customInfoToast("Wearing pijama");
            mPijaamaImg.setVisibility(View.INVISIBLE);
        }
    }

    public void warmingBed(View v) {
        ImageView mBedImg = (ImageView) findViewById(R.id.bedTempreatureImg);
        if(mBedImg.getTag().equals("cold")) {
            mTtsManager.talkLater("Warming bed");
            mPopUpManager.customInfoToast("Warming bed");

            mBedImg.setImageResource(R.drawable.hot_temperature);
            mBedImg.setTag("warm");
        } else {
            mTtsManager.talkLater("stop warming bed");
            mPopUpManager.customInfoToast("stop warming bed");

            mBedImg.setImageResource(R.drawable.cold_temperature);
            mBedImg.setTag("cold");
        }
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

    @Override
    protected void onStop() {
        super.onStop();
        mTtsManager.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTtsManager.shutDown();

    }
}
