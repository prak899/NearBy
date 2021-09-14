package in.pm.nearapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import java.util.Locale;

import in.pm.nearapp.R;

public class SignIn extends AppCompatActivity {
    TextInputEditText User_Number;
    SharedPreferences sharedpreferences;

    View shine;
    TextView ChooseLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        init();

        sharedpreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        int languageID = sharedpreferences.getInt("language_type", 0);
        setLocaleById(languageID);

        ChooseLanguage.setOnClickListener(v-> {
            changeLanguageDialog();
        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 20 seconds
                handler.postDelayed(this, 2000);
                shineAnimation();
            }
        }, 2000);  //the time is in miliseconds

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private void init() {
        User_Number= findViewById(R.id.number);

        shine = findViewById(R.id.shine);
        sharedpreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        shineAnimation();
        ChooseLanguage = findViewById(R.id.chooseLanguage);
    }

    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_right);
        shine.startAnimation(anim);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

    public void signup(View v){
        String phoneNo = User_Number.getText().toString();
        Intent intent = new Intent(getApplicationContext(),IntroActivity.class);
        intent.putExtra("phoneNo",phoneNo);
        startActivity(intent);

    }
    private void changeLanguageDialog() {
        new AlertDialog.Builder(SignIn.this)
                .setTitle("Set language")
                .setMessage("If you want to change language press ok")
                .setCancelable(false)
                .setPositiveButton("Hindi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLocale(1);
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLocale(0);
                    }
                })
                .show();
    }
    public void setLocale(int languageId) {
        Locale locale = null;
        //int language_type = sharedpreferences.getInt("language_type", 1);
        if (languageId == 1) {
            locale = new Locale("hi");
            sharedpreferences.edit().putInt("language_type", languageId).apply();
        } else if(languageId == 0){
            locale = new Locale("en");
            sharedpreferences.edit().putInt("language_type", languageId).apply();
        }


        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //restart Activity
        finish();
        startActivity(getIntent());

    }
    public void setLocaleById(int languageId) {
        Locale locale;
        //int language_type = sharedpreferences.getInt("language_type", 1);
        if (languageId == 1) {
            locale = new Locale("hi");
            //sharedpreferences.edit().putInt("language_type", languageId).apply();
        } else {
            locale = new Locale("en");
            //sharedpreferences.edit().putInt("language_type", languageId).apply();
        }


        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Log.d("XlangIDX", String.valueOf(languageId));
    }
}