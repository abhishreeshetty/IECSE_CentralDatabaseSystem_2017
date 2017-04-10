package com.androidtutorialshub.loginregister.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidtutorialshub.loginregister.model.Member;
import com.androidtutorialshub.loginregister.model.NonMember;
import com.androidtutorialshub.loginregister.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "IECSE_CENTRAL_DATABASE.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_EVENT = "event";
    private static final String TABLE_MEMBER = "member";
    private static final String TABLE_ATTENDANCE = "attendance";
    private static final String TABLE_NON_MEMBER = "non_member";
    private static final String TABLE_REGISTRATION = "registration";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "member_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_REG_NO="registration_number";
    private static final String COLUMN_PHONE_NO="phone_number";


    private static final String COLUMN_MEM_ID = "memID";
    private static final String COLUMN_MEM_NAME = "name";
    private static final String COLUMN_MEM_EMAIL = "email";
    private static final String COLUMN_MEM_PHONE = "mobile";
    private static final String COLUMN_MEM_DEPT = "dept";
    private static final String COLUMN_MEM_AL="access_level";

    private static final String COLUMN_E_ID = "eventID";
    private static final String COLUMN_E_NAME = "name";
    private static final String COLUMN_E_VENUE = "venue";
    private static final String COLUMN_E_TYPE = "type";


    private static final String COLUMN_NM_ID = "NMID";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "( "
            + COLUMN_USER_ID + " INT PRIMARY KEY, " + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, "  + COLUMN_REG_NO + " TEXT, "
            + COLUMN_PHONE_NO + " TEXT" + ");";

    private String CREATE_MEMBER_TABLE="CREATE TABLE " + TABLE_MEMBER + "(" +
            COLUMN_MEM_ID + " INT AUTO INCREMENT PRIMARY KEY, " + COLUMN_MEM_NAME + " TEXT, "
            + COLUMN_MEM_EMAIL + " TEXT, " + COLUMN_MEM_PHONE + " TEXT, "  + COLUMN_MEM_DEPT + " TEXT, "
            + COLUMN_MEM_AL + " TEXT" + ");";

    private String CREATE_NON_MEMBER_TABLE="CREATE TABLE " + TABLE_NON_MEMBER + "(" +
            COLUMN_NM_ID + " INT AUTO INCREMENT PRIMARY KEY, " + COLUMN_MEM_NAME + " TEXT, "
            + COLUMN_MEM_EMAIL + " TEXT, " + COLUMN_MEM_PHONE + " TEXT "  + ");";



    private String CREATE_EVENT_TABLE="CREATE TABLE " + TABLE_EVENT + "(" +
            COLUMN_E_ID + " INT AUTO INCREMENT PRIMARY KEY, " + COLUMN_E_NAME + " TEXT, "
            + COLUMN_E_VENUE + " TEXT, " + COLUMN_E_TYPE + " TEXT check("+ COLUMN_E_TYPE+ " in ('Dev','Web','Tech','Design','Others')));";

    private String CREATE_ATTENDANCE_TABLE="CREATE TABLE " + TABLE_ATTENDANCE + "(" +
            COLUMN_MEM_ID + " INT ," + COLUMN_NM_ID + " INT, "
            + COLUMN_E_ID + " INT, " + "primary key(memID,eventID,nmID),"+
    "foreign key(eventID) references event(eventID),"+
            "foreign key(NMID) references non_member(NMID),"+
    "foreign key(memID) references member(memID)" + ");";

    private String CREATE_REGISTRATION_TABLE="CREATE TABLE " + TABLE_REGISTRATION + "(" +
            COLUMN_MEM_ID + " INT ," + COLUMN_NM_ID + " INT, "
            + COLUMN_E_ID + " INT, " + "primary key(memID,eventID,nmID),"+
            "foreign key(eventID) references event(eventID),"+
            "foreign key(NMID) references non_member(NMID),"+
            "foreign key(memID) references member(memID)" + ");";





    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER+";";
    private String DROP_MEMBER_TABLE = "DROP TABLE IF EXISTS " + TABLE_MEMBER+";";
    private String DROP_NON_MEMBER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NON_MEMBER+";";
    private String DROP_EVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_EVENT+";";
    private String DROP_ATTENDANCE_TABLE = "DROP TABLE IF EXISTS " + TABLE_ATTENDANCE+";";
    private String DROP_REGISTRATION_TABLE = "DROP TABLE IF EXISTS " + TABLE_REGISTRATION+";";

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_MEMBER_TABLE);
        db.execSQL(CREATE_NON_MEMBER_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);
        db.execSQL(CREATE_ATTENDANCE_TABLE);
        db.execSQL(CREATE_REGISTRATION_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);


        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getMemId());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_REG_NO, user.getRegNo());
        values.put(COLUMN_PHONE_NO, user.getPhoneNo());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_REG_NO,
                COLUMN_PHONE_NO

        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setMemId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setRegNo(cursor.getInt(cursor.getColumnIndex(COLUMN_REG_NO)));
                user.setPhoneNo(cursor.getInt(cursor.getColumnIndex(COLUMN_PHONE_NO)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getMemId());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_REG_NO, user.getRegNo());
        values.put(COLUMN_PHONE_NO, user.getPhoneNo());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getMemId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getMemId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param memid
     * @return true/false
     */
    public boolean checkUser(String memid) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_ID + " = ?";

        // selection argument
        String[] selectionArgs = {memid};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_id = '26573';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
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
     * @param memid
     * @param password
     * @return true/false
     */
    public boolean checkUser(String memid, String password) {

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
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

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


    public List<NonMember> getAllNonMember() {
        // array of columns to fetch
        String[] columns = {
                "nmID",
                "name",
                "email",
                "mobile"

        };
        // sorting orders
        String sortOrder =
                "nmID" + " ASC";
        List<NonMember> nonMemberList = new ArrayList<NonMember>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query("non_member", //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NonMember nonMember = new NonMember();
                nonMember.setNMId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("nmID"))));
                nonMember.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                nonMember.setName(cursor.getString(cursor.getColumnIndex("name")));
                nonMember.setPhoneNo(cursor.getString(cursor.getColumnIndex("mobile")));
                // Adding user record to list
                nonMemberList.add(nonMember);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return nonMemberList;
    }
}
