package com.cos.fireapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {

    private static final String TAG = "JoinActivity";
    private FirebaseAuth mAuth;
    private TextInputEditText teEmail, tePassword;
    private MaterialButton btnJoin;
    private LinearLayout joinLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mAuth = FirebaseAuth.getInstance();
        teEmail = findViewById(R.id.te_email);
        tePassword = findViewById(R.id.te_password);
        joinLayout = findViewById(R.id.join_layout);
        btnJoin = findViewById(R.id.btn_join);

        btnJoin.setOnClickListener(v -> {
            String email = teEmail.getText().toString().trim(); // .trim 앞뒤로 공백을 없애준다.
            String password = tePassword.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, task -> {
                       if (task.isSuccessful()){
                           FirebaseUser user = mAuth.getCurrentUser();
                           Log.d(TAG, "onCreate: user" + user.getEmail());
                           Intent intent = new Intent(JoinActivity.this,HomeActivity.class);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);
                           
                       }else{
                           Log.d(TAG, "onCreate: 로그인 실패" + task.getException());
                           // 요즘은 Toast보다 밑에서 올라오는
                           Snackbar.make(joinLayout,"로그인 실패",Snackbar.LENGTH_LONG).show();
                       }
                    });
        });
    }
}