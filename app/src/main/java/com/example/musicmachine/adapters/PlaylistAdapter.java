package com.example.musicmachine.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicmachine.DetailsActivity;
import com.example.musicmachine.MainActivity;
import com.example.musicmachine.R;
import com.example.musicmachine.models.Song;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.SongViewHolder> {

    private final Song[] mSongs;
    private final Context mContext;

    public PlaylistAdapter(Song[] mSongs, Context mContext) {
        this.mSongs = mSongs;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_song , parent , false);

        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.bindSong(mSongs[position]);


    }

    @Override
    public int getItemCount() {
        return mSongs.length;
    }

    public  class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView imageView;
        private final TextView textView;



        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.favIcon);
            textView = itemView.findViewById(R.id.songTitleLabel);
        }

        public void bindSong(Song song) {
            textView.setText(song.getTitle());
            if (song.isFavorite()) {
                imageView.setVisibility(View.VISIBLE);
            }
            else {
                imageView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext , DetailsActivity.class);
            intent.putExtra(MainActivity.EXTRA_SONG, mSongs[getAdapterPosition()]);
            intent.putExtra(MainActivity.EXTRA_LIST_POSITION, getAdapterPosition());
            mContext.startActivity(intent);
            ((Activity)mContext).startActivityForResult(intent, MainActivity.REQUEST_FAVORITE);
        }
    }
}
