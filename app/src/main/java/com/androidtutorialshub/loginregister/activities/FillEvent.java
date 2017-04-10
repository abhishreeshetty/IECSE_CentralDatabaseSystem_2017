package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.helpers.InputValidation;
import com.androidtutorialshub.loginregister.model.Event;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

/**
 * Created by mahe on 4/9/2017.
 */

public class FillEvent extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper databaseHelper;

    private final AppCompatActivity activity = FillEvent.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEName;
    private TextInputLayout textInputLayoutEVenue;
    private TextInputLayout textInputLayoutEType;




    private TextInputEditText textInputEditTextEName;
    private TextInputEditText textInputEditTextEVenue;
    private TextInputEditText textInputEditTextEType;

    private AppCompatButton appCompatButtonViewEvent;
    private AppCompatTextView appCompatTextViewEventLink;

    private InputValidation inputValidation;
    private Event event;

   /* public void goToEventFill(View v)
    {
        databaseHelper.getWritableDatabase().execSQL("insert into event values (1, "+tv1.getText().toString()+" ,"
        + tv2.getText().toString()+" ,"+tv3.getText().toString()+" ,"+tv4.getText().toString()+");");
    }
    */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_fill);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView12);

        textInputLayoutEName = (TextInputLayout) findViewById(R.id.textInputLayoutEName);
        textInputLayoutEVenue = (TextInputLayout) findViewById(R.id.textInputLayoutEVenue);
        textInputLayoutEType = (TextInputLayout) findViewById(R.id.textInputLayoutEType);



        textInputEditTextEName = (TextInputEditText) findViewById(R.id.textInputEditTextEName);
        textInputEditTextEVenue = (TextInputEditText) findViewById(R.id.textInputEditTextEVenue);
        textInputEditTextEType = (TextInputEditText) findViewById(R.id.textInputEditTextEType);



        appCompatButtonViewEvent = (AppCompatButton) findViewById(R.id.appCompatButtonViewEvent);



    }

    private void initListeners() {
        appCompatButtonViewEvent.setOnClickListener(this);

    }
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        event = new Event();


    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.appCompatButtonViewEvent:
                addEventToSQLite();
                break;

        }
    }

    private void addEventToSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextEName, textInputLayoutEName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEType, textInputLayoutEType, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEVenue, textInputLayoutEVenue, getString(R.string.error_message_email))) {
            return;
        }





        //databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ('Codemeets','AB5','Tech');");
        //databaseHelper.getWritableDatabase().execSQL("insert into non_member (name,email,mobile) values ('abc','ashbcs@gmail.com','12351235');");
        //databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ('abc','ashbcs@gmail.com','Dev');");

        // databaseHelper.getWritableDatabase().execSQL("insert into member (name,email,mobile,dept,access_level) values ('abc','ashbcs@gmail.com','12351235','Tech','2');");
        databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ("+
                "'"+textInputEditTextEName.getText().toString().trim()+"','"+
                textInputEditTextEVenue.getText().toString().trim()+"','"+
                textInputEditTextEType.getText().toString().trim()+"');");

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

        Intent accountsIntentMem = new Intent(activity, ViewEvent.class);
        startActivity(accountsIntentMem);


    }

}
