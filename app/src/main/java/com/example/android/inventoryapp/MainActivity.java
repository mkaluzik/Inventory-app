package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.android.inventoryapp.data.Commodity;
import com.example.android.inventoryapp.data.DbHelper;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    DbHelper dbHelper;
    StorageCursorAdapter curAdapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();

        curAdapter = new StorageCursorAdapter(this, cursor);
        listView.setAdapter(curAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        curAdapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        curAdapter.swapCursor(dbHelper.readStock());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data to DB
                addDummyData();
                curAdapter.swapCursor(dbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Dummy data
     */
    private void addDummyData() {
        Commodity camera = new Commodity(
                "Digital camera",
                "15000 CZK",
                25,
                "Nikon",
                "+420 000 000 000",
                "getsupplies@nikon.com",
                "android.resource://com.example.android.inventoryapp/drawable/camera");
        dbHelper.insertItem(camera);

        Commodity drone = new Commodity(
                "Drone",
                "2 000 CZK",
                10,
                "Mavic Pro",
                "+420 000 111 000",
                "order@mavicisamazing.com",
                "android.resource://com.example.android.inventoryapp/drawable/drone");
        dbHelper.insertItem(drone);

        Commodity laptop = new Commodity(
                "Amazing Laptop",
                "25 000 CZK",
                84,
                "Lenovo",
                "+49 000 000 108",
                "getlaptop@lenovo.com",
                "android.resource://com.example.android.inventoryapp/drawable/laptop");
        dbHelper.insertItem(laptop);

        Commodity monitor1 = new Commodity(
                "Awesome Monitor",
                "1 500 CZK",
                24,
                "BenQ",
                "+420 000 000 456",
                "thankyouforshopping@benq.com",
                "android.resource://com.example.android.inventoryapp/drawable/monitor1");
        dbHelper.insertItem(monitor1);

        Commodity monitor2 = new Commodity(
                "Dream-like monitor",
                "8 500 CZK",
                97,
                "ASUS",
                "+420 000 123 000",
                "shoping@asus.com",
                "android.resource://com.example.android.inventoryapp/drawable/monitor2");
        dbHelper.insertItem(monitor2);

        Commodity printer = new Commodity(
                "Printer",
                "3 500 CZK",
                15,
                "Epson",
                "+420 000 444 000",
                "leaveyourmoney@epson.com",
                "android.resource://com.example.android.inventoryapp/drawable/printer");
        dbHelper.insertItem(printer);

        Commodity scaner = new Commodity(
                "Supercheap scaner",
                "800 CZK",
                120,
                "Epson",
                "+420 000 444 000",
                "leaveyourmoney@epson.com",
                "android.resource://com.example.android.inventoryapp/drawable/scaner");
        dbHelper.insertItem(scaner);

        Commodity tablet = new Commodity(
                "Graphical tablet",
                "4 500 CZK",
                59,
                "Amazion",
                "+34 000 000 0000",
                "shopaholics@amazion.com",
                "android.resource://com.example.android.inventoryapp/drawable/tabletgraphical");
        dbHelper.insertItem(tablet);
    }
}
