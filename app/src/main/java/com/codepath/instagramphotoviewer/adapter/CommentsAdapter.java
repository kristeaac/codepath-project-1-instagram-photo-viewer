package com.codepath.instagramphotoviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.instagramphotoviewer.model.instagram.Comment;
import com.codepath.instragamphotoviewer.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

public class CommentsAdapter extends ArrayAdapter<Comment> {
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    public CommentsAdapter(Context context, List<Comment> comments) {
        super(context, android.R.layout.simple_list_item_1, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Comment comment = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        }

        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvCommenterUsername);
        TextView tvComment = (TextView) convertView.findViewById(R.id.tvComment);
        TextView tvPostedTime = (TextView) convertView.findViewById(R.id.tvPostedTime);
        ImageView ivUserPhoto = (ImageView) convertView.findViewById(R.id.ivCommenterPhoto);
        tvUsername.setText(comment.getFrom().getUsername());
        tvComment.setText(comment.getText());
        String createdTime = comment.getCreatedTime();
        if (createdTime != null) {
            tvPostedTime.setText(PRETTY_TIME.format(new Date(Long.parseLong(createdTime) * 1000)));
        }
        ivUserPhoto.setImageResource(0);
        Picasso.with(getContext()).load(comment.getFrom().getProfilePictureUrl()).into(ivUserPhoto);

        return convertView;
    }

}
