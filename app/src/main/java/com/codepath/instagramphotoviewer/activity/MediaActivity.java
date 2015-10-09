package com.codepath.instagramphotoviewer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import com.codepath.instagramphotoviewer.adapter.MediaAdapter;
import com.codepath.instagramphotoviewer.model.instagram.Media;
import com.codepath.instagramphotoviewer.service.InstagramHelper;
import com.codepath.instragamphotoviewer.R;

import java.util.ArrayList;
import java.util.List;


public class MediaActivity extends Activity {
    private List<Media> media;
    private MediaAdapter aMedia;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        setupSwitchRefreshLayout();
        media = new ArrayList<>();
        aMedia = new MediaAdapter(this, media);
        ListView lvMedia = (ListView) findViewById(R.id.lvMedia);
        lvMedia.setAdapter(aMedia);
        fetchPopularMedia();
    }

    private void setupSwitchRefreshLayout() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularMedia();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    private void fetchPopularMedia() {
        InstagramHelper.fetchPopularMedia(new InstagramHelper.MediaResponseHandler() {
            @Override
            public void onSuccess(List<Media> popularMedia) {
                aMedia.clear();
                media.addAll(popularMedia);
                aMedia.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMessage, Throwable throwable) {
                Log.e("ERROR", errorMessage, throwable);
            }
        });
    }

}
