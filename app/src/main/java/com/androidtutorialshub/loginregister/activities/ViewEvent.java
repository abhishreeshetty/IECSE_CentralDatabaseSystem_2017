package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.adapters.EventsRecyclerAdapter;
import com.androidtutorialshub.loginregister.adapters.MembersRecyclerAdapter;
import com.androidtutorialshub.loginregister.adapters.UsersRecyclerAdapter;
import com.androidtutorialshub.loginregister.model.Event;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class ViewEvent extends AppCompatActivity {

    private AppCompatActivity activity = ViewEvent.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewEvents;
    private List<Event> listEvents;
    private EventsRecyclerAdapter eventsRecyclerAdapter;
    private DatabaseHelper databaseHelper;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_view);
        getSupportActionBar().setTitle("");

        initViews();
        initObjects();

    }


    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewEvents = (RecyclerView) findViewById(R.id.recyclerViewEvents);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listEvents = new ArrayList<>();
        eventsRecyclerAdapter = new EventsRecyclerAdapter(listEvents);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEvents.setLayoutManager(mLayoutManager);
        recyclerViewEvents.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEvents.setHasFixedSize(true);
        recyclerViewEvents.setAdapter(eventsRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        //textViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all event records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listEvents.clear();
                listEvents.addAll(databaseHelper.getAllEvent());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                eventsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
