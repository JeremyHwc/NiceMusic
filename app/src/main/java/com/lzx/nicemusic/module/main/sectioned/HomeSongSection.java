package com.lzx.nicemusic.module.main.sectioned;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.nicemusic.R;
import com.lzx.nicemusic.module.artist.ArtistDetailActivity;
import com.lzx.nicemusic.utils.GlideUtil;
import com.lzx.nicemusic.widget.CircleImageView;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by xian on 2018/2/16.
 */

public class HomeSongSection extends StatelessSection {

    private List<SongInfo> mSongInfos;
    private Context mContext;

    public HomeSongSection(Context context, List<SongInfo> mSongInfos) {
        super(new SectionParameters.Builder(R.layout.item_home_song).build());
        this.mSongInfos = mSongInfos;
        mContext = context;
    }

    @Override
    public int getContentItemsTotal() {
        return mSongInfos.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new SongHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SongHolder holder = (SongHolder) viewHolder;
        SongInfo info = mSongInfos.get(position);
        GlideUtil.loadImageByUrl(mContext, info.getSongCover(), holder.mMusicCover);
        holder.mSongName.setText(info.getSongName());
        holder.mArtistName.setText(info.getArtist());
        holder.itemView.setOnClickListener(v -> {
            ArtistDetailActivity.launch(mContext, info);
        });
    }

    class SongHolder extends RecyclerView.ViewHolder {
        private CircleImageView mMusicCover;
        private TextView mSongName;
        private TextView mArtistName;

        SongHolder(View itemView) {
            super(itemView);
            mMusicCover = itemView.findViewById(R.id.music_cover);
            mSongName = itemView.findViewById(R.id.song_name);
            mArtistName = itemView.findViewById(R.id.artist);
        }
    }
}
