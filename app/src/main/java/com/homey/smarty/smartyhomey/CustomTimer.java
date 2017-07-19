package com.homey.smarty.smartyhomey;


public class CustomTimer {

  private long startTime = 0;
    private long endTime = 0;
    private long totalTime = 0;
    private boolean timerStarded = false;


    public CustomTimer(){


    }


    public long startTimer (){

        startTime = System.currentTimeMillis();
        timerStarded = true;

        return startTime;
    }

    public long stopTimer(){
        timerStarded = false;
        endTime = System.currentTimeMillis();
        totalTime = totalTime + (endTime-startTime);

        return totalTime;
    }


    public void resetTimer(){
        timerStarded = false;
        startTime = 0;
        endTime = 0;
        totalTime = 0;

    }


    public long getCurrentTimer(){
        if(timerStarded)
        return totalTime + (System.currentTimeMillis() - startTime);

        else
            return totalTime;
    }

    public long getTotalTime(){


        return totalTime;
    }






}
