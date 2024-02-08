package com.ahmad.calcplay.userData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmad.calcplay.HelperAndAdabter.FireBaseHelper;
import com.ahmad.calcplay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class signup extends AppCompatActivity {
private  EditText nameET_signup,emailET_signup,passwordET_signup,repasswordET_signup,phoneET_signup;

 Button signupBtn;
FireBaseHelper fireBaseHelper;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //initialize components UI
        nameET_signup=findViewById(R.id.nameET_signup);
        emailET_signup=findViewById(R.id.emailET_signup);
        passwordET_signup=findViewById(R.id.passwordET_signup);
        repasswordET_signup=findViewById(R.id.repasswordET_signup);
        phoneET_signup=findViewById(R.id.phoneET_signup);
    signupBtn=findViewById(R.id.signupBtn);
        //initialize firebase authentication and firebase database (custom class)
fireBaseHelper=new FireBaseHelper(this);
//create method to organize code
    RecordAllData(signupBtn);

    }

    private void RecordAllData(Button signupBtn) {
    signupBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
String emailValue,passValue,repassValue,nameValue,phoneValue;
            emailValue=emailET_signup.getText().toString().trim();
            passValue=passwordET_signup.getText().toString().trim();
            repassValue=repasswordET_signup.getText().toString().trim();
            nameValue=nameET_signup.getText().toString().trim();
            phoneValue=phoneET_signup.getText().toString().trim();
            if (TextUtils.isEmpty(emailValue)){
                emailET_signup.setError("Email is required");
            }else {
                emailET_signup.setError(null);
            }
            if (TextUtils.isEmpty(passValue)){
                passwordET_signup.setError("password is required");
            }else {
                passwordET_signup.setError(null);
            }
            if (TextUtils.isEmpty(repassValue)){
                repasswordET_signup.setError("RE-password is required");
            }else {
                repasswordET_signup.setError(null);
            }
            if (TextUtils.isEmpty(nameValue)){
                nameET_signup.setError("name is required");
            }else {
                nameET_signup.setError(null);
            }
            if (TextUtils.isEmpty(phoneValue)){
                phoneET_signup.setError("phone is required");
            }else {
                phoneET_signup.setError(null);
            }
            //save email and password in database (real time database)
if (!TextUtils.isEmpty(nameValue)&&
        !TextUtils.isEmpty(passValue)&&
        !TextUtils.isEmpty(repassValue)&&
        !TextUtils.isEmpty(emailValue)&&
            !TextUtils.isEmpty(phoneValue)){
    if (passValue.length()>5&&repassValue.length()>5){
        if (passValue.equals(repassValue)){
fireBaseHelper.signup(nameValue, emailValue, passValue, phoneValue);
    }

        else {
            repasswordET_signup.setError("RE-password is required");
            passwordET_signup.setError("password is required");
            Toast.makeText(signup.this, "password not same re-password", Toast.LENGTH_SHORT).show();
        }

    }else{
        Toast.makeText(signup.this, "your password less than 6 characters", Toast.LENGTH_SHORT).show();
    }


        }



}});
}


}