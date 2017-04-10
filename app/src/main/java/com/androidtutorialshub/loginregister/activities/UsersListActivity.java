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
import com.androidtutorialshub.loginregister.adapters.UsersRecyclerAdapter;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersListActivity extends AppCompatActivity {

    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;


    Button memBtn;
    Button nonMemBtn;
    Button eventBtn;
    Button attenBtn;
    Button regBtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle("");
        memBtn=(Button)findViewById(R.id.buttonMem);
        nonMemBtn=(Button)findViewById(R.id.buttonNonMem);
        eventBtn=(Button)findViewById(R.id.buttonEvent);
        attenBtn=(Button)findViewById(R.id.buttonAtten);
        regBtn=(Button)findViewById(R.id.buttonReg);
        initViews();
        initObjects();

    }

    public void goToMember(View v)
    {
        Intent intentGoToMember = new Intent(getApplicationContext(), FillMember.class);
        startActivity(intentGoToMember);
    }

    public void goToNonMember(View v)
    {
        Intent intentGoToNonMember = new Intent(getApplicationContext(), FillNonMember.class);
        startActivity(intentGoToNonMember);
    }

    public void goToEvent(View v)
    {
        Intent intentGoToEvent = new Intent(getApplicationContext(), FillEvent.class);
        startActivity(intentGoToEvent);
    }

    public void goToRegistration(View v)
    {
        Intent intentGoToEvent = new Intent(getApplicationContext(), FillRegistration.class);
        startActivity(intentGoToEvent);
    }

    public void goToAttendance(View v)
    {
        Intent intentGoToEvent = new Intent(getApplicationContext(), FillAttendance.class);
        startActivity(intentGoToEvent);
    }



    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
