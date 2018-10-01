package com.example.user22.rmserver;

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 4/19/2018 at 12:21 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 4/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/


import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class App extends MultiDexApplication {
    private static Context context;
    public static Context getContext(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
