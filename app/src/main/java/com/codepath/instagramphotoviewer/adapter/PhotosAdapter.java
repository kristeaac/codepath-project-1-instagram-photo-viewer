package com.codepath.instagramphotoviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.instagramphotoviewer.model.instagram.Caption;
import com.codepath.instagramphotoviewer.model.instagram.Comment;
import com.codepath.instagramphotoviewer.model.instagram.Comments;
import com.codepath.instagramphotoviewer.model.instagram.Image;
import com.codepath.instagramphotoviewer.model.instagram.Photo;
import com.codepath.instagramphotoviewer.model.instagram.User;
import com.codepath.instragamphotoviewer.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

public class PhotosAdapter extends ArrayAdapter<Photo> {
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    public PhotosAdapter(Context context, List<Photo> photos) {
        super(context, android.R.layout.simple_list_item_1, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvPostedTime = (TextView) convertView.findViewById(R.id.tvPostedTime);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvLikeCount = (TextView) convertView.findViewById(R.id.tvLikeCount);
        ImageView ivUserPhoto = (ImageView) convertView.findViewById(R.id.ivUserPhoto);
        Caption caption = photo.getCaption();
        if (caption != null) {
            tvCaption.setText(caption.getText());
            tvPostedTime.setText(PRETTY_TIME.format(new Date(Long.parseLong(caption.getCreatedTime()) * 1000)));
        }
        tvUsername.setText(photo.getUser().getUsername());
        tvLikeCount.setText(String.valueOf(photo.getLikes().getCount()));
        ivPhoto.setImageResource(0);
        ivUserPhoto.setImageResource(0);
        Image standardResolutionImage = photo.getImages().getStandardResolutionImage();
        Picasso.with(getContext()).load(standardResolutionImage.getUrl()).placeholder(R.drawable.loading).into(ivPhoto);
        Picasso.with(getContext()).load(photo.getUser().getProfilePictureUrl()).into(ivUserPhoto);

        Comments comments = photo.getComments();
        int commentCount = comments.getCount();
        if (commentCount == 0) {
            convertView.findViewById(R.id.rlComment1).setVisibility(View.INVISIBLE);
            convertView.findViewById(R.id.rlComment2).setVisibility(View.INVISIBLE);
        } else if (commentCount == 1) {
            populateFirstComment(convertView, comments.getComments().get(0));
            convertView.findViewById(R.id.rlComment2).setVisibility(View.INVISIBLE);
        } else {
            populateFirstComment(convertView, comments.getComments().get(0));
            populateSecondComment(convertView, comments.getComments().get(1));
        }

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

}
