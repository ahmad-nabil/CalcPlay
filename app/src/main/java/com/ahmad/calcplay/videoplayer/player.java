package com.ahmad.calcplay.videoplayer;



import static com.ahmad.calcplay.HelperAndAdabter.AdabterVideo.VideoFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ahmad.calcplay.R;
import com.ahmad.calcplay.customData.Videofiles;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class player extends AppCompatActivity {
PlayerView exoplayervideo;
SimpleExoPlayer simpleExoPlayer;
int position =-1;
String path_video;
String url;
boolean fromurl;
    Uri uri;
ArrayList <Videofiles>videofiles=new ArrayList<>();
ConcatenatingMediaSource concatenatingMediaSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to make video full screen and  we put it first because must call before contentView
        FullScreen();
        setContentView(R.layout.activity_player);
        //initialize exoplayer
        exoplayervideo=findViewById(R.id.exoplayervideo);
        position=getIntent().getIntExtra("position",-1);

//check if he come from url or not
        fromurl=getIntent().getBooleanExtra("fromurl",false);
        if (fromurl) {
            url=getIntent().getStringExtra("url");
            uri=Uri.parse(url);
            getIntent().removeExtra("fromurl");
            playvideo(uri);
        }else{
            path_video=VideoFiles.get(position).getPath();
            if(path_video!=null){
                //get Uri Video from Arraylist video files
              uri =Uri.parse(path_video);
                //components exo player
                playvideo(uri);
            }
        }
        //to avoid bug invoke null we will check if path null or have value


    }



    private void playvideo(Uri uri){
        simpleExoPlayer=new SimpleExoPlayer.Builder(this).build();
        DefaultDataSourceFactory factory=new DefaultDataSourceFactory(player.this, Util.getUserAgent(this,"my app"));
        concatenatingMediaSource=new ConcatenatingMediaSource();

        ExtractorsFactory extractorsFactory=new DefaultExtractorsFactory();

        MediaSource mediaSource=new ProgressiveMediaSource.Factory(factory,extractorsFactory).createMediaSource(MediaItem.fromUri(uri));
        concatenatingMediaSource.addMediaSource(mediaSource);
        exoplayervideo.setKeepScreenOn(true);
        exoplayervideo.setPlayer(simpleExoPlayer);

        simpleExoPlayer.setPlayWhenReady(true);

        simpleExoPlayer.prepare(concatenatingMediaSource);
    }
    private void FullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}