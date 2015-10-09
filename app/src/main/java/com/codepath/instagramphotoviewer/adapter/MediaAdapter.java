package com.codepath.instagramphotoviewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.instagramphotoviewer.activity.CommentsActivity;
import com.codepath.instagramphotoviewer.activity.VideoPlayerActivity;
import com.codepath.instagramphotoviewer.constant.ExtraKeys;
import com.codepath.instagramphotoviewer.model.instagram.Caption;
import com.codepath.instagramphotoviewer.model.instagram.Comment;
import com.codepath.instagramphotoviewer.model.instagram.Comments;
import com.codepath.instagramphotoviewer.model.instagram.Image;
import com.codepath.instagramphotoviewer.model.instagram.Media;
import com.codepath.instagramphotoviewer.model.instagram.User;
import com.codepath.instragamphotoviewer.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

public class MediaAdapter extends ArrayAdapter<Media> {
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    public MediaAdapter(Context context, List<Media> media) {
        super(context, android.R.layout.simple_list_item_1, media);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Media media = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_media, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvPostedTime = (TextView) convertView.findViewById(R.id.tvPostedTime);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvLikeCount = (TextView) convertView.findViewById(R.id.tvLikeCount);
        ImageView ivUserPhoto = (ImageView) convertView.findViewById(R.id.ivUserPhoto);

        if (media.isVideo()) {
            ImageView ivClickToPlay = (ImageView) convertView.findViewById(R.id.ivClickToPlayVideo);
            ivClickToPlay.setVisibility(View.VISIBLE);
            ivClickToPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                    intent.putExtra(ExtraKeys.URL, media.getVideos().getStandardResolutionVideo().getUrl());
                    getContext().startActivity(intent);
                }
            });
        } else {
            ImageView tvClickToPlay = (ImageView) convertView.findViewById(R.id.ivClickToPlayVideo);
            tvClickToPlay.setVisibility(View.INVISIBLE);
        }

        Caption caption = media.getCaption();
        if (caption != null) {
            tvCaption.setText(caption.getText());
            tvPostedTime.setText(PRETTY_TIME.format(new Date(Long.parseLong(caption.getCreatedTime()) * 1000)));
        }
        tvUsername.setText(media.getUser().getUsername());
        tvLikeCount.setText(String.valueOf(media.getLikes().getCount()));
        ivPhoto.setImageResource(0);
        ivUserPhoto.setImageResource(0);
        Image standardResolutionImage = media.getImages().getStandardResolutionImage();
        Picasso.with(getContext()).load(standardResolutionImage.getUrl()).placeholder(R.drawable.loading).into(ivPhoto);
        Picasso.with(getContext()).load(media.getUser().getProfilePictureUrl()).into(ivUserPhoto);

        Comments comments = media.getComments();
        List<Comment> commentsList = comments.getComments();
        int commentCount = comments.getCount();
        if (commentCount >= 2) {
            populateFirstComment(convertView, commentsList.get(0));
            populateSecondComment(convertView, commentsList.get(1));
        } else if (commentCount == 1) {
            populateFirstComment(convertView, commentsList.get(0));
            hideSecondComment(convertView);
        } else {
            hideFirstComment(convertView);
            hideSecondComment(convertView);
        }

        TextView tvMoreComments = (TextView) convertView.findViewById(R.id.tvMoreComments);
        tvMoreComments.setText(String.format("%s total comment(s)", commentCount));
        tvMoreComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommentsActivity.class);
                intent.putExtra(ExtraKeys.MEDIA_ID, media.getId());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    private void populateFirstComment(View convertView, Comment comment) {
        int photoResourceId = R.id.ivCommenterPhoto1;
        int usernameResourceId = R.id.tvCommenterUsername1;
        int commentResourceId = R.id.tvComment1;
        populateComment(convertView, comment, photoResourceId, usernameResourceId, commentResourceId);
    }

    private void populateSecondComment(View convertView, Comment comment) {
        int photoResourceId = R.id.ivCommenterPhoto2;
        int usernameResourceId = R.id.tvCommenterUsername2;
        int commentResourceId = R.id.tvComment2;
        populateComment(convertView, comment, photoResourceId, usernameResourceId, commentResourceId);
    }

    private void populateComment(View convertView, Comment comment, int photoResourceId, int usernameResourceId, int commentResourceId) {
        ImageView ivCommenterUserPhoto = (ImageView) convertView.findViewById(photoResourceId);
        TextView tvCommenterUsername = (TextView) convertView.findViewById(usernameResourceId);
        TextView tvComment = (TextView) convertView.findViewById(commentResourceId);
        User from = comment.getFrom();
        tvCommenterUsername.setText(from.getUsername());
        tvComment.setText(comment.getText());
        ivCommenterUserPhoto.setImageResource(0);
        Picasso.with(getContext()).load(from.getProfilePictureUrl()).into(ivCommenterUserPhoto);
    }

    private void hideFirstComment(View convertView) {
        int commentResourceId = R.id.rlComment1;
        hideComment(convertView, commentResourceId);
    }

    private void hideSecondComment(View convertView) {
        int commentResourceId = R.id.rlComment2;
        hideComment(convertView, commentResourceId);
    }

    private void hideComment(View convertView, int commentResourceId) {
        convertView.findViewById(commentResourceId).setVisibility(View.INVISIBLE);
    }

}
