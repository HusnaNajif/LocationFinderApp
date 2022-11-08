package com.example.rpmaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textlogin;
    EditText etusername,etpassword,etrepassword;
    Button btnreg;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textlogin=findViewById(R.id.textlogin);
        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        etrepassword=findViewById(R.id.etrepassword);
        btnreg=findViewById(R.id.btnreg);

        DB=new DBHelper(this);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid=etusername.getText().toString();
                String password=etpassword.getText().toString();
                String repassword=etrepassword.getText().toString();
                if(userid.equals("")||password.equals("")||repassword.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(repassword)){
                        boolean matchuser=DB.verifyusername(userid);
                        if(matchuser==false){
                            boolean insertdata=DB.insertuser(userid,password);
                            if(insertdata==true){
                                Toast.makeText(MainActivity.this, "registration successfull", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "USER EXISTS,PLEASE LOGIN", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(MainActivity.this, "password mismatch,please try again", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

}