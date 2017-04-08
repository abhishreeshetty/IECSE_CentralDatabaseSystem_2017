package com.androidtutorialshub.loginregister.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.helpers.InputValidation;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

/**
 * Created by lalit on 8/27/2016.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutMemId;
    private TextInputLayout textInputLayoutRegNo;
    private TextInputLayout textInputLayoutPhoneNo;



    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextMemId;
    private TextInputEditText textInputEditTextRegNo;
    private TextInputEditText textInputEditTextPhoneNo;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutMemId = (TextInputLayout) findViewById(R.id.textInputLayoutMemID);
        textInputLayoutRegNo = (TextInputLayout) findViewById(R.id.textInputLayoutRegNo);
        textInputLayoutPhoneNo = (TextInputLayout) findViewById(R.id.textInputLayoutPhoneNo);


        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextMemId = (TextInputEditText) findViewById(R.id.textInputEditTextMemID);
        textInputEditTextRegNo = (TextInputEditText) findViewById(R.id.textInputEditTextRegNo);
        textInputEditTextPhoneNo = (TextInputEditText) findViewById(R.id.textInputEditTextPhoneNo);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

        /*String query = "SELECT * FROM user;";
        Cursor c = databaseHelper.getWritableDatabase().rawQuery(query, null);
        if (c!=null){
            c.moveToFirst();
            Log.d("2", c.getString(0));
            Log.d("3", c.getString(1));
            Log.d("4", c.getString(2));
            Log.d("5", c.getString(3));
            Log.d("6", c.getString(4));
            Log.d("7", c.getString(5));

            c.close();
        }

        String query2 = "SELECT user_password FROM user where member_id = 789654;";
        Cursor c1 = databaseHelper.getWritableDatabase().rawQuery(query2, null);
        if (c1 != null){
            c1.moveToFirst();
            Log.d("Password: ", c1.getString(0));
            c1.close();
        }*/
    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextMemId, textInputLayoutMemId, getString(R.string.error_message_memid))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextRegNo, textInputLayoutRegNo, getString(R.string.error_message_regno))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhoneNo, textInputLayoutPhoneNo, getString(R.string.error_message_phoneno))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextMemId.getText().toString().trim())) {

            user.setMemId(Integer.valueOf(textInputEditTextMemId.getText().toString().trim()).intValue());
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setRegNo(Integer.valueOf(textInputEditTextRegNo.getText().toString().trim()).intValue());
            user.setPhoneNo(Integer.valueOf(textInputEditTextPhoneNo.getText().toString()).intValue());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_memid_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextMemId.setText(null);
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextRegNo.setText(null);
        textInputEditTextPhoneNo.setText(null);
    }
}
