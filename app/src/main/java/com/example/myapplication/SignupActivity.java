package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    private ProgressDialog loadingbar;
    private EditText InputName,InputPhoneNumber,InputPassword;
    private String name, phonenumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadingbar = new ProgressDialog(SignupActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //redirecting to Login Page
        TextView Re_LOGIN;
        Re_LOGIN = findViewById(R.id.btnLogin);
        Re_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Getting the button
        Button btnUpsign;
        btnUpsign = findViewById(R.id.btnSignUp);


        //Click Listener For Signup
        btnUpsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpUser();
            }
        });

    }

    private void SignUpUser()
    {

        InputName = findViewById(R.id.userName);
        InputPhoneNumber =findViewById(R.id.phoneNumber);
        InputPassword =findViewById(R.id.Password);
        name = InputName.getText().toString();
        phonenumber = InputPhoneNumber.getText().toString();
        password = InputPassword.getText().toString();


        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please write your Name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phonenumber))
        {
            Toast.makeText(this,"Please write your Phone Number...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write your Password...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait for a while...");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            ValidateUser(name, phonenumber, password);
        }

    }

    private void ValidateUser(String name, String phonenumber, String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (!(snapshot.child("Users").child(phonenumber).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name",name);
                    userdataMap.put("phone",phonenumber);
                    userdataMap.put("password",password);

                    RootRef.child("Users").child(phonenumber).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SignupActivity.this,"Your account has been created. Congratulations",Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingbar.dismiss() ;
                                        Toast.makeText(SignupActivity.this,"There is some error, please try again",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }
                else
                {
                    Toast.makeText(SignupActivity.this, "This " + phonenumber + "already exist",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(SignupActivity.this, "Please try gain with another Phone Number ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginIntro.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}