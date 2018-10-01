package com.example.user22.rmserver;

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 4/19/2018 at 12:34 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 4/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/


import android.util.Log;

public class AppLog {
    private static String TAG = AppLog.class.getName();

    public static void d(String mag){
        d(TAG,mag);
    }
    public static void d(String tag,String msg){
        Log.d(tag, msg);
    }

}
