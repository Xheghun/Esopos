package com.xheghun.esopos;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.xheghun.esopos.adapter.ExpandableListAdapter;
import com.xheghun.esopos.fragments.AllSalesFragment;
import com.xheghun.esopos.fragments.DashBoardFragment;
import com.xheghun.esopos.fragments.OfflineHelpFragment;
import com.xheghun.esopos.fragments.OfflinePOSFragment;
import com.xheghun.esopos.fragments.OnlinePOSFragment;
import com.xheghun.esopos.fragments.StoreTimelineFragment;
import com.xheghun.esopos.model.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.esopos_main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.esopos_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.esopos_nav_view)
    NavigationView navigationView;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;


	@BindView(R.id.expandableListView)
	ExpandableListView expandableListView;

	ExpandableListAdapter expandableListAdapter;

	List<MenuModel> headerList = new ArrayList<>();
	HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
	private List<MenuModel> childModelsList;
	private MenuModel childModel;
	private MenuModel menuModel;

	@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esopos_nav_drawer);
        ButterKnife.bind(this);

       setSupportActionBar(toolbar);
       toolbar.setTitle(getResources().getString(R.string.khans_store));

		prepareMenuData();
		populateExpandableList();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.esopos_bottom_nav_fragment, new DashBoardFragment())
                .commit();
       toolbar.setNavigationOnClickListener(view1 -> drawerLayout.openDrawer(GravityCompat.START));

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

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		return false;
	}

	private void prepareMenuData() {

		//Dashboard. No sub menus
		MenuModel menuModel = new MenuModel("Dashboard", true, false, new DashBoardFragment(),
				mGetDrawable(R.drawable.ic_dashboard), 0);
		headerList.add(menuModel);

		if (menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

		loadSalesRegister();


		menuModel = new MenuModel("Customer", true, false, null,
				mGetDrawable(R.drawable.ic_customer), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

		loadOfflineSales();

		menuModel = new MenuModel("Supplier", true, false, null,
				mGetDrawable(R.drawable.ic_supplier), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

		loadProductServices();

		loadReporting();

		loadExpenses();

		menuModel = new MenuModel("Store Timeline", true, false, new StoreTimelineFragment(),
				mGetDrawable(R.drawable.ic_past), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

		menuModel = new MenuModel("Market Place", true, false, null,
				mGetDrawable(R.drawable.ic_basketball), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

		menuModel = new MenuModel("Help", true, false, null,
				mGetDrawable(R.drawable.ic_help), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}


		menuModel = new MenuModel("Settings", true, false, null,
				mGetDrawable(R.drawable.ic_settings), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

		menuModel = new MenuModel("Log Out", true, false, null,
				mGetDrawable(R.drawable.ic_logout), 0);
		headerList.add(menuModel);

		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}

	}

	private void loadExpenses() {
		menuModel = new MenuModel("Expenses", true, true, null,
				mGetDrawable(R.drawable.ic_us_dollar_24dp), mGetDrawable(R.drawable.ic_keyboard_arrow_right));
		headerList.add(menuModel);

		childModelsList = new ArrayList<>();
		childModel = new MenuModel("Expense", false, false, null,
				mGetDrawable(R.drawable.ic_us_dollar_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Expense", false, false, null,
				mGetDrawable(R.drawable.ic_us_dollar_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Expense", false, false, null,
				mGetDrawable(R.drawable.ic_us_dollar_24dp), 0);
		childModelsList.add(childModel);

		if (menuModel.hasChildren) {
			childList.put(menuModel, childModelsList);
		}
	}

	private void loadProductServices() {
		menuModel = new MenuModel("Product Service", true, true, null,
				mGetDrawable(R.drawable.ic_grid_24dp), mGetDrawable(R.drawable.ic_keyboard_arrow_right));
		headerList.add(menuModel);
		childModelsList = new ArrayList<>();
		childModel = new MenuModel("Product List", false, false, null,
				mGetDrawable(R.drawable.ic_grid_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Add Product", false, false, null,
				mGetDrawable(R.drawable.ic_plus_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Service List", false, false, null,
				mGetDrawable(R.drawable.ic_grid_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Add Service", false, false, null,
				mGetDrawable(R.drawable.ic_plus_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Category List", false, false, null,
				mGetDrawable(R.drawable.ic_grid_24dp), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Add Category", false, false, null,
				mGetDrawable(R.drawable.ic_plus_24dp), 0);
		childModelsList.add(childModel);

		if (menuModel.hasChildren) {
			childList.put(menuModel, childModelsList);
		}

		menuModel = new MenuModel("Staff", false, false, null,
				mGetDrawable(R.drawable.ic_staff_card), 0);
		if (!menuModel.hasChildren) {
			childList.put(menuModel, null);
		}
	}

	private void loadReporting() {
		menuModel = new MenuModel("Reporting", true, true, null,
				mGetDrawable(R.drawable.ic_chart), mGetDrawable(R.drawable.ic_keyboard_arrow_right));
		headerList.add(menuModel);

		childModelsList = new ArrayList<>();
		childModel = new MenuModel("Generate Report", false, false, null,
				mGetDrawable(R.drawable.ic_box), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("All Sales Report", false, false, null,
				mGetDrawable(R.drawable.ic_view_details), 0);
		childModelsList.add(childModel);


		childModel = new MenuModel("Inventory Report", false, false, null,
				mGetDrawable(R.drawable.ic_view_details), 0);
		childModelsList.add(childModel);


		childModel = new MenuModel("Offline Sales", false, false, null,
				mGetDrawable(R.drawable.ic_offline), 0);
		childModelsList.add(childModel);

		if (menuModel.hasChildren) {
			childList.put(menuModel, childModelsList);
		}
	}

	private void loadSalesRegister() {
		menuModel = new MenuModel("Sales Register", true, true, null,
				mGetDrawable(R.drawable.ic_expense), mGetDrawable(R.drawable.ic_keyboard_arrow_right)); //Menu of Java Tutorials
		headerList.add(menuModel);

		childModelsList = new ArrayList<>();
		childModel = new MenuModel("Online POS", false, false, new OnlinePOSFragment(),
				mGetDrawable(R.drawable.ic_expense), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("All Sales", false, false, new AllSalesFragment(),
				mGetDrawable(R.drawable.ic_expense), 0);
		childModelsList.add(childModel);


		if (menuModel.hasChildren) {
			Log.d("API123", "here");
			childList.put(menuModel, childModelsList);
		}
	}

	private void loadOfflineSales() {

		childModelsList = new ArrayList<>();

		menuModel = new MenuModel("Offline Sales", true, true, null,
				mGetDrawable(R.drawable.ic_offline), mGetDrawable(R.drawable.ic_keyboard_arrow_right)); //Menu of Python Tutorials
		headerList.add(menuModel);

		childModel = new MenuModel("Offline POS", false, false, new OfflinePOSFragment(),
				mGetDrawable(R.drawable.ic_offline), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Offline Settings", false, false, null,
				mGetDrawable(R.drawable.ic_offline), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Offline Sales", false, false, null,
				mGetDrawable(R.drawable.ic_offline), 0);
		childModelsList.add(childModel);

		childModel = new MenuModel("Offline Help", false, false, new OfflineHelpFragment(),
				mGetDrawable(R.drawable.ic_offline), 0);
		childModelsList.add(childModel);


		if (menuModel.hasChildren) {
			childList.put(menuModel, childModelsList);
		}
	}

	private int mGetDrawable(int resID) {
		if (resID != 0)
			return resID;
		else
			return R.drawable.ic_info_outline_red;

	}

	private void populateExpandableList() {

		expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
		expandableListView.setAdapter(expandableListAdapter);

		expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
			MenuModel menuModel = headerList.get(groupPosition);
			if (menuModel.hasChildren && menuModel.fragment != null) {
				loadFragment(menuModel.fragment);
					onBackPressed();
				}
			return false;
		});

		expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

			if (childList.get(headerList.get(groupPosition)) != null) {
				MenuModel model = Objects.requireNonNull(childList.get(headerList.get(groupPosition))).get(childPosition);
				if (model.fragment != null) {
					loadFragment(model.fragment);
					onBackPressed();
				}
			}

			return false;
		});
	}
}
