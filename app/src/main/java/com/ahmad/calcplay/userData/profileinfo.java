package com.ahmad.calcplay.userData;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ahmad.calcplay.HelperAndAdabter.FireBaseHelper;
import com.ahmad.calcplay.R;
import com.ebanx.swipebtn.OnStateChangeListener;

public class profileinfo extends AppCompatActivity {
EditText nameET_profileInfo,emailET_profileInfo,phoneET_profileInfo,passwordET_profileInfo;
com.ebanx.swipebtn.SwipeButton swipeTosave;
FireBaseHelper update_and_get_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileinfo);
        //initialize component UI
        nameET_profileInfo=findViewById(R.id.nameET_profileInfo);
        emailET_profileInfo=findViewById(R.id.emailET_profileInfo);
        phoneET_profileInfo=findViewById(R.id.phoneET_profileInfo);
        passwordET_profileInfo=findViewById(R.id.passwordET_profileInfo);
        swipeTosave=findViewById(R.id.swipeTosave);
        //initialize object to firebase helper  (custom class to organize code)
        update_and_get_data=new FireBaseHelper(profileinfo.this);
        //get date from database
        update_and_get_data.getdata(nameET_profileInfo,emailET_profileInfo,passwordET_profileInfo,phoneET_profileInfo);

        swipeTosave.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                updatedata();

            }
        });

    }

    private void updatedata() {
        String name ,email,password,phone;
        name=nameET_profileInfo.getText().toString().trim();
        email=emailET_profileInfo.getText().toString().trim();
        password=passwordET_profileInfo.getText().toString().trim();
        phone=phoneET_profileInfo.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            nameET_profileInfo.setError("name required");
        }
        if(TextUtils.isEmpty(email)) {
            emailET_profileInfo.setError("email required");
        }
        if(TextUtils.isEmpty(password)) {
            emailET_profileInfo.setError("password required");
        }
        if(TextUtils.isEmpty(phone)) {
            emailET_profileInfo.setError("phone required");
        }
        if (!TextUtils.isEmpty(name)&&
                !TextUtils.isEmpty(password)&&
                !TextUtils.isEmpty(email)&&
                !TextUtils.isEmpty(phone)){
            update_and_get_data.updatedata(name,email,password,phone);
        }

    }



}