package com.example.musicmachine;


import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import androidx.annotation.NonNull;

public class PlayerHandler extends Handler {
    private final PlayerService mPlayerService;
    public PlayerHandler(PlayerService playerService){
        mPlayerService =  playerService;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.arg1){
            case 0: //play
                mPlayerService.play();

                break;

            case 1:
                mPlayerService.pause();
                break;
            case 2:
                int isPlaying = mPlayerService.isPlaying() ? 1:0;
                Message message = Message.obtain();
                message.arg1 =  isPlaying;
                if(msg.arg2 == 1){
                    message.arg2 = 1;
                }
                message.replyTo = mPlayerService.mMessenger;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
