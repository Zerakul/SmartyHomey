package com.homey.smarty.smartyhomey;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.homey.smarty.smartyhomey.Managers.PopUpManager;
import com.homey.smarty.smartyhomey.Managers.TTSManager;

/**
 * Created by Seiran on 17/07/2017.
 */

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
