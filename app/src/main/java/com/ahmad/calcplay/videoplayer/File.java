package com.ahmad.calcplay.videoplayer;

import static com.ahmad.calcplay.videoplayer.videoplayer.videofilesList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.calcplay.HelperAndAdabter.AdabterVideo;
import com.ahmad.calcplay.R;


public class File extends Fragment {

View view;
RecyclerView fileListRV;
AdabterVideo adabterVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
 //inflate layout
        view=inflater.inflate(R.layout.fragment_file, container, false);
 //intilize Recycle view to get video list in this Recycle
fileListRV=view.findViewById(R.id.fileListRV);

//check video list if null or not (we intilized it static public  in the befor in videoplayer Class)
        if (videofilesList!=null&&videofilesList.size()>0){
            adabterVideo=new AdabterVideo(getContext().getApplicationContext(),videofilesList);
            fileListRV.setAdapter(adabterVideo);
            fileListRV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }
return view;
    }
}