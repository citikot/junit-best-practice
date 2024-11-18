package com.lineate.traineeship.junit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "apod")
public class ApodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "date not NULL")
    private LocalDate date;
    private String url;
    private boolean hd;
    private String title;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return hd;
    }

    public void setHd(boolean hd) {
        this.hd = hd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ApodEntity{"
                + "id='" + id + '\''
                + ", date=" + date
                + ", url='" + url + '\''
                + ", hd='" + hd + '\''
                + ", title='" + title + '\''
                + ", type='" + type + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApodEntity)) {
            return false;
        }
        ApodEntity entity = (ApodEntity) o;
        return hd == entity.hd &&
                Objects.equals(id, entity.id) &&
                Objects.equals(date, entity.date) &&
                Objects.equals(url, entity.url) &&
                Objects.equals(title, entity.title) &&
                Objects.equals(type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, url, hd, title, type);
    }
}
