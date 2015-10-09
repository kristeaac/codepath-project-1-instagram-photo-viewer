package com.codepath.instagramphotoviewer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import com.codepath.instagramphotoviewer.adapter.PhotosAdapter;
import com.codepath.instagramphotoviewer.model.instagram.Photo;
import com.codepath.instagramphotoviewer.service.InstagramHelper;
import com.codepath.instragamphotoviewer.R;

import java.util.ArrayList;
import java.util.List;


public class PhotosActivity extends Activity {
    private List<Photo> photos;
    private PhotosAdapter aPhotos;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        setupSwitchRefreshLayout();
        photos = new ArrayList<>();
        aPhotos = new PhotosAdapter(this, photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aPhotos);
        fetchPopularPhotos();
    }

    private void setupSwitchRefreshLayout() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotos();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    private void fetchPopularPhotos() {
        InstagramHelper.fetchPopularPhotos(new InstagramHelper.PhotosResponseHandler() {
            @Override
            public void onSuccess(List<Photo> popularPhotos) {
                aPhotos.clear();
                photos.addAll(popularPhotos);
                aPhotos.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(String errorMessage, Throwable throwable) {
                Log.e("ERROR", errorMessage, throwable);
            }
        });
    }

}
