package com.example.mvvmex2.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private int id;
    private String img;
    private Integer rating;
    private Integer runtime;
    @SerializedName("body")
    private String summary;
    private String title;
    private String url;
    private Integer year;

    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", rating=" + rating +
                ", runtime=" + runtime +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", year=" + year +
                '}';
    }

    public Movie(int id, String img, Integer rating, Integer runtime, String summary, String title, String url, Integer year) {
        this.id = id;
        this.img = img;
        this.rating = rating;
        this.runtime = runtime;
        this.summary = summary;
        this.title = title;
        this.url = url;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
