package com.example.mtvi_ver2.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mtvi_ver2.database.DBHelper;
import com.example.mtvi_ver2.database.data.DataMovies;

import java.util.ArrayList;

public class MoviesDAO {

    /*=============================================================================================*/
    /*---read movies---*/
    /*=============================================================================================*/

    public static ArrayList<DataMovies> readMovies(Context context){
        ArrayList<DataMovies> dataMovies = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Movies", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int movie_id = cursor.getInt(0);
            /**/
            String movie_image = cursor.getString(1);
            String movie_name = cursor.getString(2);
            String movie_genres = cursor.getString(3);
            String movie_detail = cursor.getString(4);
            String movie_link = cursor.getString(5);

            DataMovies movies = new DataMovies(movie_id, movie_image, movie_name, movie_genres, movie_detail, movie_link);
            dataMovies.add(movies);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return dataMovies;
    }

    /*=============================================================================================*/
    /*---insert movies---*/
    /*=============================================================================================*/

    public static boolean insertMovies(Context context, DataMovies dataMovies){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("movie_image", dataMovies.getMovie_image());
        values.put("movie_name", dataMovies.getMovie_name());
        values.put("movie_genres", dataMovies.getMovie_genres());
        values.put("movie_detail", dataMovies.getMovie_detail());
        values.put("movie_link", dataMovies.getMovie_link());

        long result = database.insert("Movies", null, values);
        return (result > 0);
    }

    /*=============================================================================================*/
    /*---update movies---*/
    /*=============================================================================================*/

    public static boolean updateMovies(Context context, DataMovies dataMovies){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("movie_image", dataMovies.getMovie_image());
        values.put("movie_name", dataMovies.getMovie_name());
        values.put("movie_genres", dataMovies.getMovie_genres());
        values.put("movie_detail", dataMovies.getMovie_detail());
        values.put("movie_link", dataMovies.getMovie_link());

        long result = database.update("Movies", values, "movie_id=?", new String[]{dataMovies.getMovie_id() + ""});
        return (result > 0);
    }

    /*=============================================================================================*/
    /*---delete movies---*/
    /*=============================================================================================*/

    public static boolean deleteMovies(Context context, int movie_id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        long result = database.delete("Movies","movie_id=?", new String[]{movie_id + ""});
        return (result > 0);
    }
}
