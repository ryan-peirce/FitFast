package cse280.fitfastapp;

import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText mEmailView, mPasswordView;
    Button loginButton, accountButton;
    private final String PREFS = "SharedPreferences";
    private SharedPreferences sPref;

    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = getApplicationContext();


        sPref = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        // Get Views from Login activity
        mEmailView = (EditText) findViewById(R.id.userName);
        mEmailView.setText("");
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setText("");
        loginButton = (Button) findViewById(R.id.email_sign_in_button);
        accountButton = (Button) findViewById(R.id.create_an_account);



    }
    public void onResume(){
        super.onResume();
        mEmailView = (EditText) findViewById(R.id.userName);
        mEmailView.setText("");
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setText("");
    }

    public void createNew(View view){
        Intent intent = new Intent(this, CreateAccount.class);

        startActivityForResult(intent, RESULT_OK);


    }

    public void login(View view) {
        Intent intent = new Intent(this, DashBoard.class);
        String loginString = mEmailView.getText().toString().trim();
        String passString = mPasswordView.getText().toString().trim();

        String login = sPref.getString("name","");
        String pass = sPref.getString("password","");

        if (loginString.equals(login) && passString.equals(pass)){
            startActivityForResult(intent, RESULT_OK);
        }
        else{
            Toast.makeText(mContext, "The username or password is incorrect", Toast.LENGTH_LONG).show();
        }


    }
}

