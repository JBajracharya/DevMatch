package com.finalProject.devmatch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserState;
import com.amazonaws.mobile.client.UserStateDetails;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.util.Log;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;
import static com.amazonaws.mobile.client.UserState.SIGNED_IN;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    static String TAG = "mainActivity";
    String username;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(this, EditProfile.class));
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CreateProfile()).commit();


                return true;
//                break;

            case R.id.nav_message:
                if(AWSMobileClient.getInstance().currentUserState().getUserState().equals(SIGNED_IN)) {
                    Intent intent = new Intent(this, DevMatchMessaging.class);
                    Log.i("@@2@@@2@@@@@@@@@@@", username);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please login or register", Toast.LENGTH_SHORT).show();
                    return false;
                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Messages()).commit();
//                break;
            case R.id.nav_project:

                startActivity(new Intent(this, CreateProject.class));
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProjectFragment()).commit();
//
                return true;
//                break;


            case R.id.nav_projectSearch:
                startActivity(new Intent(this, SearchForProjects.class));
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProjectSearch()).commit();
                return true;

//                break;
            case R.id.nav_projectSearch:
                startActivity(new Intent(this, SearchForProjects.class));
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProjectSearch()).commit();
                return true;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        username = AWSMobileClient.getInstance().getUsername();
        if(username != null)
        {
            Log.i("USERNAME!!!!!!!!!!", username);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlack));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorBlack));
//

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(0, 0, 0));
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CreateProfile()).commit();
//            navigationView.setCheckedItem(R.id.nav_profile);
//        }


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);






        // initializing aws authentication
        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        Log.i("INIT", "onResult: " + userStateDetails.getUserState());
                        username = AWSMobileClient.getInstance().getUsername();
                        Log.i("USERUSERUSER", username);
                        if(userStateDetails.getUserState().equals(UserState.SIGNED_OUT)) {

                            //        Drop-in pre build auth
                            // 'this' refers to the current active activity
                            AWSMobileClient.getInstance().showSignIn(MainActivity.this, SignInUIOptions.builder()
                                            .nextActivity(MainActivity.class)
                                            .build(),
                                    new Callback<UserStateDetails>() {
                                        @Override
                                        public void onResult(UserStateDetails result) {
                                            Log.d(TAG, "onResult: " + result.getUserState());
                                            switch (result.getUserState()){
                                                case SIGNED_IN:
                                                    Log.i("INIT", "logged in!");
                                                    break;
                                                case SIGNED_OUT:
                                                    Log.i(TAG, "onResult: User did not choose to sign-in");
                                                    break;
                                                default:
                                                    AWSMobileClient.getInstance().signOut();
                                                    break;
                                            }

                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            Log.e(TAG, "onError: ", e);
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("INIT", "Initialization error.", e);
                    }
                }
        );

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main,menu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        headerView.setBackgroundColor(Color.rgb(0, 0, 0));
        TextView navUsername = (TextView) headerView.findViewById(R.id.menu_Uname);
        navUsername.setTextColor(Color.rgb(255, 255, 255));
        navUsername.setText(AWSMobileClient.getInstance().getUsername());
        navUsername.setVisibility(VISIBLE);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
                return true;
        }
        if(id == R.id.authenticate)
        {
            authenticate();
        }

        if(id == R.id.action_signOut) {
            //    method to log out user and send to log in page :::::::::::::::::::
            AWSMobileClient.getInstance().signOut();
            Intent goToMainPage = new Intent(MainActivity.this, MainActivity.class);
            startActivity(goToMainPage);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void authenticate()
    {
        AWSMobileClient.getInstance().showSignIn(
                this,
                SignInUIOptions.builder()
                        .nextActivity(MainActivity.class)
                        .canCancel(true)
                        .build(),
                new Callback<UserStateDetails>() {
                    @Override
                    public void onResult(UserStateDetails result) {
                        //Log.d(TAG, "onResult: " + result.getUserState());
                        switch (result.getUserState()){
                            case SIGNED_IN:
                                Log.i("INIT", "logged in!");
                                break;
                            case SIGNED_OUT:
                                //Log.i(TAG, "onResult: User did not choose to sign-in");
                                break;
                            default:
                                AWSMobileClient.getInstance().signOut();
                                break;
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        //Log.e(TAG, "onError: ", e);
                    }
                }
        );
    }
}
