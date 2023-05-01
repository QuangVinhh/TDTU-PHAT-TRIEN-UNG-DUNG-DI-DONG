package com.example.mtvi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {

    ///*---method || get data movies---*///
    public static List<DataMovies> getAll(Context context){
        List<DataMovies> dataMovies = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Movies", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int movie_id = cursor.getInt(0);
            String movie_name = cursor.getString(1);
            String movie_genres = cursor.getString(2);
            String movie_description = cursor.getString(3);
            int movie_image = cursor.getInt(4);
            String movie_linked = cursor.getString(5);

            DataMovies data = new DataMovies(movie_id, movie_name, movie_genres, movie_description, movie_image, movie_linked);
            dataMovies.add(data);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return dataMovies;
    }

    ///*---method || insert data movies---*///
    public static boolean insert(Context context, String movie_name, String movie_genres, String movie_description, int movie_image, String movie_linked){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("movie_name", movie_name);
        values.put("movie_genres", movie_genres);
        values.put("movie_description", movie_description);
        values.put("movie_image", movie_image);
        values.put("movie_linked", movie_linked);

        long row = database.insert("Movies", null, values);
        return (row>0);
    }

    ///*---method || update data movies---*///
    public static boolean update(Context context, DataMovies dataMovies){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("movie_image", dataMovies.getMovie_image());
        values.put("movie_name", dataMovies.getMovie_name());
        values.put("movie_genres", dataMovies.getMovie_genres());
        values.put("movie_description", dataMovies.getMovie_description());
        values.put("movie_linked", dataMovies.getMovie_linked());

        int row = database.update("Movies", values, "movie_id=?", new String[]{dataMovies.getMovie_id() + ""});
        return (row>0);
    }

    ///*---method || delete data movies---*///
    public static boolean delete(Context context, int movie_id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        int row = database.delete("Movies","movie_id=?", new String[]{movie_id + ""});
        return (row>0);
    }
}