package com.ahmad.calcplay.HelperAndAdabter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.calcplay.R;
import com.ahmad.calcplay.customData.Videofiles;
import com.ahmad.calcplay.videoplayer.player;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class AdabterVideo extends RecyclerView.Adapter<AdabterVideo.myView> {
    Context context;
   public static ArrayList <Videofiles>VideoFiles;

    public AdabterVideo(Context context, ArrayList<Videofiles> videoFiles) {
        this.context = context;
        VideoFiles = videoFiles;
    }

    @NonNull
    @Override
    public AdabterVideo.myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.videoitems,parent,false);
        return new myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterVideo.myView holder, int position) {
        holder.videoTitle.setText(VideoFiles.get(position).getTitle());
        holder.duration.setText(VideoFiles.get(position).getDuration());
        Glide.with(context).load(new File(VideoFiles.get(position).getPath()))
                .into(holder.Thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,player.class);
                intent.putExtra("position",holder.getBindingAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return VideoFiles.size();
    }

    public class myView extends RecyclerView.ViewHolder{
        ImageView Thumbnail;
        TextView duration,videoTitle;
        public myView(@NonNull View itemView) {
            super(itemView);
            Thumbnail=itemView.findViewById(R.id.Thumbnail);
            duration=itemView.findViewById(R.id.duration);
            videoTitle=itemView.findViewById(R.id.videoTitle);
        }
    }
}
