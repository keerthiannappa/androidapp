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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    BottomNavigationView complaints_navigation , navigation;
    Toolbar toolbar;
    TextView nav_name , nav_number, nav_email;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String k=user.getEmail();
    final String[] e=k.split("@");
    String value0,value1,value2;

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
        View headerview=navigationView.getHeaderView(0);
        nav_name=(TextView) headerview.findViewById(R.id.textview_nav_name);
        nav_number=(TextView) headerview.findViewById(R.id.textView_nav_number);
        nav_email=(TextView) headerview.findViewById(R.id.textView_nav_email);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                 value1 = dataSnapshot.child(e[0]).child("mobile").getValue(String.class);

                 value0 = dataSnapshot.child(e[0]).child("name").getValue(String.class);

                 value2 = dataSnapshot.child(e[0]).child("emailID").getValue(String.class);
                nav_number.setText(value1);
                nav_name.setText(value0);
                nav_email.setText(value2);


            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


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
