package com.diyasstudios.theavenue;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.graphics.Color.parseColor;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    BottomNavigationView complaints_navigation;
    BottomNavigationView navigation;
    Toolbar toolbar;
    LinearLayout linearLayout;
    TextView nav_name;
    TextView nav_number;
    private DatabaseReference mRef;


    private NavigationView.OnNavigationItemSelectedListener mOn
            = new NavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Toast.makeText(MainActivity.this, "The Avenue v1.0",
                    Toast.LENGTH_SHORT).show();

        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;


    }};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_events:
                    fragment=new EventFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.INVISIBLE);
                    navigation.setBackgroundColor(parseColor("#c62828"));
                    toolbar.setTitle("Events");
                    toolbar.setBackgroundColor(getResources().getColor(R.color.eventcolor2));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.eventcolor1));
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.eventcolor2));
                    return true;

                case R.id.navigation_complaints:
                    fragment=new ComplaintsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.VISIBLE);
                    navigation.setBackgroundColor(parseColor("#7b1fa2"));
                    toolbar.setTitle("Complaints");
                    toolbar.setBackgroundColor(getResources().getColor(R.color.complaintcolor2));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.complaintcolor1));
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.complaintcolor2));
                    return true;

                case R.id.navigation_transactions:
                    fragment=new TransactionFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.INVISIBLE);
                    navigation.setBackgroundColor(parseColor("#4caf50"));
                    toolbar.setTitle("Transactions");
                    toolbar.setBackgroundColor(getResources().getColor(R.color.transactioncolor2));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.transactioncolor1));
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.transactioncolor2));
                    return true;

                case R.id.navigation_me:
                    fragment=new MeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();
                    complaints_navigation.setVisibility(View.INVISIBLE);
                    navigation.setBackgroundColor(parseColor("#039be5"));
                    toolbar.setTitle("Me");
                    toolbar.setBackgroundColor(getResources().getColor(R.color.mecolor2));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.mecolor1));
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.mecolor2));
                    return true;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fragment=new EventFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout,fragment,fragment.getTag()).commit();

        toolbar=(Toolbar) findViewById(R.id.toolbar);

        mRef = FirebaseDatabase.getInstance().getReference();

        complaints_navigation = (BottomNavigationView) findViewById(R.id.complaints_navigation2);
        nav_name=(TextView) findViewById(R.id.textview_nav_name);
        nav_number=(TextView) findViewById(R.id.textView_nav_number);

        mRef.child("User").child("keerthiannappa@gmail.com").child("Name").setValue("Keerthi");
        mRef.child("User").child("keerthiannappa@gmail.com").child("Mobile").setValue("8428421605");
        mRef.child("User").child("keerthiannappa@gmail.com").child("MailID").setValue("keerthiannappa@gmail.com");
        mRef.child("User").child("keerthiannappa@gmail.com").child("Password").setValue("theavenue");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1=dataSnapshot.child("User").child(LoginActivity.staticemail).child("Name").getValue(String.class);
                String value2=dataSnapshot.child("User").child(LoginActivity.staticemail).child("Number").getValue(String.class);

                nav_name.setText(value1);
                nav_number.setText(value2);

                //USE THE SAME CHILD PARAMETER TO RETRIVE

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        toolbar.setTitle("Events");
        toolbar.setBackgroundColor(getResources().getColor(R.color.eventcolor2));

        linearLayout=(LinearLayout) findViewById(R.id.linear);

        getWindow().setStatusBarColor(getResources().getColor(R.color.eventcolor1));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.eventcolor2));

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setBackgroundColor(parseColor("#c62828"));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
