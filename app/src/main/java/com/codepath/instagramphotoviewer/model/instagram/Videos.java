package com.codepath.instagramphotoviewer.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Videos {
    @JsonProperty("low_resolution")
    private Video lowResolutionVideo;
    @JsonProperty("standard_resolution")
    private Video standardResolutionVideo;

    public Video getLowResolutionVideo() {
        return lowResolutionVideo;
    }

    public void setLowResolutionVideo(Video lowResolutionVideo) {
        this.lowResolutionVideo = lowResolutionVideo;
    }

    public Video getStandardResolutionVideo() {
        return standardResolutionVideo;
    }

    public void setStandardResolutionVideo(Video standardResolutionVideo) {
        this.standardResolutionVideo = standardResolutionVideo;
    }
}
