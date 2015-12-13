package cse280.fitfastapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;



import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class CreateAccount extends AppCompatActivity {

    SharedPreferences sPref;
    private final String PREFS = "SharedPreferences";
    private final String genderKey = "gender";
    private final String activityKey = "activity_level";
    private final String timeKey = "time_frame";
    private final String nameKey = "name";
    private final String ageKey = "age";
    private final String heightKey = "height";
    private final String weightKey = "weight";
    private final String goalKey = "goal";
    private final String passwordKey = "password";
    private final String desiredWorkoutsKey = "desired_workouts";
    private Context mContext;


    RadioButton male;
    RadioButton female;
    RadioButton one;
    RadioButton two;
    RadioButton three;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sPref = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.three);
        three = (RadioButton) findViewById(R.id.six);
        mContext = getApplicationContext();


    }

    @Override
    public void onResume(){
        super.onResume();

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        SharedPreferences.Editor editor = sPref.edit();
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    editor.putString(genderKey,"Male");
                break;
            case R.id.female:
                if (checked)
                    editor.putString(genderKey, "Female");
                break;

            case R.id.one:
                if (checked)
                    editor.putString(timeKey,"3");
                break;
            case R.id.three:
                if (checked)
                    editor.putString(timeKey,"6");
                break;
            case R.id.six:
                if (checked)
                    editor.putString(timeKey,"12");
                break;
        }
        editor.commit();
    }


    @TargetApi(21)
    public void genderRed(){

        male.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.RED));
        female.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.RED));
    }

    @TargetApi(21)
    public void timeRed(){

        one.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.RED));
        two.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.RED));
        three.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.RED));
    }

    @TargetApi(21)
    public void clearRed(){
        try {
            male.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.DKGRAY));
            female.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.DKGRAY));
            one.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.DKGRAY));
            two.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.DKGRAY));
            three.setButtonTintList(android.content.res.ColorStateList.valueOf(Color.DKGRAY));
        }
        catch (NoSuchMethodError e){}

    }

    public void done(View view){

        SharedPreferences.Editor editor = sPref.edit();
        EditText editDesiredWorkouts = (EditText) findViewById(R.id.set_desired_workouts);
        EditText editName = (EditText) findViewById(R.id.set_name);
        EditText editAge = (EditText) findViewById(R.id.set_age);
        EditText editActivityLevel = (EditText) findViewById(R.id.set_activity_level);
        EditText editHeight = (EditText) findViewById(R.id.set_height);
        EditText editWeight = (EditText) findViewById(R.id.set_weight);
        EditText editGoal = (EditText) findViewById(R.id.set_goal);
        EditText editPassword = (EditText) findViewById(R.id.set_password);
        int errors = 0;
        String error = "";
        String name = editName.getText().toString().toLowerCase().trim();
        String password = editPassword.getText().toString().trim();


        clearRed();


        if(male.isChecked() == false && female.isChecked() == false){
            errors += 1;
            error = "Please select a gender";
            try {
                genderRed();
            }
            catch (NoSuchMethodError e){}

        }

        if(one.isChecked() == false && three.isChecked() == false && two.isChecked() == false){
            errors += 1;
            error = "Please select a timeframe";
            try {
                timeRed();
            }
            catch (NoSuchMethodError e){}
        }

        try {
            if (1 > Integer.parseInt(editDesiredWorkouts.getText().toString()) || Integer.parseInt(editDesiredWorkouts.getText().toString()) > 14) {
                errors +=1;
                error = "Please enter a desired # of workouts between 1 and 14";
                editDesiredWorkouts.setText(null);
                editDesiredWorkouts.setHintTextColor(Color.RED);


            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a desired # of workouts between 1 and 14";
            editDesiredWorkouts.setText(null);
            editDesiredWorkouts.setHintTextColor(Color.RED);
        }

        try {
            if (editName.getText().toString().length() > 10 || editName.getText().toString().equals("")) {
                errors +=1;
                error = "Please enter a name between 1 and 10 charecters long";
                editName.setText(null);
                editName.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a name between 1 and 10 charecters long";
            editName.setText(null);
            editName.setHintTextColor(Color.RED);
        }

        try {
            if (1 > Integer.parseInt(editAge.getText().toString()) || Integer.parseInt(editAge.getText().toString()) > 120) {
                errors +=1;
                error = "Please enter a valid age";
                editAge.setText(null);
                editAge.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a valid age";
            editAge.setText(null);
            editAge.setHintTextColor(Color.RED);
        }

        try {
            if (1 > Integer.parseInt(editActivityLevel.getText().toString()) || Integer.parseInt(editActivityLevel.getText().toString()) > 5) {
                errors +=1;
                error = "Please enter an activity level between 1 and 5";
                editActivityLevel.setText(null);
                editActivityLevel.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter an activity level between 1 and 5";
            editActivityLevel.setText(null);
            editActivityLevel.setHintTextColor(Color.RED);
        }

        try {
            if (1 > Integer.parseInt(editHeight.getText().toString()) || Integer.parseInt(editHeight.getText().toString()) > 120) {
                errors +=1;
                error = "Please enter a valid height";
                editHeight.setText(null);
                editHeight.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a valid height";
            editHeight.setText(null);
            editHeight.setHintTextColor(Color.RED);
        }

        try {
            if (1 > Integer.parseInt(editWeight.getText().toString()) || Integer.parseInt(editWeight.getText().toString()) > 999) {
                errors +=1;
                error = "Please enter a valid weight";
                editWeight.setText(null);
                editWeight.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a valid weight";
            editWeight.setText(null);
            editWeight.setHintTextColor(Color.RED);
        }

        try {
            if (1 > Integer.parseInt(editGoal.getText().toString()) || Integer.parseInt(editGoal.getText().toString()) > 999) {
                errors +=1;
                error = "Please enter a valid goal weight";
                editGoal.setText(null);
                editGoal.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a valid goal weight";
            editGoal.setText(null);
            editGoal.setHintTextColor(Color.RED);
        }

        try {
            if (editPassword.getText().toString().length() > 16 || editPassword.getText().toString().length() < 1) {
                errors +=1;
                error = "Please enter a password between 1 and 16 characters";
                editPassword.setText(null);
                editPassword.setHintTextColor(Color.RED);
            }
        }
        catch(Exception e){
            errors +=1;
            error = "Please enter a password between 1 and 16 characters";
            editPassword.setText(null);
            editPassword.setHintTextColor(Color.RED);
        }

        if(errors > 0){
            if(errors < 2){
                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(mContext, "Please correct the fields marked in red", Toast.LENGTH_LONG).show();
            }
        }

        else{
            DecimalFormat df = new DecimalFormat("###0.00");
            double bmi = fitfast.fitness.User.CalcBMI(Double.parseDouble(editWeight.getText().toString()), Double.parseDouble(editHeight.getText().toString()));
            boolean gender = false;
            if(sPref.getString(genderKey,"").equals("Male")){
                gender = false;
            }
            else{
                gender = true;
            }
            double daily = fitfast.fitness.User.CalcDailyCals(gender, Double.parseDouble(editWeight.getText().toString()),
                    Double.parseDouble(editHeight.getText().toString()),Integer.parseInt(editAge.getText().toString()),Integer.parseInt(editActivityLevel.getText().toString()));

            int idaily = (int) daily;

            int days = 0;
            int time = Integer.parseInt(sPref.getString(timeKey, ""));
            if(time == 3)
                days = 60;
            else if(time == 6)
                days = 120;
            else if(time == 12)
                days = 240;


            int targetCals = (int)(idaily + fitfast.fitness.Prediction.calcDailyCals(Double.parseDouble(editGoal.getText().toString()) ,Double.parseDouble(editWeight.getText().toString()),days ));
            int difference = (int)(fitfast.fitness.Prediction.calcDailyCals(Double.parseDouble(editGoal.getText().toString()) ,Double.parseDouble(editWeight.getText().toString()),days ));


            editor.putString(nameKey, editName.getText().toString());
                    editor.putString(ageKey, editAge.getText().toString());
                    editor.putString(activityKey, editActivityLevel.getText().toString());
                    editor.putString(heightKey, editHeight.getText().toString());
                    editor.putString(weightKey, editWeight.getText().toString());
                    editor.putString(goalKey, editGoal.getText().toString());
                    editor.putString(passwordKey, editPassword.getText().toString());
                    editor.putString(desiredWorkoutsKey, editDesiredWorkouts.getText().toString());
                    editor.putString("start", editWeight.getText().toString());
            editor.putString("daily", Integer.toString(idaily));

            editor.putString("bmi", df.format(bmi));

            editor.putString("target", Integer.toString(targetCals));

            editor.putString("diff", Integer.toString(difference));


            editor.commit();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}