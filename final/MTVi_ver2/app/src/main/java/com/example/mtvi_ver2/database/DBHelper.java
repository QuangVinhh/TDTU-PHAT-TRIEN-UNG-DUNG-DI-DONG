package com.example.mtvi_ver2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    /*=============================================================================================*/
    /*===DATABASE===*/
    /*=============================================================================================*/

    private static final String DATABASE_NAME = "DatabaseMTVi";
    private static final int DATABASE_VERSION = 1;

    /*=============================================================================================*/
    /*===TABLE MOVIES===*/
    /*=============================================================================================*/

    private static final String MOVIES_TABLE = "Movies";
    private static final String MOVIES_ID = "movie_id";
    private static final String MOVIES_IMAGE = "movie_image";
    private static final String MOVIES_NAME = "movie_name";
    private static final String MOVIES_GENRES = "movie_genres";
    private static final String MOVIES_DETAIL = "movie_detail";
    private static final String MOVIES_LINK = "movie_link";

    /*=============================================================================================*/
    /*===TABLE SERVICES===*/
    /*=============================================================================================*/

    private static final String SERVICES_TABLE = "Services";
    private static final String SERVICES_ID = "service_id";
    private static final String SERVICES_NAME = "service_name";
    private static final String SERVICES_PRICE = "service_price";
    private static final String SERVICES_DETAIL = "service_detail";

    /*=============================================================================================*/
    /*===TABLE ACCOUNT===*/
    /*=============================================================================================*/

    private static final String ACCOUNTS_TABLE = "Accounts";
    private static final String ACCOUNTS_ID = "account_id";
    private static final String ACCOUNTS_NAME = "account_name";
    private static final String ACCOUNTS_EMAIL = "account_email";
    private static final String ACCOUNTS_PASSWORD = "account_password";
    private static final String ACCOUNTS_CHECK = "account_check";

    /*=============================================================================================*/
    /*===MAIN===*/
    /*=============================================================================================*/

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*---sql || table movies---*/
        String sql_movies = "CREATE TABLE " + MOVIES_TABLE + " (" + MOVIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                + MOVIES_IMAGE + " TEXT, "
                                                                + MOVIES_NAME + " TEXT, "
                                                                + MOVIES_GENRES + " TEXT, "
                                                                + MOVIES_DETAIL + " TEXT, "
                                                                + MOVIES_LINK + " TEXT);";

        /*---sql || table services---*/
        String sql_services = "CREATE TABLE " + SERVICES_TABLE + " (" + SERVICES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                + SERVICES_NAME + " TEXT, "
                                                                + SERVICES_PRICE + " TEXT, "
                                                                + SERVICES_DETAIL + " TEXT);";

        /*---sql || table accounts---*/
        String sql_accounts = "CREATE TABLE " + ACCOUNTS_TABLE + " (" + ACCOUNTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                + ACCOUNTS_NAME + " TEXT, "
                                                                + ACCOUNTS_EMAIL + " TEXT, "
                                                                + ACCOUNTS_PASSWORD + " TEXT, "
                                                                + ACCOUNTS_CHECK + " TEXT);";

        /*---sql || create table---*/
        sqLiteDatabase.execSQL(sql_movies);
        sqLiteDatabase.execSQL(sql_services);
        sqLiteDatabase.execSQL(sql_accounts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SERVICES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS_TABLE);
        onCreate(sqLiteDatabase);
    }
}
