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
import com.androidtutorialshub.loginregister.adapters.MembersRecyclerAdapter;
import com.androidtutorialshub.loginregister.adapters.UsersRecyclerAdapter;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class ViewMember extends AppCompatActivity {

    private AppCompatActivity activity = ViewMember.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewMembers;
    private List<Member> listMembers;
    private MembersRecyclerAdapter membersRecyclerAdapter;
    private DatabaseHelper databaseHelper;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_view);
        getSupportActionBar().setTitle("");

        initViews();
        initObjects();

    }


    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewMembers = (RecyclerView) findViewById(R.id.recyclerViewMembers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listMembers = new ArrayList<>();
        membersRecyclerAdapter = new MembersRecyclerAdapter(listMembers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMembers.setLayoutManager(mLayoutManager);
        recyclerViewMembers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMembers.setHasFixedSize(true);
        recyclerViewMembers.setAdapter(membersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        //textViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all member records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listMembers.clear();
                listMembers.addAll(databaseHelper.getAllMember());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                membersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
