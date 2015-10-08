package com.codepath.instagramphotoviewer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.instagramphotoviewer.model.instagram.Caption;
import com.codepath.instagramphotoviewer.model.instagram.Image;
import com.codepath.instagramphotoviewer.model.instagram.Photo;
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
        Picasso.with(getContext()).load(standardResolutionImage.getUrl()).into(ivPhoto);
        Picasso.with(getContext()).load(photo.getUser().getProfilePictureUrl()).into(ivUserPhoto);
        return convertView;
    }
}
