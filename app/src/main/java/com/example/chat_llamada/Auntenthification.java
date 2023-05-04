package com.example.chat_llamada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chat_llamada.databinding.ActivityAuntenthificationBinding;
import com.example.chat_llamada.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class Auntenthification extends AppCompatActivity {
    String Correo, Contrasenia,nombre;
    DatabaseReference databaseReference;
    ActivityAuntenthificationBinding binding;
    private Switch aSwitch;
    private TextView emailtext;
    private TextView passwordtext;
    private Button logintext;
    private TextView crearnuevacuentatext;
    private TextView newusertext;
    private TextView createnewaccounttext;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAuntenthificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Correo = binding.correo.getText().toString();
                Contrasenia = binding.Contrasenia.getText().toString();
                nombre = binding.Nombre.getText().toString();
                Login();
            }
        });

        binding.Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Correo = binding.correo.getText().toString();
                Contrasenia = binding.Contrasenia.getText().toString();
                nombre = binding.Nombre.getText().toString();
                SignIn();
            }
        });

        SwitchCompat switchLanguage = findViewById(R.id.idioma);

        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                LanguageHelper.updateLanguage(this, "es");
            } else {
                LanguageHelper.updateLanguage(this, "en");
            }

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!= null){
            startActivity(new Intent(Auntenthification.this,menuPrincipalUser.class));
            finish();
        }
    }

    private void Login(){
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(Correo.trim(),Contrasenia)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.e("nombre",nombre);
                        if (nombre.equals("administrador"))
                        {
                            startActivity(new Intent(Auntenthification.this,menuPrincipalAdmin.class));
                            finish();
                        }
                       else{
                            startActivity(new Intent(Auntenthification.this,menuPrincipalUser.class));
                            finish();
                        }
                    }
                });

    }
    private void SignIn(){
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(Correo.trim(),Contrasenia)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(nombre).build();
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        userModel userModel = new userModel(FirebaseAuth.getInstance().getUid(),Correo,nombre,Contrasenia);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);

                        if (nombre.equals("administrador"))
                        {
                            startActivity(new Intent(Auntenthification.this,menuPrincipalAdmin.class));
                            finish();
                        }
                        else{
                            startActivity(new Intent(Auntenthification.this,menuPrincipalUser.class));
                            finish();
                        }

                    }
                }).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Log.d(String.valueOf(this), "createUserWithEmail:success");
                            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification();
                            if(nombre.equals("administrador"))
                            {
                                Intent i = new Intent(getApplicationContext(), menuPrincipalAdmin.class);
                                startActivity(i);
                            }
                            else{
                                Intent i = new Intent(getApplicationContext(), menuPrincipalUser.class);
                                startActivity(i);
                            }

                            // updateUI(user);
                        }else{
                            Log.w(String.valueOf(this), "createUserWithEmial:failure",task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });

    }


    }


