package com.example.endofyearassignment2025;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etUserName;
    EditText etPassword;
    Button btnLogin;
    //רשימה של שמות משתמש וסיסמאות לבדיקה
    String[] arrayUserName = {"hadas","noa","lior"};
    String[] arrayPassword = {"1234","5678","abcd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValidUser = false;
                //בדיקת התאמת שם המשתמש וסיסמה
                for(int i=0; i<arrayUserName.length; i++){
                    if(arrayUserName[i].equals(etUserName.getText().toString()) && arrayPassword[i].equals(etPassword.getText().toString()))
                    {
                        isValidUser = true;
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("userName", etUserName.getText().toString().trim());
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
                if(!isValidUser){
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}