package com.homey.smarty.smartyhomey;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;

/**
 * Created by Seiran on 17/07/2017.
 */

public class BathRoomActivity extends FragmentActivity {
    public FABToolbarLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bath_room_activity);
        setFab();
    }


    public void doSome (View v){



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
}
