package com.codepath.instagramphotoviewer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.codepath.instagramphotoviewer.adapter.CommentsAdapter;
import com.codepath.instagramphotoviewer.constant.ExtraKeys;
import com.codepath.instagramphotoviewer.model.instagram.Comment;
import com.codepath.instagramphotoviewer.service.InstagramHelper;
import com.codepath.instragamphotoviewer.R;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends Activity {
    private List<Comment> comments;
    private CommentsAdapter aComments;
    private String photoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        photoId = intent.getStringExtra(ExtraKeys.PHOTO_ID);

        comments = new ArrayList<>();
        aComments = new CommentsAdapter(this, comments);
        ListView lvComments = (ListView) findViewById(R.id.lvComments);
        lvComments.setAdapter(aComments);
        fetchPopularComments();
    }

    private void fetchPopularComments() {
        InstagramHelper.fetchComments(photoId, new InstagramHelper.CommentsResponseHandler() {
            @Override
            public void onSuccess(List<Comment> allComments) {
                aComments.clear();
                comments.addAll(allComments);
                aComments.notifyDataSetChanged();
                // TODO swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMessage, Throwable throwable) {
                Log.e("ERROR", errorMessage, throwable);
            }
        });
    }

}
