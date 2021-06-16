package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ShowResults extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseAccess databaseAccess;
    ArrayList<String> id, title, picture, time, instructions, ingredients;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);


        id = new ArrayList<>();
        title = new ArrayList<>();
        picture = new ArrayList<>();
        time = new ArrayList<>();
        instructions = new ArrayList<>();
        ingredients = new ArrayList<>();

        customAdapter = new CustomAdapter(ShowResults.this, title, time);



        databaseAccess = DatabaseAccess.getInstance(ShowResults.this);
        databaseAccess.open();
        ArrayList<String> finalIDArrayList = new ArrayList<>();
        ArrayList<String> idSearchHelper = new ArrayList<>();

        ArrayList<String> ingredientsList = getIntent().getStringArrayListExtra("ingredientsList");




        for (int i = 0; i<ingredientsList.size(); i++){
            Log.i("testowanie_w_nowej_aktywności", finalIDArrayList.toString());
            Log.i("SearchHelper", idSearchHelper.toString());
            if (i == 0){
                finalIDArrayList = databaseAccess.extractRecipesIdsFromIngredientsTables(ingredientsList.get(i));
                idSearchHelper = deepCopy(finalIDArrayList);
            } else {
                finalIDArrayList.retainAll(databaseAccess.extractRecipesIdsFromIngredientsTables(ingredientsList.get(i)));
                Log.i("testowanie_w_nowej_aktywności", String.valueOf(finalIDArrayList.size()));
                if (finalIDArrayList.size() == 0){

                    finalIDArrayList = deepCopy(idSearchHelper);
                } else {
                    idSearchHelper = deepCopy(finalIDArrayList);
                }
            }
        }

        moveDataExtractedFromDataBaseIntoArrays(finalIDArrayList);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowResults.this));

        Log.i("testowanie_w_nowej_aktywności", title.toString());
    }

    void moveDataExtractedFromDataBaseIntoArrays(ArrayList<String> idsToSelectRecipes){
        Cursor cursor = databaseAccess.selectAllRecipiesOfGivenIDs(idsToSelectRecipes);
        if (cursor == null || cursor.getCount()==0) {return;}
        while (cursor.moveToNext()){
            id.add(cursor.getString(0));
            title.add(cursor.getString(1));
            picture.add(cursor.getString(2));
            time.add(cursor.getString(3));
            instructions.add(cursor.getString(4));
            //ingredients.add(cursor.getString(5));
        }
    }

    ArrayList<String> deepCopy (ArrayList<String> oldArray){
        ArrayList<String> deepCopied = new ArrayList<>(oldArray);
        return deepCopied;
    }

}