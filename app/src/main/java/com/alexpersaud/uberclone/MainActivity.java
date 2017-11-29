package com.alexpersaud.uberclone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public void getStarted(View view){
        SwitchCompat userTypeSwitch = (SwitchCompat) findViewById(R.id.userTypeSwitch);
        Toast.makeText(this, "not really sure what to do now", Toast.LENGTH_LONG).show();

        String userType = "rider";

        if(userTypeSwitch.isChecked()){
            userType = "driver";
        }

        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ParseUser.logOut();
        if (ParseUser.getCurrentUser() == null){
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null){
                        Log.i("anon log", "success");

                    }else{
                        Log.i("anon log", "fail");
                    }
                }
            });
        }



        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
