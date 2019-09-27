package com.example.checklistimaguru;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity  {

    void goSearch(Date date){
        searchSettings.setVisibility(View.INVISIBLE);
        searchSettings.startAnimation(bottom_top);
        menuItem.collapseActionView();
        if (isOnline(this))
        new MyAsyncTask(date, this, recyclerView).execute();
        else {
            Toast.makeText(getApplicationContext(),
                    "Отсутствует интернет-соединение!",Toast.LENGTH_LONG).show();
        }
    }

    static boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }
    RecyclerView recyclerView;
    SearchView searchView;
    MenuItem settings;
    MenuItem menuItem;
    TextView infoText;
    private CardView searchSettings;
    Animation top_bottom = null;
    Animation bottom_top = null;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        top_bottom = AnimationUtils.loadAnimation(this, R.anim.slide_top_bottom);
        bottom_top = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_top);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchSettings = findViewById(R.id.searchSettings);
        CalendarView calendarView = findViewById(R.id.calendarView);
        Button todayButton = findViewById(R.id.todayButton);
        Button tomorrowButton = findViewById(R.id.tomorrowButton);
        infoText = findViewById(R.id.infoText);
        final Date today = new Date();
        final Date tomorrow;
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar.getTime();
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch(today);
                setTitle(R.string.today);
            }
        });
        tomorrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch(tomorrow);
                setTitle(R.string.tomorrow);
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                final DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
                calendar.set(year,month,dayOfMonth);
                Date date = calendar.getTime();
                String title = dateFormat.format(date).substring(0,1).toUpperCase() + dateFormat.format(date).substring(1).toLowerCase();
                goSearch(date);
                setTitle(title);
            }
        });
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        final SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        menuItem = menu.findItem(R.id.search);
        settings = menu.findItem(R.id.settings);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                infoText.setVisibility(View.INVISIBLE);
                searchSettings.setVisibility(View.VISIBLE);
                searchSettings.startAnimation(top_bottom);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchSettings.setVisibility(View.INVISIBLE);
                searchSettings.startAnimation(bottom_top);
                return true;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() { //кнопка поиска по названию
            @Override
            public void onClick(View v) {
                searchSettings.setVisibility(View.INVISIBLE);
                searchSettings.startAnimation(bottom_top);
            }
        });

        settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, settingsActivity.class);
                startActivity(intent);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String name;
                infoText.setVisibility(View.INVISIBLE);
                query = searchView.getQuery().toString();
                name = query.replaceAll("\\s+","%20");
                new MyAsyncTask(name, MainActivity.this, recyclerView).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}