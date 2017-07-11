package com.example.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginid,password;
    Button submit;
    String myLoginid, myPassword;
    CheckBox checkBox1, checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        findview();

    }

    public void init(){
        SharedPreferences setting = getSharedPreferences("login",MODE_PRIVATE);

        myLoginid = setting.getString("loginid", "");
        myPassword = setting.getString("password", "");
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox1.setChecked(setting.getBoolean("record", false));
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox2.setChecked(setting.getBoolean("times", false));
    }

    public void findview(){
        loginid = (EditText)findViewById(R.id.loginid);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);

        if(checkBox1.isChecked()==true) {
            loginid.setText(myLoginid);
            password.setText(myPassword);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
    }

    public void doSubmit(){


        if(loginid.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("帳號不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            loginid.setFocusableInTouchMode(true);
            loginid.requestFocus();

        }else if (password.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("密碼不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            password.setFocusableInTouchMode(true);
            password.requestFocus();

        }else {

            Intent intent = new Intent(this, CheckActivity.class);

            Bundle bag = new Bundle();
            bag.putString("loginid", loginid.getText().toString());
            bag.putString("password", password.getText().toString());
            bag.putBoolean("record", checkBox1.isChecked());
            bag.putBoolean("times", checkBox2.isChecked());

            intent.putExtras(bag);
            startActivity(intent);

        }
    }

}
