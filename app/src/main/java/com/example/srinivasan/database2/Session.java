package com.example.srinivasan.database2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SRINIVASAN on 4/17/2017.
 */

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    public Session(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedinmode",loggedIn);
        editor.commit();
    }
    public boolean loggedIn(){
        return sharedPreferences.getBoolean("loggedinmode",false);
    }
}
