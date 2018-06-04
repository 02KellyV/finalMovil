package com.clases.carlosponton.tutorstudend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Intent i;
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView view = (TextView) findViewById(R.id.btnRegister);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this,SingupActivity.class);
                startActivity(i);
            }

        });

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            i = new Intent(this,HomeActivity.class);
            finish();
            startActivity(i);
        }

        txtEmail = (EditText)  findViewById(R.id.txtEmail);
        txtPassword = (EditText)  findViewById(R.id.txtPassword);
    }

    public void login(View v){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, getResources().getString(R.string.error_empty_email), Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, getResources().getString(R.string.error_empty_password), Toast.LENGTH_SHORT).show();
            }else{
                progressDialog.setMessage(getResources().getString(R.string.registering_user));
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            i = new Intent(MainActivity.this,HomeActivity.class);
                            finish();
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.error_registered), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

}
