package com.homey.smarty.smartyhomey;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.homey.smarty.smartyhomey.Managers.NotifierManager;
import com.homey.smarty.smartyhomey.Managers.PopUpManager;
import com.homey.smarty.smartyhomey.Managers.TTSManager;

/**
 * Created by Seiran on 17/07/2017.
 */

public class BathRoomActivity extends FragmentActivity {

    private FABToolbarLayout layout;
    private TTSManager mTtsManager;
    private PopUpManager mPopUpManager;
    private NotifierManager mNotifierManager;

    private ImageView mWashingMachine;
    private ImageView mLaundaryImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.bath_room_activity);

        mPopUpManager = new PopUpManager(this, BathRoomActivity.this);
        mTtsManager = new TTSManager(this);
        mNotifierManager = new NotifierManager(this);

        mWashingMachine = (ImageView) findViewById(R.id.WashingMachineImg);
        mLaundaryImg = (ImageView) findViewById(R.id.LaundryBasket);
        setFab();
    }

    public void warmTowel(View v) {
        ImageView mTowelImg = (ImageView) findViewById(R.id.TowelImg);

        if (mTowelImg.getTag().equals("blue")) {
            mTtsManager.talkLater("Warming Towel");
            mPopUpManager.customInfoToast("Warming Towel");

            mTowelImg.setImageResource(R.drawable.towel_red);
            mTowelImg.setTag("red");
        } else {
            mTtsManager.talkLater("the towel is already warm");
            mPopUpManager.customInfoToast("The towel is already warm");
        }
    }

    public void newTowel(View v) {
        ImageView mTowelImg = (ImageView) findViewById(R.id.TowelImg);

        if (mTowelImg.getTag().equals("red")) {
            mTtsManager.talkLater("New Towel");
            mPopUpManager.customInfoToast("New Towel");

            mTowelImg.setImageResource(R.drawable.towel_blue);
            mTowelImg.setTag("blue");
        } else {
            mTtsManager.talkLater("The towel is new");
            mPopUpManager.customInfoToast("The towel is new");
        }
    }

    public void startWashing(View v) {
        if (mWashingMachine.getTag().equals("ready")) {
            if(mLaundaryImg.getTag().equals("full")) {
                mPopUpManager.makePopUp(v, R.layout.washingmachine_popup);
            } else {
                mTtsManager.talkLater("no clothes to wash");
                mPopUpManager.customInfoToast("No clothes to wash");
                mWashingMachine.setImageResource(R.drawable.washing_machine_green);
                mWashingMachine.setTag("ready");
            }
        } else {
            mTtsManager.talkLater("washing machine already working");
            mPopUpManager.customInfoToast("Washing machine already working");
        }
    }

    public void startWashingActivity(View v) {
        if(mWashingMachine.getTag().equals("ready")) {
            mTtsManager.talkLater("Start washing machine");
            mNotifierManager.newNotification("Smarty Homey New Message", "Starting Washing machine");
            mWashingMachine.setImageResource(R.drawable.washing_machine_red);
            mWashingMachine.setTag("working");
            noLaundry(v);
        } else {
            mTtsManager.talkLater("washing machine already working");
            mPopUpManager.customInfoToast("Washing machine already working");
        }
    }
    public void doneWashing (View v) {
        if(mWashingMachine.getTag().equals("working")) {
            mNotifierManager.newNotification("Smarty Homey New Message", "Washing machine finished");
            mTtsManager.talkLater("washing machine finished");
            mPopUpManager.customInfoToast("washing machine finished");
            mWashingMachine.setImageResource(R.drawable.washing_machine_green);
            mWashingMachine.setTag("ready");
        } else {
            mTtsManager.talkLater("washing machine does not working");
            mPopUpManager.customInfoToast("Washing machine does not working");
            mWashingMachine.setImageResource(R.drawable.washing_machine_green);
            mWashingMachine.setTag("ready");
        }
    }

    public void hasLaundry (View v) {
        if(mLaundaryImg.getTag().equals("empty")){
            mTtsManager.talkLater("new laundry to wash");
            mPopUpManager.customInfoToast("New laundry to wash");
            mLaundaryImg.setImageResource(R.drawable.laundry_basket_full);
            mLaundaryImg.setTag("full");
        }
    }

    public void noLaundry (View v) {
        mLaundaryImg.setImageResource(R.drawable.laundry_basket_empty);

        if(mLaundaryImg.getTag().equals("empty")){
            mTtsManager.talkLater("no laundry to wash");
            mPopUpManager.customInfoToast("No laundry to wash");
        } else {
            mTtsManager.talkLater("No new laundry");
            mPopUpManager.customInfoToast("No new laundry");
        }
        mLaundaryImg.setTag("empty");
    }

    public void startHeatingBath(View v) {
        ImageView mBathTemprature = (ImageView) findViewById(R.id.bathTemprature);
        if(mBathTemprature.getTag().equals("cold")) {
            mTtsManager.talkLater("The bath is cold");
            mPopUpManager.customInfoToast("The bath is cold");
        } else {
            mTtsManager.talkLater("The bath is warm");
            mPopUpManager.customInfoToast("The bath is warm");
        }
    }

    public void heatBath(View v) {
        ImageView mBathTemprature = (ImageView) findViewById(R.id.bathTemprature);

        if (mBathTemprature.getTag().equals("cold")) {
            mTtsManager.talkLater("Heating bath");
            mPopUpManager.customInfoToast("Heating bath");

            mBathTemprature.setImageResource(R.drawable.hot_temperature);
            mBathTemprature.setTag("warm");
        } else {
            mTtsManager.talkLater("Stopping heating bath");
            mPopUpManager.customInfoToast("Stopping heating bath");

            mBathTemprature.setImageResource(R.drawable.cold_temperature);
            mBathTemprature.setTag("cold");
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void washingMachineTimer(View v) {
        TimePicker mTimePicker = (TimePicker) mPopUpManager.getPopupWindow().getContentView().findViewById(R.id.timePickerWashingMachine);
        int hour = mTimePicker.getHour();
        int minute = mTimePicker.getMinute();

        mTtsManager.talkLater("Washing machine will start working at " + String.valueOf(hour) + " " + String.valueOf(minute));
        if(minute < 10)
            mPopUpManager.customInfoToast("Washing machine will start working at " + String.valueOf(hour) + ":0" + String.valueOf(minute));
        else
            mPopUpManager.customInfoToast("Washing machine will start working at " + String.valueOf(hour) + ":" + String.valueOf(minute));
        mPopUpManager.getPopupWindow().dismiss();
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
