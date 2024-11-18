package com.lineate.traineeship.junit.dto;

import java.time.LocalDate;

public class ApodDto {
    private LocalDate date;
    private String url;
    private boolean isHd;
    private String title;
    private ApodType type;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHd() {
        return isHd;
    }

    public void setHd(boolean hd) {
        isHd = hd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ApodType getType() {
        return type;
    }

    public void setType(ApodType type) {
        this.type = type;
    }
}
