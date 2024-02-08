package com.ahmad.calcplay.userData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmad.calcplay.HelperAndAdabter.FireBaseHelper;
import com.ahmad.calcplay.R;
import com.ahmad.calcplay.mainmenu;
import com.ahmad.calcplay.videoplayer.player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
private Button loginBtn,signupLayoutBtn,rememberBtn;
EditText email_login,passwordlogin;
FireBaseHelper fireBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialize button with listener
        loginBtn=findViewById(R.id.loginBtn);
        signupLayoutBtn=findViewById(R.id.signupLayoutBtn);
        rememberBtn=findViewById(R.id.rememberBtn);
        loginBtn.setOnClickListener(this);
        signupLayoutBtn.setOnClickListener(this);
        rememberBtn.setOnClickListener(this);
        //edit text fields
        email_login=findViewById(R.id.email_login);
        passwordlogin=findViewById(R.id.Password_login);

        fireBaseHelper=new FireBaseHelper(login.this);

    }
private void restpassword(){

    View view= LayoutInflater.from(this).
            inflate(R.layout.dialog_box,null);
    EditText editText=view.findViewById(R.id.editText);
    editText.setHint("enter email");
    AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
    alertDialog.setView(view).setTitle("enter your email").setPositiveButton(
            "send link to rest password",((dialog, which) -> {
                fireBaseHelper.remeberpassword(editText.getText().toString());
            })).setNegativeButton("cancel",(dialog, which) -> {
        Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();

    }).show();

}
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.loginBtn){
            if (TextUtils.isEmpty(email_login.getText().toString())
                    ||TextUtils.isEmpty(passwordlogin.getText().toString())){
                Toast.makeText(this, "enter all values ", Toast.LENGTH_SHORT).show();
            }else{
                fireBaseHelper.signin(email_login.getText().toString(), passwordlogin.getText().toString());

            }

        }else if (v.getId()==R.id.rememberBtn){
            restpassword();
        }else if (v.getId()==R.id.signupLayoutBtn){
            startActivity(new Intent(this, signup.class));
            finish();
        }

    }
}