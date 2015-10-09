package com.codepath.instagramphotoviewer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import com.codepath.instagramphotoviewer.constant.ExtraKeys;
import com.codepath.instragamphotoviewer.R;

public class VideoPlayerActivity extends Activity implements MediaPlayer.OnCompletionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video_player);

        VideoView v = (VideoView) findViewById(R.id.vvVideo);

        String url = null;
        if (getIntent().getExtras() != null) {
            url = getIntent().getExtras().getString(ExtraKeys.URL);

            if (url != null) {
                v.setMediaController(new MediaController(this));
                v.setOnCompletionListener(this);
                v.setVideoURI(Uri.parse(url));
                v.start();
            }
        }

        if (url == null) {
            throw new IllegalArgumentException("Must set url extra paremeter in intent.");
        }
    }

    @Override
    public void onCompletion(MediaPlayer v) {
        finish();
    }

}