package com.osepoo.angamizaactual;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity {

    ShimmerFrameLayout shimmerFrameLayout, shimmerFrameLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.ghaliclassic));
        }

        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.slpashshimmer);
        shimmerFrameLayout1 = (ShimmerFrameLayout) findViewById(R.id.slpashshimmer2);

        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout1.startShimmer();

        Thread firstPageTimer = new Thread(){
            public void run(){

                try{
                    sleep(3000);
                    Intent slimshady = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(slimshady);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        firstPageTimer.start();

    }
}