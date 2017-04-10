package com.androidtutorialshub.loginregister.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.helpers.InputValidation;
import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.NonMember;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

/**
 * Created by mahe on 4/9/2017.
 */

public class FillNonMember extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper databaseHelper;

    private final AppCompatActivity activity = FillNonMember.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName1;
    private TextInputLayout textInputLayoutEmail1;
    private TextInputLayout textInputLayoutPhoneNo1;




    private TextInputEditText textInputEditTextName1;
    private TextInputEditText textInputEditTextEmail1;
    private TextInputEditText textInputEditTextPhoneNo1;

    private AppCompatButton appCompatButtonViewNonMember;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private NonMember nonmember;

   /* public void goToMemberFill(View v)
    {
        databaseHelper.getWritableDatabase().execSQL("insert into member values (1, "+tv1.getText().toString()+" ,"
        + tv2.getText().toString()+" ,"+tv3.getText().toString()+" ,"+tv4.getText().toString()+");");
    }
    */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_member_fill);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView2);

        textInputLayoutName1 = (TextInputLayout) findViewById(R.id.textInputLayoutName3);
        textInputLayoutEmail1 = (TextInputLayout) findViewById(R.id.textInputLayoutEmail3);
        textInputLayoutPhoneNo1 = (TextInputLayout) findViewById(R.id.textInputLayoutMobile3);


        textInputEditTextName1 = (TextInputEditText) findViewById(R.id.textInputEditTextName3);
        textInputEditTextPhoneNo1 = (TextInputEditText) findViewById(R.id.textInputEditTextMobile3);
        textInputEditTextEmail1 = (TextInputEditText) findViewById(R.id.textInputEditTextEmail3);


        appCompatButtonViewNonMember = (AppCompatButton) findViewById(R.id.appCompatButtonViewNonMember);



    }

    private void initListeners() {
        appCompatButtonViewNonMember.setOnClickListener(this);

    }
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        nonmember = new NonMember();


    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonViewNonMember:
                addNonMemberToSQLite();
                break;


        }
    }

    private void addNonMemberToSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextName1, textInputLayoutName1, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail1, textInputLayoutEmail1, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhoneNo1, textInputLayoutPhoneNo1, getString(R.string.error_message_phoneno))) {
            return;
        }




        databaseHelper.getWritableDatabase().execSQL("insert into non_member (name,email,mobile) values ('"+
                textInputEditTextName1.getText().toString().trim()+"','"+
                textInputEditTextEmail1.getText().toString().trim()+"','"+
                textInputEditTextPhoneNo1.getText().toString().trim()+"');");

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();


    }

}
