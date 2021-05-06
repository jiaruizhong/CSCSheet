package com.example.cscheatsheet;

import android.app.Application;

import com.example.cscheatsheet.fragments.flashcards.FlashcardDatabase;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    private static ParseApplication instance;



    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        // Register your parse modes
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HbZcbn7T9NRHfR9vt64jpEOkD5lMrA3GmV1GzAHT")
                .clientKey("c59Wh2fleRW1zyKbd1JRZDtX7KyqKgv7tLF1TRAQ")
                .server("https://parseapi.back4app.com")
                .build()
        );
        FlashcardDatabase.getInstance();
    }
    public static ParseApplication getInstances(){
        return instance;
    }
}
