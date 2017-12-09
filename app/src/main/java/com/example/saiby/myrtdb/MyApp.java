package com.example.saiby.myrtdb;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SAIBY on 11/30/2017.
 */

public class MyApp extends android.app.Application{

        @Override
        public void onCreate() {
            super.onCreate();
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
}
