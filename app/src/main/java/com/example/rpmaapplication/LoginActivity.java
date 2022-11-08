package com.example.rpmaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView textreg;
    EditText etusername,etpassword;
    Button btnlog;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textreg=findViewById(R.id.textreg);
        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        btnlog=findViewById(R.id.btnlog);
        DB=new DBHelper(this);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid=etusername.getText().toString();
                String password=etpassword.getText().toString();
                if(userid.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();

                }
                else{
                    boolean verifyboth=DB.verifyusernamepassword(userid,password);
                    if(verifyboth==true){
                        Toast.makeText(LoginActivity.this, "login successfull", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),specificLocationActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        textreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}