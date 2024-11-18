package com.lineate.traineeship.junit.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class NasaApod {
    private LocalDate date;
    private String explanation;
    private String url;
    @JsonProperty("hdurl")
    private String hdUrl;
    private String title;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("service_version")
    private String serviceVersion;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    @Override
    public String toString() {
        return "NasaApod{"
                + "date=" + date
                + ", explanation='" + explanation + '\''
                + ", url='" + url + '\''
                + ", hdUrl='" + hdUrl + '\''
                + ", title='" + title + '\''
                + ", mediaType='" + mediaType + '\''
                + ", serviceVersion='" + serviceVersion + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NasaApod)) {
            return false;
        }
        NasaApod nasaApod = (NasaApod) o;
        return Objects.equals(date, nasaApod.date) &&
                Objects.equals(explanation, nasaApod.explanation) &&
                Objects.equals(url, nasaApod.url) &&
                Objects.equals(hdUrl, nasaApod.hdUrl) &&
                Objects.equals(title, nasaApod.title) &&
                Objects.equals(mediaType, nasaApod.mediaType) &&
                Objects.equals(serviceVersion, nasaApod.serviceVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, explanation, url, hdUrl, title, mediaType, serviceVersion);
    }
}
