package cse280.fitfastapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieInput extends AppCompatActivity {

    private final String PREFS = "SharedPreferences";
    private SharedPreferences sPref;
    Integer caloriesIn;
    final String caloriesKey = "calories_in";
    TextView caloriesEatenDisplay;
    Toolbar toolbar;
    EditText calsIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_input);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        caloriesEatenDisplay = (TextView) findViewById(R.id.calories_eaten_display);
        sPref = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        caloriesIn = sPref.getInt(caloriesKey,0);
    }

    @Override
    public void onResume() {
        super.onResume();
        caloriesIn = sPref.getInt(caloriesKey,0);
        caloriesEatenDisplay.setText(String.valueOf(caloriesIn));
    }

    public void toDash(View view) {
        //finishActivity(RESULT_OK);
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }



    public void addTotal(View view) {
        calsIn = (EditText) findViewById(R.id.calories_eaten);


            int calories;

            if(calsIn.length()<6) {
                calories = Integer.parseInt(calsIn.getText().toString());
                SharedPreferences.Editor editor = sPref.edit();
                editor.putInt(caloriesKey,caloriesIn+=calories);

                editor.commit();

                caloriesEatenDisplay.setText(Integer.toString(caloriesIn));
                calsIn.setText("");
            }
            else    {
                Toast.makeText(getApplicationContext(), "Calories value is too large", Toast.LENGTH_LONG).show();
            }

        }
    }
