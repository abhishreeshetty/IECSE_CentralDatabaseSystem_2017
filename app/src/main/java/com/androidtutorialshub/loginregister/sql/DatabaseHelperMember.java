package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.NonMember;
import com.androidtutorialshub.loginregister.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahe on 4/10/2017.
 */

public class DatabaseHelperMember extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "IECSE_CENTRAL_DATABASE.db";

    // User table name

    private static final String TABLE_MEMBER = "member";





    private static final String COLUMN_MEM_ID = "memID";
    private static final String COLUMN_MEM_NAME = "name";
    private static final String COLUMN_MEM_EMAIL = "email";
    private static final String COLUMN_MEM_PHONE = "mobile";
    private static final String COLUMN_MEM_DEPT = "dept";
    private static final String COLUMN_MEM_AL="access_level";

    // create table sql query


    private String CREATE_MEMBER_TABLE="CREATE TABLE " + TABLE_MEMBER + "(" +
            COLUMN_MEM_ID + " INT PRIMARY KEY, " + COLUMN_MEM_NAME + " TEXT, "
            + COLUMN_MEM_EMAIL + " TEXT, " + COLUMN_MEM_PHONE + " TEXT, "  + COLUMN_MEM_DEPT + " TEXT, "
            + COLUMN_MEM_AL + " TEXT" + ");";






    // drop table sql query

    private String DROP_MEMBER_TABLE = "DROP TABLE IF EXISTS " + TABLE_MEMBER+";";


    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelperMember(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_MEMBER_TABLE);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_MEMBER_TABLE);


        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param member
     */
    public void addMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEM_ID, member.getMemId());
        values.put(COLUMN_MEM_NAME, member.getName());
        values.put(COLUMN_MEM_EMAIL, member.getEmail());
        values.put(COLUMN_MEM_PHONE, member.getPhoneNo());
        values.put(COLUMN_MEM_DEPT, member.getDept());
        values.put(COLUMN_MEM_AL, member.getAccessLevel());

        // Inserting Row
        db.insert(TABLE_MEMBER, null, values);
        db.close();
    }



    /**
     * This method to update user record
     *
     * @param member
     */
    public void updateUser(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEM_ID, member.getMemId());
        values.put(COLUMN_MEM_NAME, member.getName());
        values.put(COLUMN_MEM_EMAIL, member.getEmail());
        values.put(COLUMN_MEM_PHONE, member.getPhoneNo());
        values.put(COLUMN_MEM_DEPT, member.getDept());
        values.put(COLUMN_MEM_AL, member.getAccessLevel());

        // updating row
        db.update(TABLE_MEMBER, values, COLUMN_MEM_ID + " = ?",
                new String[]{String.valueOf(member.getMemId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param member
     */
    public void deleteMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_MEMBER, COLUMN_MEM_ID + " = ?",
                new String[]{String.valueOf(member.getMemId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param memid
     * @return true/false
     */
    public boolean checkMember(String memid) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_MEM_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_MEM_ID + " = ?";

        // selection argument
        String[] selectionArgs = {memid};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_id = '26573';
         */
        Cursor cursor = db.query(TABLE_MEMBER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @return true/false
     */
   /* public boolean checkUser(String memid, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_ID + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {memid, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        /*Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        if (cursorCount > 0) {
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }*/

    public List<Member> getAllMember() {
        // array of columns to fetch
        String[] columns = {
                "memID",
                "name",
                "email",
                "mobile",
                "dept",
                "access_level"

        };
        // sorting orders
        String sortOrder =
                "memID" + " ASC";
        List<Member> memberList = new ArrayList<Member>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query("member", //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Member member = new Member();
                member.setMemId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("memID"))));
                member.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                member.setName(cursor.getString(cursor.getColumnIndex("name")));
                member.setAccessLevel(cursor.getString(cursor.getColumnIndex("access_level")));
                member.setDept(cursor.getString(cursor.getColumnIndex("dept")));
                member.setPhoneNo(cursor.getString(cursor.getColumnIndex("mobile")));
                // Adding user record to list
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return memberList;
    }



}
