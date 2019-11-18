package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aldricklevina.hadir.Model.Account;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private Button btnLogin, btnRegis;
    private EditText editTextPass, editTextEmail;
    private Intent intent;
    private ArrayList<Account> listAcc;
    private Boolean loginSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intent = getIntent();

        if (intent != null) {
            listAcc = intent.getParcelableArrayListExtra("listAcc");
        }

        btnLogin = findViewById(R.id.btnLogin_login);
        btnRegis = findViewById(R.id.btnRegis_login);
        editTextEmail = findViewById(R.id.editTextEmail_login);
        editTextPass = findViewById(R.id.editTextPass_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Account acc : listAcc) {
                    if (editTextEmail.getText().toString().equals(acc.email) && editTextPass.getText().toString().equals(acc.password)) {
                        loginSuccess = true;
                        break;
                    }
                }

                if (loginSuccess) {
                    intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("isLogin", true);
                    startActivity(intent);
                    finish();
                } else {
                    editTextEmail.setError("Wrong Email");
                    editTextPass.setError("Wrong Password");
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Login.this, Register.class);
                intent.putParcelableArrayListExtra("listAcc", listAcc);
                startActivity(intent);
            }
        });
    }
}
