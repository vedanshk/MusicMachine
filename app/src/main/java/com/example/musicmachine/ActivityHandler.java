package com.example.musicmachine;


import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import androidx.annotation.NonNull;

public class ActivityHandler extends Handler {
    private MainActivity mainActivity;

    public ActivityHandler(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    @Override
    public void handleMessage(@NonNull Message msg) {
        if(msg.arg1 == 0){



            // Music is not playing

            if(msg.arg2 == 1){
                mainActivity.changePlayText("Play");
            }else{
                Message message = Message.obtain();
                message.arg1 = 0;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            // play the music
            // change play button to say "pause


            mainActivity.changePlayText("Pause");
        }else if (msg.arg1 ==1){
            if( msg.arg2 == 1){
                mainActivity.changePlayText("Pause");
            }else{
                Message message = Message.obtain();
                message.arg1 = 1;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

                mainActivity.changePlayText("Play");
            }


        }

    }
}
