package com.example.musicmachine;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class DownloadIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadIntentService(String name) {
        super("DownloadIntent Service");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
