package w3engineers.com.cookerbot.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import w3engineers.com.cookerbot.R;
import w3engineers.com.cookerbot.aysctask.InitCSV;
import w3engineers.com.cookerbot.bluetooth.DeviceListActivity;
import w3engineers.com.cookerbot.fragment.GameFragment;
import w3engineers.com.cookerbot.permission.CookBotAppPermissions;
import w3engineers.com.cookerbot.permission.InvokePermission;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private GameFragment fragment;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initMarshmallowPermission();
        InitCSV initCSV = new InitCSV(this);
        initCSV.execute();

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

         if (id == R.id.nav_iot) {
            Intent intent = new Intent(MainActivity.this,DeviceListActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_create_recipe) {
             Intent intent = new Intent(MainActivity.this,NewRecipe.class);
             startActivity(intent);
        }else if (id == R.id.nav_recipe_list) {
             Intent intent = new Intent(MainActivity.this,RecipeList.class);
             startActivity(intent);
         }
         /*else if (id == R.id.nav_recipe_map) {
             Intent intent = new Intent(MainActivity.this,GradientsMapActivity.class);
             startActivity(intent);
         }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if(fragment!=null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commitAllowingStateLoss();
        }
        return true;

        //return true;
    }

    /*public void initMarshmallowPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
        },1);
    }*/

    public void initMarshmallowPermission() {

        if (InvokePermission.getInstance().isPermitted(mContext,
                CookBotAppPermissions.GetPermissionsArray(
                        CookBotAppPermissions.PERMISSION_BLUETOOTH,
                        CookBotAppPermissions.PERMISSION_BLUETOOTH,
                        CookBotAppPermissions.PERMISSION_BLUETOOTH_ADMIN,
                        CookBotAppPermissions.PERMISSION_WRITE_EXTERNAL_STORAGE,
                        CookBotAppPermissions.PERMISSION_READ_EXTERNAL_STORAGE,
                        CookBotAppPermissions.PERMISSION_CAMERA))) {

        }else
        {
            //Toast.makeText(mContext,"App could not run without permission",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

            }
        }
    }
}
