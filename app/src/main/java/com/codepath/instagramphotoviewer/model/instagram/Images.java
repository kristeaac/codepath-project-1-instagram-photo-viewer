package com.codepath.instagramphotoviewer.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    @JsonProperty("standard_resolution")
    private Image standardResolutionImage;

    public Image getStandardResolutionImage() {
        return standardResolutionImage;
    }

    public void setStandardResolutionImage(Image standardResolutionImage) {
        this.standardResolutionImage = standardResolutionImage;
    }
}
