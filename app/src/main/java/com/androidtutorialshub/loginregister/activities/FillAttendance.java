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
import com.androidtutorialshub.loginregister.model.Attendance;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

/**
 * Created by mahe on 4/9/2017.
 */

public class FillAttendance extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper databaseHelper;

    private final AppCompatActivity activity = FillAttendance.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutMID;
    private TextInputLayout textInputLayoutNID;
    private TextInputLayout textInputLayoutEID;





    private TextInputEditText textInputEditTextMID;
    private TextInputEditText textInputEditTextNID;
    private TextInputEditText textInputEditTextEID;


    private AppCompatButton appCompatButtonViewAttendance;
    private AppCompatTextView appCompatTextViewAttendanceLink;

    private InputValidation inputValidation;
    private Attendance attendance;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_fill);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView3);

        textInputLayoutMID = (TextInputLayout) findViewById(R.id.textInputLayoutMID);
        textInputLayoutNID = (TextInputLayout) findViewById(R.id.textInputLayoutNID);
        textInputLayoutEID = (TextInputLayout) findViewById(R.id.textInputLayoutEID);



        textInputEditTextMID = (TextInputEditText) findViewById(R.id.textInputEditTextMID);
        textInputEditTextNID = (TextInputEditText) findViewById(R.id.textInputEditTextNID);
        textInputEditTextEID = (TextInputEditText) findViewById(R.id.textInputEditTextEID);


        appCompatButtonViewAttendance = (AppCompatButton) findViewById(R.id.appCompatButtonViewAttendance);



    }

    private void initListeners() {
        appCompatButtonViewAttendance.setOnClickListener(this);

    }
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        attendance = new Attendance();


    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.appCompatButtonViewAttendance:
                addMemberToSQLite();
                break;

        }
    }

    private void addMemberToSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextMID, textInputLayoutMID, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextNID, textInputLayoutNID, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEID, textInputLayoutEID, getString(R.string.error_message_email))) {
            return;
        }



        //databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ('Codemeets','AB5','Tech');");
        //databaseHelper.getWritableDatabase().execSQL("insert into non_member (name,email,mobile) values ('abc','ashbcs@gmail.com','12351235');");
        //databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ('abc','ashbcs@gmail.com','Dev');");

        // databaseHelper.getWritableDatabase().execSQL("insert into member (name,email,mobile,dept,access_level) values ('abc','ashbcs@gmail.com','12351235','Tech','2');");
        databaseHelper.getWritableDatabase().execSQL("insert into attendance (memId,nmID,eventID) values ("+
                "'"+Integer.parseInt(textInputEditTextMID.getText().toString().trim())+"','"+
                Integer.parseInt(textInputEditTextNID.getText().toString().trim())+"','"+
                Integer.parseInt(textInputEditTextEID.getText().toString().trim())+ "');");

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

        Intent accountsIntentAtte = new Intent(activity, ViewAttendance.class);
        startActivity(accountsIntentAtte);


    }

}
