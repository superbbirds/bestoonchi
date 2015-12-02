package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pooria.bestoonchi.MylistPackage.RVAdapter;
import com.example.pooria.bestoonchi.model.Darkhast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // Array of Darkhast class
    private List<Darkhast> darkhasts;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBarInit();

        // RecyclerListView
        rv=(RecyclerView)findViewById(R.id.rv);

        //layout manager in 3form (linear , grid , staggeredGrid)
        int columns = 2;
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager llm2  = new GridLayoutManager(this,columns);

        rv.setLayoutManager(llm2);
        rv.setHasFixedSize(true);

        //read data from Parse.com and add to darkhastArray
        initializeData();

        //connect darkhastArray to RecyclerListView
        initializeAdapter();



    }

    private void actionBarInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                startActivity(intent);

                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Category) {
Intent intent=new Intent(MainActivity.this,Category.class);
            startActivity(intent);
        } else if (id == R.id.MyActivity) {
Intent intent=new Intent(MainActivity.this,MyactivityActivity.class);
            startActivity(intent);
        } else if (id == R.id.Myselles) {
Intent intent=new Intent(MainActivity.this,SellesActivity.class);
            startActivity(intent);
        } else if (id == R.id.Setting) {
Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.Regestry) {
Intent intent=new Intent(MainActivity.this,regestry.class);
            startActivity(intent);
        } else if (id == R.id.Inbox) {
Intent intent=new Intent(MainActivity.this,InboxActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initializeData(){

        darkhasts = new ArrayList<>();
        darkhasts.add(new Darkhast("Kala 1", "56000", R.drawable.object1));
        darkhasts.add(new Darkhast("jense ", "78000", R.drawable.object2));
        darkhasts.add(new Darkhast("product", "160000", R.drawable.object3));
    }


    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(darkhasts);
        rv.setAdapter(adapter);
    }
}
