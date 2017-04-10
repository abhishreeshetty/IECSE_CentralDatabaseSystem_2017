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

    private TextInputLayout textInputLayoutName1;
    private TextInputLayout textInputLayoutEmail1;
    private TextInputLayout textInputLayoutPhoneNo1;
    private TextInputLayout textInputLayoutAccessLevel1;
    private TextInputLayout textInputLayoutDept1;




    private TextInputEditText textInputEditTextName1;
    private TextInputEditText textInputEditTextEmail1;
    private TextInputEditText textInputEditTextAccessLevel1;
    private TextInputEditText textInputEditTextPhoneNo1;
    private TextInputEditText textInputEditTextDept1;

    private AppCompatButton appCompatButtonViewMember;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private Member member;

   /* public void goToMemberFill(View v)
    {
        databaseHelper.getWritableDatabase().execSQL("insert into member values (1, "+tv1.getText().toString()+" ,"
        + tv2.getText().toString()+" ,"+tv3.getText().toString()+" ,"+tv4.getText().toString()+");");
    }
    */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_fill);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView1);

        textInputLayoutName1 = (TextInputLayout) findViewById(R.id.textInputLayoutName1);
        textInputLayoutEmail1 = (TextInputLayout) findViewById(R.id.textInputLayoutEmail1);
        textInputLayoutAccessLevel1 = (TextInputLayout) findViewById(R.id.textInputLayoutAccessLevel);
        textInputLayoutPhoneNo1 = (TextInputLayout) findViewById(R.id.textInputLayoutMobile1);
        textInputLayoutDept1 = (TextInputLayout) findViewById(R.id.textInputLayoutDeptl);


        textInputEditTextName1 = (TextInputEditText) findViewById(R.id.textInputEditTextName1);
        textInputEditTextAccessLevel1 = (TextInputEditText) findViewById(R.id.textInputEditTextAccessLevel);
        textInputEditTextPhoneNo1 = (TextInputEditText) findViewById(R.id.textInputEditTextMobile1);
        textInputEditTextEmail1 = (TextInputEditText) findViewById(R.id.textInputEditTextEmail1);
        textInputEditTextDept1 = (TextInputEditText) findViewById(R.id.textInputEditTextDept1);


        appCompatButtonViewMember = (AppCompatButton) findViewById(R.id.appCompatButtonViewMember);



    }

    private void initListeners() {
        appCompatButtonViewMember.setOnClickListener(this);

    }
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        member = new Member();


    }

    public void onClick(View v) {
        Log.d("b","c");
        switch (v.getId()) {

            case R.id.appCompatButtonViewMember:
                addMemberToSQLite();
                break;

        }
    }

    private void addMemberToSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextName1, textInputLayoutName1, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail1, textInputLayoutEmail1, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextAccessLevel1, textInputLayoutAccessLevel1, getString(R.string.error_message_email))) {
            return;
        }


        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhoneNo1, textInputLayoutPhoneNo1, getString(R.string.error_message_phoneno))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextDept1, textInputLayoutDept1, getString(R.string.error_message_phoneno))) {
            return;
        }


        //databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ('Codemeets','AB5','Tech');");
        //databaseHelper.getWritableDatabase().execSQL("insert into non_member (name,email,mobile) values ('abc','ashbcs@gmail.com','12351235');");
        //databaseHelper.getWritableDatabase().execSQL("insert into event (name,venue,type) values ('abc','ashbcs@gmail.com','Dev');");

        // databaseHelper.getWritableDatabase().execSQL("insert into member (name,email,mobile,dept,access_level) values ('abc','ashbcs@gmail.com','12351235','Tech','2');");
        databaseHelper.getWritableDatabase().execSQL("insert into member (name,email,mobile,dept,access_level) values ("+
                "'"+textInputEditTextName1.getText().toString().trim()+"','"+
                textInputEditTextEmail1.getText().toString().trim()+"','"+
                textInputEditTextPhoneNo1.getText().toString().trim()+"','"+
                textInputEditTextDept1.getText().toString().trim()+"','"+
                textInputEditTextAccessLevel1.getText().toString().trim()+"');");

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

        Intent accountsIntentMem = new Intent(activity, ViewMember.class);
        startActivity(accountsIntentMem);


    }

}
