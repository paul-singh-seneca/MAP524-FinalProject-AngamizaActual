package com.osepoo.angamizaactual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_ID = "keyid";
    private static final String KEY_EMPLOYEEID = "keyemployeeid";
    private static final String KEY_FIRSTNAME = "keyfirstname";
    private static final String KEY_LASTNAME = "keylastname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_SIGNINNAME = "keysigninname";
    private static final String KEY_PHONE_NO = "keyphone_no";
    private static final String KEY_ROLE = "keyrole";
    private static final String KEY_STATUS = "keystatus";


    private static SharedPrefManager mInstance;
    private static Context ctx;


    private SharedPrefManager(Context context) { ctx = context; }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_EMPLOYEEID, user.getEmpid());
        editor.putString(KEY_FIRSTNAME, user.getFirstname());
        editor.putString(KEY_LASTNAME, user.getLastname());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_SIGNINNAME, user.getSigninname());
        editor.putString(KEY_PHONE_NO, user.getPhone_no());
        editor.putString(KEY_ROLE, user.getRole());
        editor.putString(KEY_STATUS, user.getStatus());

        editor.apply();
    }


    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SIGNINNAME, null) != null && sharedPreferences.getString(KEY_EMPLOYEEID, null) != null
                && sharedPreferences.getString(KEY_STATUS, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_EMPLOYEEID, null),
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_LASTNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_SIGNINNAME, null),
                sharedPreferences.getString(KEY_PHONE_NO, null),
                sharedPreferences.getString(KEY_ROLE, null),
                sharedPreferences.getString(KEY_STATUS, null)

        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        ctx.startActivity(new Intent(ctx, LoginActivity.class));

    }


}