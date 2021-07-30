package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Model.Users;
import com.example.myapplication.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginIntro extends AppCompatActivity {
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);
        Paper.init(this);
        loadingBar = new ProgressDialog(this);

        //Button to get started
        Button Get_Started;
        Get_Started = findViewById(R.id.btnGetStarted);
        Get_Started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Redirect("LOGIN");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPassWord = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey != "" && UserPassWord != "")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPassWord))
            {
                AllowAccess(UserPhoneKey,UserPassWord);

                loadingBar.setTitle("Already logged in");
                loadingBar.setMessage("Please wait for a while...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

            }
        }
    }

    private void AllowAccess(String phonenum, String pass)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.child("Users").child(phonenum).exists())
                {
                    Users userData = snapshot.child("Users").child(phonenum).getValue(Users.class);

                    if (userData.getPhone().equals(phonenum))
                    {
                        if (userData.getPassword().equals(pass))
                        {
                            Toast.makeText(LoginIntro.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Prevalent.currentOnlineUser = userData;

                            Intent intent = new Intent(LoginIntro.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginIntro.this,"Password in incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginIntro.this,"Account with this " + phonenum + "number do not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginIntro.this,"Please Create new Account",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error)
            {

            }
        });
    }

    private void Redirect(String Name) throws Exception
    {
        if(Name.equals("LOGIN"))
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if (Name.equals("MAIN"))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            throw new Exception("No path Exist");
        }


    }
}