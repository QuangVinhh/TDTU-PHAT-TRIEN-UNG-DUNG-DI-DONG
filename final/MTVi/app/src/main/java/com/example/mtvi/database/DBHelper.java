package com.example.mtvi.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "DatabaseMTVi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Movies(movie_id integer primary key autoincrement," +
                "movie_name text, movie_genres text, movie_description text, movie_image integer, movie_linked text)";
        sqLiteDatabase.execSQL(sql);

//        /*---insert hard code--*/
        sql = "Insert Into Movies Values(null, 'Stand By Me I', 'Anime, Kid', 'Doraemon I', 0, 'https://www.youtube.com/watch?v=dnRAVwBBRRA')";
        sqLiteDatabase.execSQL(sql);
//        sql = "Insert Into Movies Values(null, 'Stand By Me II', 'Anime, Kid', 'Doraemon II', 0)";
//        sqLiteDatabase.execSQL(sql);
//        sql = "Insert Into Movies Values(null, 'Harry Potter and the Sorcerers Stone', 'Adventure, Family', 'Harry Potter', 0)";
//        sqLiteDatabase.execSQL(sql);
//        sql = "Insert Into Movies Values(null, 'Harry Potter and the Chamber of Secrets', 'Adventure, Family', 'Harry Potter', 0)";
//        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Movies");
        onCreate(sqLiteDatabase);
    }

}
