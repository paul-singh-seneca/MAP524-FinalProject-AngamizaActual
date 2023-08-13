package com.osepoo.angamizaactual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.osepoo.angamizaactual.utils.AboutFragment;
import com.osepoo.angamizaactual.utils.FaceFragment;
import com.osepoo.angamizaactual.utils.FeedFragment;
import com.osepoo.angamizaactual.utils.MapsFragment;
import com.osepoo.angamizaactual.utils.TasksFragment;
import com.shashank.sony.fancytoastlib.FancyToast;

public class DrawerActivity extends AppCompatActivity {


    FlowingDrawer mDrawer;
    NavigationView navigationView;
    RelativeLayout coordinatorLayout;
    boolean doubleBackToExitPressedOnce = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.ghaliclassic));
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();

        coordinatorLayout = (RelativeLayout) findViewById(R.id.content);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.vNavigation);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });



      setupToolbar();
      setupMenuHeader();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void selectDrawerItem(MenuItem item) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass;
        switch(item.getItemId()) {
            case R.id.menu_feed:
                fragmentClass = FeedFragment.class;
                break;
            //case R.id.menu_face:
            //   fragmentClass = FaceFragment.class;
            //   break;
            case R.id.menu_tasks:
                fragmentClass = TasksFragment.class;
                break;
            case R.id.menu_maps:
                fragmentClass = MapsFragment.class;
                break;
            case R.id.menu_about:
                fragmentClass = AboutFragment.class;
                break;
            //case R.id.menu_log:
            //    Intent intent = new Intent(DrawerActivity.this,LogActivity.class);
            //    startActivity(intent);
            //  break;
            default:
                fragmentClass = FeedFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setReorderingAllowed(true)
                .addToBackStack("fragback")
                .commit();

        if (id == R.id.menu_logout) {
            if (mDrawer.isMenuVisible()) {
                mDrawer.closeMenu();}
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            FancyToast.makeText(DrawerActivity.this, "LogOut Successful", FancyToast.LENGTH_LONG,FancyToast.WARNING,R.drawable.crying,false).show();

        } else if (id == R.id.menu_log) {
            Intent intent = new Intent(DrawerActivity.this,LogActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_search) {
            Intent intent2 = new Intent(DrawerActivity.this,SearchActivity.class);
            startActivity(intent2);
        } else if (id == R.id.menu_help) {
            Intent intent3 = new Intent(DrawerActivity.this,HelpLine.class);
            startActivity(intent3);
        } else if (id == R.id.menu_face) {
            Intent intent4 = new Intent(DrawerActivity.this,FaceDetection.class);
            startActivity(intent4);}

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
       // setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeMenu();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);  // clear all scroll flags
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setBackgroundColor(getColor(R.color.ghaliclassic));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.toggleMenu();
            }
        });
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    public void setupMenuHeader(){
      /*  View view;
        LayoutInflater inflater = (LayoutInflater) DrawerActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_global_menu_header, null);
        ImageView imageViewmenu = view.findViewById(R.id.vMenuImageView);
        Glide.with(DrawerActivity.this)
                .load(R.drawable.coplights)
                .placeholder(R.drawable.loading) // any placeholder to load at start
                .error(R.drawable.error)  // any image in case of error
                .override(200, 200) // resizing
                .centerCrop()
                .into(imageViewmenu);

       */
        View view = navigationView.getHeaderView(0);
        LinearLayout linearLayout = view.findViewById(R.id.vGlobalMenuHeader);
        linearLayout.setBackground(getDrawable(R.drawable.classic_rounded_solid));

    }



    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            //super.onBackPressed();
            //startActivity(new Intent(DrawerActivity.this,LoginActivity.class));
            //finish();
            // Create a snackbar
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                FancyToast.makeText(DrawerActivity.this, "See you soon", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,R.drawable.waving,true).show();
                return;
            }

            this.doubleBackToExitPressedOnce = true;


            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Press again to exit", Snackbar.LENGTH_LONG)
                    .setAction("YES",
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

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

        }
    }
}
