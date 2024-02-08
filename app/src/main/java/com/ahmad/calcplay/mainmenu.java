package com.ahmad.calcplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmad.calcplay.HelperAndAdabter.FireBaseHelper;
import com.ahmad.calcplay.calculator.calculator;
import com.ahmad.calcplay.userData.login;
import com.ahmad.calcplay.userData.profileinfo;
import com.ahmad.calcplay.videoplayer.videoplayer;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class mainmenu extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
Toolbar toolbar;
ImageView imgCalculator, imgvideoplayer;
Button videoplayerbtn,calcbtn;
FirebaseAuth auth;
    FireBaseHelper fireBaseHelper;
TextView name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        auth=FirebaseAuth.getInstance();
        //navigation menu components
    drawerLayout=findViewById(R.id.drawerLayout);
    navigationView=findViewById(R.id.navigation);
    toolbar=findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawerToggle=new ActionBarDrawerToggle(mainmenu.this,drawerLayout,toolbar,R.string.open,R.string.close);
    drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
    drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    drawerToggle.syncState();
        navigationView.setItemIconTintList(null);
        //inflate header menu
        View view =LayoutInflater.from(mainmenu.this).
                inflate(R.layout.header,navigationView,false);
       navigationView.addHeaderView(view);
        name=view.findViewById(R.id.name_header);
        email=view.findViewById(R.id.email_header);

fireBaseHelper=new FireBaseHelper(mainmenu.this);
fireBaseHelper.headervalue(name,email);
    //initialize other components for UI
    imgvideoplayer =findViewById(R.id.imgvideoplayer);
    imgvideoplayer.setOnClickListener(this);
    imgCalculator=findViewById(R.id.imgCalculator);
    imgCalculator.setOnClickListener(this);
    videoplayerbtn=findViewById(R.id.videoplayerbtn);
    videoplayerbtn.setOnClickListener(this);
    calcbtn=findViewById(R.id.calcBtn);
        calcbtn.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 if (item.getItemId() == R.id.profileInfo) {
                     startActivity(new Intent(mainmenu.this, profileinfo.class));

                 }
                if (item.getItemId() == R.id.share) {
startActivity(Intent.createChooser(new Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT,"sharing my app"),"share using"));

                }
                if (item.getItemId() == R.id.logout) {
                    startActivity(new Intent(mainmenu.this, login.class));
            finish();
            auth.signOut();
                }
           return true;
            }
        });
    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.imgvideoplayer ||v.getId()==R.id.videoplayerbtn){
            startActivity(new Intent(this, videoplayer.class));
        }
        if (v.getId()==R.id.imgCalculator||v.getId()==R.id.calcBtn){
            startActivity(new Intent(this, calculator.class));
        }
    }


    }