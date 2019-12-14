package activity.Model;

import java.util.List;

public class JsonApiObject {
    public List<Anime_info> animeInfos;
    public void JsonApiObject(List<Anime_info> input){
        this.animeInfos = input;
    }
}
