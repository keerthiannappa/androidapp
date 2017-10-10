package com.diyasstudios.theavenue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    BottomNavigationView complaints_navigation , navigation;
    Toolbar toolbar;
    TextView nav_name , nav_number;

    private FirebaseAuth auth;



    //sliding navigation bar listener start
    private NavigationView.OnNavigationItemSelectedListener mOn
            = new NavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Toast.makeText(MainActivity.this, "The Avenue v1.0",
                    Toast.LENGTH_SHORT).show();

        }

            if (id == R.id.nav_signout) {
                Toast.makeText(MainActivity.this, "logged out",
                        Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;


    }};
    //sliding navigation bar listener end


    //bottom navigation bar listener start
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_events:
                    fragment=new EventFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.INVISIBLE);
                    toolbar.setTitle("Events");
                    return true;

                case R.id.navigation_complaints:
                    fragment=new ComplaintsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.VISIBLE);
                    toolbar.setTitle("Complaints");
                    return true;

                case R.id.navigation_transactions:
                    fragment=new TransactionFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.INVISIBLE);
                    toolbar.setTitle("Transactions");
                    return true;

                case R.id.navigation_me:
                    fragment=new MeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.INVISIBLE);
                    toolbar.setTitle("Me");
                    return true;


                //complaints navigation bar items
                case R.id.transaction_navigation_new:
                    fragment=new ComplaintsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    return true;

                case R.id.transaction_navigation_existing:
                    fragment=new ComplaintsFragment2();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    return true;

            }
            return false;
        }

    };
    //bottom navigation bar listener end



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth=FirebaseAuth.getInstance();


        fragment=new EventFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();

        toolbar=(Toolbar) findViewById(R.id.toolbar);

        nav_name=(TextView) findViewById(R.id.textview_nav_name);
        nav_number=(TextView) findViewById(R.id.textView_nav_number);

        toolbar.setTitle("Events");
        toolbar.setBackgroundColor(getResources().getColor(R.color.mecolor1));


        getWindow().setStatusBarColor(getResources().getColor(R.color.mecolor1));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.mecolor1));


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        complaints_navigation = (BottomNavigationView) findViewById(R.id.complaints_navigation2);
        complaints_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(mOn);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_signout) {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
