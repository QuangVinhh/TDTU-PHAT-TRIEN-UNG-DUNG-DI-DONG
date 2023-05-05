package com.example.mtvi_ver2.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.database.DBHelper;

import java.util.ArrayList;

public class ServicesDAO {

    /*=============================================================================================*/
    /*---read services---*/
    /*=============================================================================================*/

    public static ArrayList<DataServices> readServices(Context context){
        ArrayList<DataServices> dataServices = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Services", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int service_id = cursor.getInt(0);
            String service_name = cursor.getString(1);
            String service_price = cursor.getString(2);
            String service_detail = cursor.getString(3);

            DataServices services = new DataServices(service_id, service_name, service_price, service_detail);
            dataServices.add(services);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return dataServices;
    }

    /*=============================================================================================*/
    /*---insert services---*/
    /*=============================================================================================*/

    public static boolean insertServices(Context context, DataServices dataServices){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("service_name", dataServices.getService_name());
        values.put("service_price", dataServices.getService_price());
        values.put("service_detail", dataServices.getService_detail());

        long result = database.insert("Services", null, values);
        return (result > 0);
    }

    /*=============================================================================================*/
    /*---update services---*/
    /*=============================================================================================*/

    public static boolean updateServices(Context context, DataServices dataServices){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("service_name", dataServices.getService_name());
        values.put("service_price", dataServices.getService_price());
        values.put("service_detail", dataServices.getService_detail());

        long result = database.update("Services", values, "service_id=?", new String[]{dataServices.getService_id() + ""});
        return (result > 0);
    }

    /*=============================================================================================*/
    /*---delete services---*/
    /*=============================================================================================*/

    public static boolean deleteServices(Context context, int service_id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        long result = database.delete("Services","service_id=?", new String[]{service_id + ""});
        return (result > 0);
    }
}
