package com.osepoo.angamizaactual;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TransitionButton transitionButton;
    EditText editTextmail, editTextpass;

    boolean isSuccessful = false;

    CoordinatorLayout coordinatorLayout;

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.ghaliclassic));
        }

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, DrawerActivity.class));
        }

        transitionButton = findViewById(R.id.transition_button);
        editTextmail = findViewById(R.id.editTextemail);
        editTextpass = findViewById(R.id.editTextpassword);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.logincoordinator);

        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the loading animation when the user tap the button
                transitionButton.startAnimation();

                userLogin();

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // Choose a stop animation if your call was succesful or not
                        if (isSuccessful) {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                                @Override
                                public void onAnimationStopEnd() {
                                    Intent intent = new Intent(getBaseContext(), DrawerActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 2000);




            }
        });
    }
    private void userLogin() {


        final String signinname = editTextmail.getText().toString().trim();
        final String empid = editTextpass.getText().toString().trim();

        if(TextUtils.isEmpty(signinname)){
            editTextmail.setError("Please enter your username");
            editTextmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(empid)) {
            editTextpass.setError("Please enter your password");
            editTextpass.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                //FancyToast.makeText(getApplicationContext(),obj.getString("message"),FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();

                                Snackbar snackbar = Snackbar.make(coordinatorLayout, obj.getString("message"), Snackbar.LENGTH_SHORT);
                                        /*.setAction("YES",
                                                // If the Undo button
                                                // is pressed, show
                                                // the message using Toast
                                                new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view)
                                                    {
                                                        FancyToast.makeText(DrawerActivity.this, "See you soon", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,R.drawable.waving,true).show();
                                                        finish();
                                                    }
                                                });

                                         */

                                snackbar.show();


                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("empid"),
                                        userJson.getString("firstname"),
                                        userJson.getString("lastname"),
                                        userJson.getString("email"),
                                        userJson.getString("signinname"),
                                        userJson.getString("phone_no"),
                                        userJson.getString("role"),
                                        userJson.getString("status")



                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //starting the profile activity
                                //finish();

                                isSuccessful = true;


                            } else {
                               //FancyToast.makeText(getApplicationContext(),obj.getString("message"),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                                isSuccessful = false;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                       // FancyToast.makeText(getApplicationContext(),error.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                      /*  Snackbar snackbar = Snackbar.make(coordinatorLayout, error.getMessage(), Snackbar.LENGTH_SHORT);
                                        /*.setAction("YES",
                                                // If the Undo button
                                                // is pressed, show
                                                // the message using Toast
                                                new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view)
                                                    {
                                                        FancyToast.makeText(DrawerActivity.this, "See you soon", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,R.drawable.waving,true).show();
                                                        finish();
                                                    }
                                                });



                        snackbar.show();
                        */
                        if(error instanceof NoConnectionError){
                            ConnectivityManager cm = (ConnectivityManager)LoginActivity.this
                                    .getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo activeNetwork = null;
                            if (cm != null) {
                                activeNetwork = cm.getActiveNetworkInfo();
                            }
                            if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Server is not connected to internet.", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                //Toast.makeText(getActivity(), "Server is not connected to internet.",Toast.LENGTH_SHORT).show();
                            } else {
                                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Your device is not connected to internet.", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                //Toast.makeText(getActivity(), "Your device is not connected to internet.",Toast.LENGTH_SHORT).show();
                            }
                      /*  } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                                || (error.getCause().getMessage() != null
                                && error.getCause().getMessage().contains("connection"))){
                            Toast.makeText(getActivity(), "Your device is not connected to internet.",
                                    Toast.LENGTH_SHORT).show();

                       */
                        } else if (error.getCause() instanceof MalformedURLException){
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Bad Request.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Bad Request.", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                                || error.getCause() instanceof JSONException
                                || error.getCause() instanceof XmlPullParserException){
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Parse Error (because of invalid json or xml).", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Parse Error (because of invalid json or xml).",Toast.LENGTH_SHORT).show();
                        } else if (error.getCause() instanceof OutOfMemoryError){
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Out Of Memory Error.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof AuthFailureError){
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "server couldn't find the authenticated request.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "server couldn't find the authenticated request.", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Server is not responding.", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                        }else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                                || error.getCause() instanceof ConnectTimeoutException
                                || error.getCause() instanceof SocketException
                                || (error.getCause().getMessage() != null
                                && error.getCause().getMessage().contains("Connection timed out"))) {
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Connection timeout error", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "Connection timeout error",Toast.LENGTH_SHORT).show();
                        } else {
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Unknown Error", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            //Toast.makeText(getActivity(), "An unknown error occurred.",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("signinname", signinname);
                params.put("empid", empid);
                return params;
            }
        };
        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }
}