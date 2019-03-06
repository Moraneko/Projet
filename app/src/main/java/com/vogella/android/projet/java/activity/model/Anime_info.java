package com.vogella.android.projet.java.activity.model;

public class Anime_info {

    private int mal_id ;
    private int rank;
    private String title;
    private String url;
    private String image_url;
    private String type;
    private int episode;
    private String start_date;
    private String end_date;
    private int members;
    private double score;

    public int getMal_id() {
        return mal_id;
    }

    public int getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getType() {
        return type;
    }

    public int getEpisode() {
        return episode;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getMembers() {
        return members;
    }

    public double getScore() {
        return score;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setScore(double score) {
        this.score = score;
    }


}
