package com.xheghun.esopos;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.xheghun.esopos.fragments.DashBoardFragment;
import com.xheghun.esopos.fragments.OfflineHelpFragment;
import com.xheghun.esopos.fragments.OfflinePOSFragment;
import com.xheghun.esopos.fragments.OnlinePOSFragment;
import com.xheghun.esopos.fragments.StoreTimelineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.esopos_main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.esopos_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.esopos_nav_view)
    NavigationView navigationView;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esopos_nav_drawer);
        ButterKnife.bind(this);

       setSupportActionBar(toolbar);
       toolbar.setTitle(getResources().getString(R.string.khans_store));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.esopos_bottom_nav_fragment, new DashBoardFragment())
                .commit();

       toolbar.setNavigationOnClickListener(view1 -> drawerLayout.openDrawer(GravityCompat.START));
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            //Handle Drawer Item Selection
            switch (menuItem.getItemId()) {
                case R.id.drawer_dashboard:
                    loadFragment(new DashBoardFragment());
                    break;
                case R.id.drawer_online_pos:
                    loadFragment(new OnlinePOSFragment());
                    break;
                case R.id.drawer_all_sales:
                    break;
                case R.id.drawer_customer:
                    break;
                case R.id.drawer_offline_pos:
                    loadFragment(new OfflinePOSFragment());
                    break;
                case R.id.drawer_offline_help:
                    loadFragment(new OfflineHelpFragment());
                    break;
                case R.id.store_timeline:
                    loadFragment(new StoreTimelineFragment());
                    break;
            }
            return true;
        });
    }

    private void loadFragment(@NonNull Fragment object) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.esopos_bottom_nav_fragment, object)
                .commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else {
             builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Esopos");
            builder.setMessage("Close Application");
            builder.setIcon(R.drawable.logo_md);

            builder.setPositiveButton("Yes", (dialog, which) -> super.onBackPressed());
            builder.setNegativeButton("No", (dialog, which) -> alertDialog.dismiss());
            alertDialog = builder.create();
            alertDialog.show();
        }
    }
}