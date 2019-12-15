package activity.Model;

import java.io.Serializable;
import java.util.HashMap;

public class FavorisList implements Serializable {
    private HashMap<Integer,SingleInfo> favList;
    public FavorisList () {
        this.favList = new HashMap<>();
    }
    public boolean isFav (Integer i) {
        if (favList.keySet().contains(i)){
            return true;
        } else {
            return false;
        }
    }
    public void addFav(SingleInfo info){
        if(!favList.keySet().contains(info.getMal_id())) {
            favList.put(info.getMal_id(), info);
            System.out.println("Ajout de l'anime:" + info.getTitle());
        } else {
          System.out.println("Cet anime est déjà dans la liste des favoris");
        }
    }
    public  void delFav(Integer id){
        if(favList.keySet().contains(id)){
            System.out.println("Suppr de "+ favList.get(id).getTitle() + "de la liste");
            favList.remove(id);
        } else {
            System.out.println("Cet anime est déjà supprimer de la liste des favoris");
        }
    }
    public HashMap<Integer,SingleInfo> getCurrentFavList() {
        return favList;
    }

}
