package com.lzx.musiclibrary.manager;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.media.session.PlaybackStateCompat;

import com.lzx.musiclibrary.MusicService;
import com.lzx.musiclibrary.OnPlayerEventListener;
import com.lzx.musiclibrary.bean.MusicInfo;
import com.lzx.musiclibrary.control.IPlayControl;
import com.lzx.musiclibrary.playback.PlaybackManager;

import java.util.List;


/**
 * @author lzx
 * @date 2018/1/22
 */

public class MusicManager implements IPlayControl,PlaybackManager.PlaybackServiceCallback {

    private Context mContext;
    private boolean isUseMediaPlayer = false;
    private IPlayControl control;

    public static MusicManager get() {
        return SingletonHolder.sInstance;
    }

    @Override
    public void onPlaybackStart() {

    }

    @Override
    public void onPlaybackPause() {

    }

    @Override
    public void onPlaybackStop() {

    }

    @Override
    public void onNotificationRequired() {

    }

    @Override
    public void onPlaybackStateUpdated(PlaybackStateCompat newState) {

    }

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static final MusicManager sInstance = new MusicManager();
    }

    public void init(Context context) {
        init(context, false);
    }

    public void init(Context context, boolean isUseMediaPlayer) {
        mContext = context;
        this.isUseMediaPlayer = isUseMediaPlayer;
        Intent intent = new Intent(mContext, MusicService.class);
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService service = ((MusicService.PlayBinder) iBinder).getService();
            service.init(isUseMediaPlayer);
            control = ((MusicService.PlayBinder) iBinder).getPlayControl();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void unbindService() {
        mContext.unbindService(mServiceConnection);
    }

    private MusicManager() {

    }



    @Override
    public void playMusic(List<MusicInfo> list, int index) {
        if (control != null) {
            control.playMusic(list, index);
        }
    }

    @Override
    public void playMusic(MusicInfo info) {
        if (control != null) {
            control.playMusic(info);
        }
    }

    @Override
    public void playMusic(int index) {
        if (control != null) {
            control.playMusic(index);
        }
    }

    @Override
    public void playMusicAutoStopWhen(List<MusicInfo> list, int index, int time) {
        if (control != null) {
            control.playMusicAutoStopWhen(list, index, time);
        }
    }

    @Override
    public void playMusicAutoStopWhen(MusicInfo info, int time) {
        if (control != null) {
            control.playMusicAutoStopWhen(info, time);
        }
    }

    @Override
    public void playMusicAutoStopWhen(int index, int time) {
        if (control != null) {
            control.playMusicAutoStopWhen(index, time);
        }
    }

    @Override
    public void setAutoStopTime(int time) {
        if (control != null) {
            control.setAutoStopTime(time);
        }
    }

    @Override
    public MusicInfo getCurrPlayingMusic() {
        if (control != null) {
            return control.getCurrPlayingMusic();
        }
        return null;
    }

    @Override
    public void setCurrMusic(int index) {
        if (control != null) {
            control.setCurrMusic(index);
        }
    }

    @Override
    public int getCurrPlayingIndex() {
        if (control != null) {
            return control.getCurrPlayingIndex();
        }
        return -1;
    }

    @Override
    public void pauseMusic() {
        if (control != null) {
            control.pauseMusic();
        }
    }

    @Override
    public void resumeMusic() {
        if (control != null) {
            control.resumeMusic();
        }
    }

    @Override
    public void stopMusic() {
        if (control != null) {
            control.stopMusic();
        }
    }

    @Override
    public void setPlayList(List<MusicInfo> list) {
        if (control != null) {
            control.setPlayList(list);
        }
    }

    @Override
    public void setPlayList(List<MusicInfo> list, int index) {
        if (control != null) {
            control.setPlayList(list, index);
        }
    }

    @Override
    public List<MusicInfo> getPlayList() {
        if (control != null) {
            control.getPlayList();
        }
        return null;
    }

    @Override
    public int getStatus() {
        if (control != null) {
            control.getStatus();
        }
        return 0;
    }

    @Override
    public void playNext() {
        if (control != null) {
            control.playNext();
        }
    }

    @Override
    public void playPre() {
        if (control != null) {
            control.playPre();
        }
    }

    @Override
    public boolean hasPre() {
        return control != null && control.hasPre();
    }

    @Override
    public boolean hasNext() {
        return control != null && control.hasNext();
    }

    @Override
    public MusicInfo getPreMusic() {
        if (control != null) {
            return control.getPreMusic();
        }
        return null;
    }

    @Override
    public MusicInfo getNextMusic() {
        if (control != null) {
            return control.getNextMusic();
        }
        return null;
    }

    @Override
    public void setPlayMode(int mode) {
        if (control != null) {
            control.setPlayMode(mode);
        }
    }

    @Override
    public int getPlayMode() {
        if (control != null) {
            return control.getPlayMode();
        }
        return 0;
    }

    @Override
    public long getProgress() {
        if (control != null) {
            return control.getProgress();
        }
        return 0;
    }

    @Override
    public void seekTo(int position) {
        if (control != null) {
            control.seekTo(position);
        }
    }

    @Override
    public void reset() {
        if (control != null) {
            control.reset();
        }
    }

    @Override
    public void addPlayerEventListener(OnPlayerEventListener listener) {
        if (control != null) {
            control.addPlayerEventListener(listener);
        }
    }
}
