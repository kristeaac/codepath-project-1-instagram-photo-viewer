package com.codepath.instagramphotoviewer.service;

import com.codepath.instagramphotoviewer.model.instagram.Photo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstagramHelper {
    private static final String CLIENT_ID = "bc6c997f0da6402f927e7595180c5a83";

    public static void fetchPopularPhotos(final PhotosResponseHandler handler) {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                List<Photo> photos = new ArrayList<>();
                try {
                    JSONArray photosJson = response.getJSONArray("data");
                    for (int i = 0; i < photosJson.length(); i++) {
                        photos.add(mapper.readValue(photosJson.getJSONObject(i).toString(), Photo.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.onSuccess(photos);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.onFailure(responseString, throwable);
            }
        });
    }

    public static interface PhotosResponseHandler {

        void onSuccess(List<Photo> photos);

        void onFailure(String errorMessage, Throwable throwable);

    }
}
