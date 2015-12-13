package com.example.pooria.bestoonchi;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pooria.bestoonchi.MylistPackage.RVAdapter;
import com.example.pooria.bestoonchi.model.Darkhast;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // Array of Darkhast class
    private List<Darkhast> darkhasts;
    private UltimateRecyclerView ultimateRecyclerView;
    private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog;
    List<ParseObject> objects;
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBarInit();
        initRecyclerView();


        //read data from Parse.com and add to darkhastArray
        readData();

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
            Intent intent = new Intent(MainActivity.this, Category.class);
            startActivity(intent);
        } else if (id == R.id.MyActivity) {
            Intent intent = new Intent(MainActivity.this, MyactivityActivity.class);
            startActivity(intent);
        } else if (id == R.id.Myselles) {
            Intent intent = new Intent(MainActivity.this, SellesActivity.class);
            startActivity(intent);
        } else if (id == R.id.Setting) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.Regestry) {
            Intent intent = new Intent(MainActivity.this, regestry.class);
            startActivity(intent);
        } else if (id == R.id.Inbox) {
            Intent intent = new Intent(MainActivity.this, InboxActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void readData() {


        darkhasts = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(parseConstant.request_Class_Name);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    // GENERATE LOOP HERE AND GET ALL DATA OF LIST INTO YOUR LOCAL LIST WHICH YOU ARE PASSING TO ADAPTER OF RECYCLER VIEW
                    //I am telling you to add loop so you can learn..

                    //for best performance, image must be caching with Libs ( Fresco - Glide or Picasa - UIL)
                    //otherwise we need to extend ParseQueryAdapter and shim to RVAdapter
                    for (int i = 0; i < objects.size(); i++) {
                        String title = objects.get(i).getString(parseConstant.request_Field_Name);
                        String description = objects.get(i).getString(parseConstant.request_Field_Tozihat);
                        ParseFile photoFile = objects.get(i).getParseFile(parseConstant.request_Field_Picture);

                        if (photoFile == null) {
                            Log.e("test",
                                    "Error no photo." + i);

                            //get NoPhoto must be here
                            photoFile = objects.get(16).getParseFile(parseConstant.request_Field_Picture);

                        } else {
                            photoFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null) {
                                        Log.d("test",
                                                "We've got data in data.");
                                    }
                                }
                            });

                        }

                        Darkhast information = new Darkhast(title, description, photoFile);
                        darkhasts.add(information);

                        //change Adapter OR add itemAdapter must be implement here
                        adapter.notifyItemInserted(darkhasts.size() - 1);

                    }


                } else {

                    Toast.makeText(MainActivity.this, "Loading Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    // something went wrong
                    Log.d("Error ", e.toString());

                }
            }


        });

    }

    private void notifiChange() {
        adapter.notifyDataSetChanged();

    }

    //connect RecyclerListView to Darkhasts <list>
    private void initializeAdapter() {
        adapter = new RVAdapter(darkhasts);
        ultimateRecyclerView.setAdapter(adapter);

//        ultimateRecyclerView.setEmptyView(getResources().getIdentifier("empty_view","layout",getPackageName()));
//        ultimateRecyclerView.showEmptyView();

    }

    private void initRecyclerView() {
        // RecyclerListView
        ultimateRecyclerView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);
        //layout manager in 3form (linear , grid , staggeredGrid)
        int columns = 2;
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager llm2 = new GridLayoutManager(this, columns, LinearLayoutManager.VERTICAL, false);
        ultimateRecyclerView.setLayoutManager(llm2);
        ultimateRecyclerView.setSaveEnabled(true);
        Picasso picasso = Picasso.with(this);
        ultimateRecyclerView.setHasFixedSize(true);

        //set Animation
        ultimateRecyclerView.getItemAnimator().setAddDuration(100);
        ultimateRecyclerView.getItemAnimator().setMoveDuration(200);
        ultimateRecyclerView.getItemAnimator().setChangeDuration(100);

        //pull down refresh
        ultimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        ultimateRecyclerView.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void refreshData() {


        readData();
        initializeAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            Log.e(TAG, "adapter is Null");
        } else {
            notifiChange();

        }
    }
}
