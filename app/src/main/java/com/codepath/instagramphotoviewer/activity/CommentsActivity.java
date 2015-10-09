package com.codepath.instagramphotoviewer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.codepath.instagramphotoviewer.constant.ExtraKeys;
import com.codepath.instragamphotoviewer.R;

public class CommentsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        String photoId = intent.getStringExtra(ExtraKeys.PHOTO_ID);
        Log.d("DEBUG", photoId);
    }

}
