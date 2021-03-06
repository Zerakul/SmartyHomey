package com.homey.smarty.smartyhomey.Managers;


import android.app.Activity;
import android.content.Context;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.homey.smarty.smartyhomey.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PopUpManager {

    private LayoutInflater layoutInflater;
    private Context appContext;
    private Activity mAcivity;
    private PopupWindow popupWindow;



    /**
     * Constructor
     * @param applicationContext applicaiton who will call the popup
     * @param mainActivity the activity that will calll the popups
     */
    public PopUpManager(Context applicationContext, Activity mainActivity) {
        this.appContext = applicationContext;
        this.mAcivity = mainActivity;
    }


    /**
     *Make window Poppup and showto user
     * @param v the view who initiated the popup
     * @param layout the layout of the popup

     */
    public void makePopUp(View v, int layout) {

        int[] loc_int = new int[2];
        v.getLocationOnScreen(loc_int);
        layoutInflater = (LayoutInflater) appContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup containter = (ViewGroup) layoutInflater.inflate(layout, null);
        popupWindow = new PopupWindow(containter, 420, 420, true);
        popupWindow.showAtLocation(v,Gravity.NO_GRAVITY,400,200);

        containter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }






    /**
     * Custom information toast
     * @param text the text should be represented
     */
    public void customInfoToast(String text){


        //Creating the LayoutInflater instance
        LayoutInflater li = (LayoutInflater) appContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        //Getting the View object as defined in the custom_info_toast.xml file
        View layout = li.inflate(R.layout.custom_info_toast, (ViewGroup) mAcivity.findViewById(R.id.infoToast));

        Toast toast = new Toast(appContext);
       TextView iText= (TextView) layout.findViewById(R.id.infoToastTxt);
        iText.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.NO_GRAVITY, 0, 250);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }



    /**
     * Custom error toast
     * @param text the text should be represented
     */
    public void customErrorToast(String text){


        //Creating the LayoutInflater instance
        LayoutInflater li = (LayoutInflater) appContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        //Getting the View object as defined in the custom_error_toast.xml file
        View layout = li.inflate(R.layout.custom_error_toast, (ViewGroup) mAcivity.findViewById(R.id.errorToast));

        Toast toast = new Toast(appContext);
        TextView iText= (TextView) layout.findViewById(R.id.errorToastTxt);

        iText.setText(text);
        toast.setDuration(Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.LEFT, 0, 270);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }


    /**
     * Getter for the popupWindow
     * @return Currently active popup window
     */
    public PopupWindow getPopupWindow() {
        return popupWindow;
    }


}
