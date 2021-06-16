package org.pytorch.demo.objectdetection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private final SQLiteOpenHelper openHelper;
    Cursor cursor = null;
    private SQLiteDatabase db;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db=openHelper.getWritableDatabase();
    }
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public ArrayList<String> extractRecipesIdsFromIngredientsTables (String Name){
        ArrayList<String> ids = new ArrayList<>();
        cursor=db.rawQuery("select * from " + Name, null);
        while (cursor.moveToNext()){
            ids.add(cursor.getString(0));
        }
        return ids;
    }

    public Cursor selectAllRecipiesOfGivenIDs (ArrayList<String> ids){
        if (ids.size() == 0) {return null;}
        String query = "SELECT * FROM recipes WHERE id = " + ids.get(0);
        for (int i = 1; i < ids.size(); ++i){
            query += " OR id = " + ids.get(i);
        }
        cursor=db.rawQuery(query, null);
        return cursor;
    }

}
