package com.example.touristguide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
TextView textView;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





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



        //noinspection SimplifiableIfStatement


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_first_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new DenmarkFragment())
                    .commit();

            textView = (TextView) findViewById(R.id.text1);
            textView.setMovementMethod(new ScrollingMovementMethod());
            String data = "";
            StringBuffer sbuffer = new StringBuffer();
            InputStream is = this.getResources().openRawResource(R.raw.denmark);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            if (is != null)
            {
                try{
                    while( (data=reader.readLine()) != null){
                        sbuffer.append( data + "n" );
                    }
                    textView.setText(sbuffer);
                    is.close();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        } else if (id == R.id.nav_second_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new CobenhagenFragment())
                    .commit();
        } else if (id == R.id.nav_third_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new AarhusFragment())
                    .commit();


        } else if (id == R.id.nav_fourth_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new EsbjergFragment())
                    .commit();
        } else if (id == R.id.nav_fifth_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new HorsensFragment()
            )
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
