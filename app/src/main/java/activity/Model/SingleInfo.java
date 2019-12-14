package activity.Model;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class SingleInfo {
    private int mal_id;
    private String image_url;
    private String title;
    private String title_english;
    private String type;
    private String source;
    private String episodes;
    private String duration;
    private String rating;
    private double score;
    private int rank;
    private int popularity;
    private String synopsis;
    private Bitmap img;
    private  ArrayList<SingleInfo_Genre> genres = new ArrayList<>() ;
    private  ArrayList<SingleInfo_Studio> studio = new ArrayList<>();

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_english() {
        return title_english;
    }

    public void setTitle_english(String title_english) {
        this.title_english = title_english;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getScore() {
        Double a=score;
        return a.toString();
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRank() {
        Integer a=rank;
        return a.toString();
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public ArrayList<SingleInfo_Genre> getGenre() {
        return genres;
    }

    public ArrayList<SingleInfo_Studio> getStudio() {
        return studio;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStudiosStr(){
        StringBuilder sb = new StringBuilder();
        for(SingleInfo_Studio stu : studio){
            sb.append(" -"+stu.getName()+"\n");
        }
        return sb.toString();

    }
    public String getGenreStr(){
        StringBuilder sb = new StringBuilder();
        for(SingleInfo_Genre g : genres){
            sb.append(" -"+g.getName()+"\n");
        }
        return sb.toString();
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}