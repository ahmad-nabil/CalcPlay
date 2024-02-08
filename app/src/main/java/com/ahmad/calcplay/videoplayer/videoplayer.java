package com.ahmad.calcplay.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ahmad.calcplay.R;
import com.ahmad.calcplay.customData.Videofiles;
import com.ahmad.calcplay.mainmenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class videoplayer extends AppCompatActivity {
final static int STORAGE_permission=1;
BottomNavigationView navvideo;
   static ArrayList <String>foldername=new ArrayList<>();

static public ArrayList <Videofiles> videofilesList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        /*
       method for check permission  and if the application have api level 33 dont need this way
        but less than api level 33  need ask user directly
         */
        permissioncheck();
        //to load video files automaticaly if their any video
        videofilesList=getVideofiles(videoplayer.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new File()).commitNow();

        //navigation bottom components

        navvideo=findViewById(R.id.navvideo);
        navvideo.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.files){
                    videofilesList=getVideofiles(videoplayer.this);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new File()).commit();
                    item.setChecked(true);
                    return true;
                }
               else if (item.getItemId()==R.id.mainmenu) {
                    startActivity(new Intent(videoplayer.this,mainmenu.class));
                }  else if (item.getItemId()==R.id.playurl) {
playurl();                }
                return true;
            }
        });

    }
public ArrayList<Videofiles>getVideofiles(Context context){

   // Sample query to get data
    String[]projection={
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DISPLAY_NAME
    };
    //create temp arraylist to store video files
    ArrayList<Videofiles>temp=new ArrayList<>();
    Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);
    if (cursor!=null){
        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String path =cursor.getString(1);
            String title=cursor.getString(2);
            String size =cursor.getString(3);
            String dataAdded=cursor.getString(4);
            String duration =cursor.getString(5);
            String filename=cursor.getString(6);
            Videofiles videofiles=new Videofiles(id,path,title,filename,size,dataAdded,duration);
            temp.add(videofiles);
            Log.e("TAG", path);
        }
        cursor.close();
    }
return temp;
}




    private void permissioncheck() {
        getVideofiles(videoplayer.this);
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(videoplayer.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_permission);
        }
    }
    public void playurl(){

        View view= LayoutInflater.from(this).
                inflate(R.layout.dialog_box,null);
        EditText editText=view.findViewById(R.id.editText);
        editText.setHint("enter url video");

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setView(view).setTitle("enter URL").setPositiveButton(
                "play",((dialog, which) -> {
                    String url=editText.getText().toString();
                    Intent intent=new Intent(this, player.class);
                    intent.putExtra("url",url);
                    intent.putExtra("fromurl",true);
                    startActivity(intent);

                })).setNegativeButton("cancel",(dialog, which) -> {
            Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();

        }).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode==STORAGE_permission){
        for (int i = 0; i <permissions.length; i++) {
String per=permissions[i];
if (grantResults[i]==PackageManager.PERMISSION_DENIED){
    ActivityCompat.requestPermissions(videoplayer.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_permission);

}
        }
    }
    }
}