package com.codepath.instagramphotoviewer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        photoId = intent.getStringExtra(ExtraKeys.PHOTO_ID);
        setupSwitchRefreshLayout();

        comments = new ArrayList<>();
        aComments = new CommentsAdapter(this, comments);
        ListView lvComments = (ListView) findViewById(R.id.lvComments);
        lvComments.setAdapter(aComments);
        fetchPopularComments();
    }

    private void setupSwitchRefreshLayout() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularComments();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void fetchPopularComments() {
        InstagramHelper.fetchComments(photoId, new InstagramHelper.CommentsResponseHandler() {
            @Override
            public void onSuccess(List<Comment> allComments) {
                aComments.clear();
                comments.addAll(allComments);
                aComments.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMessage, Throwable throwable) {
                Log.e("ERROR", errorMessage, throwable);
            }
        });
    }

}
