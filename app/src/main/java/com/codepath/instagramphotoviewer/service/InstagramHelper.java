package com.codepath.instagramphotoviewer.service;

import com.codepath.instagramphotoviewer.model.instagram.Comment;
import com.codepath.instagramphotoviewer.model.instagram.Media;
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

    public static void fetchPopularMedia(final MediaResponseHandler handler) {
        String url = String.format("https://api.instagram.com/v1/media/popular?client_id=%s", CLIENT_ID);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                List<Media> media = new ArrayList<>();
                try {
                    JSONArray mediaJson = response.getJSONArray("data");
                    for (int i = 0; i < mediaJson.length(); i++) {
                        media.add(mapper.readValue(mediaJson.getJSONObject(i).toString(), Media.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.onSuccess(media);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.onFailure(responseString, throwable);
            }
        });
    }

    public static void fetchComments(String mediaId, final CommentsResponseHandler handler) {
        String url = String.format("https://api.instagram.com/v1/media/%s/comments?client_id=%s", mediaId, CLIENT_ID);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                List<Comment> comments = new ArrayList<>();
                try {
                    JSONArray commentsJson = response.getJSONArray("data");
                    for (int i = 0; i < commentsJson.length(); i++) {
                        comments.add(mapper.readValue(commentsJson.getJSONObject(i).toString(), Comment.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.onSuccess(comments);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.onFailure(responseString, throwable);
            }
        });
    }

    public static interface MediaResponseHandler {

        void onSuccess(List<Media> medias);

        void onFailure(String errorMessage, Throwable throwable);

    }

    public static interface CommentsResponseHandler {

        void onSuccess(List<Comment> comments);

        void onFailure(String errorMessage, Throwable throwable);

    }

}
