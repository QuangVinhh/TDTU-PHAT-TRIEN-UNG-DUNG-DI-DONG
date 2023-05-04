package com.example.mtvi_ver2.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mtvi_ver2.database.DBHelper;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.data.DataServices;

import java.util.ArrayList;

public class AccountsDAO {

    /*=============================================================================================*/
    /*---read ONE accounts---*/
    /*=============================================================================================*/

    /*=============================================================================================*/
    /*---read accounts---*/
    /*=============================================================================================*/

    public static ArrayList<DataAccounts> readAccounts(Context context){
        ArrayList<DataAccounts> dataAccounts = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Accounts", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int account_id = cursor.getInt(0);
            String account_name = cursor.getString(1);
            String account_email = cursor.getString(2);
            String account_password = cursor.getString(3);
            String account_check = cursor.getString(4);

            DataAccounts accounts = new DataAccounts(account_id, account_name, account_email, account_password, account_check);
            dataAccounts.add(accounts);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return dataAccounts;
    }

    /*=============================================================================================*/
    /*---insert accounts---*/
    /*=============================================================================================*/

    public static boolean insertAccounts(Context context, DataAccounts dataAccounts){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("account_name", dataAccounts.getAccount_name());
        values.put("account_email", dataAccounts.getAccount_email());
        values.put("account_password", dataAccounts.getAccount_password());
        values.put("account_check", dataAccounts.getAccount_check());

        long result = database.insert("Accounts", null, values);
        return (result > 0);
    }

    /*=============================================================================================*/
    /*---update services---*/
    /*=============================================================================================*/

    public static boolean updateAccounts(Context context, DataAccounts dataAccounts){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("account_name", dataAccounts.getAccount_name());
        values.put("account_email", dataAccounts.getAccount_email());
        values.put("account_password", dataAccounts.getAccount_password());
        values.put("account_check", dataAccounts.getAccount_check());

        long result = database.update("Accounts", values, "account_id=?", new String[]{dataAccounts.getAccount_id() + ""});
        return (result > 0);
    }

    /*=============================================================================================*/
    /*---delete services---*/
    /*=============================================================================================*/

    public static boolean deleteAccounts(Context context, int account_id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        long result = database.delete("Accounts","account_id=?", new String[]{account_id + ""});
        return (result > 0);
    }
}
