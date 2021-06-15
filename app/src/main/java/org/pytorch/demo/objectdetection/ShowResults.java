package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ShowResults extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<StringBuffer> ingredientsList = (ArrayList<StringBuffer>) getIntent().getSerializableExtra("ingredientsList");
        Log.i("testowanie_w_nowej", ingredientsList.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);
    }
}