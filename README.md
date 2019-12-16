# Application Mobile 4A : My animeList

## Auteur
 TARBY Arnaud
 
## Présentation

Application permettant de choisir les animés à partir d'une liste récupérer par appel api avec accès à différentes pages. L'utilisateur à aussi accés à une page lui montrant sur un graphe circulaire les statistique concernant les genres des animés ayant était ajouté à la liste. Enfin l'utilisateur ne perd pas ses données lorsqu'il quitte l'application.

## Contenu globale: 

- 2 Activités
- 3 Fragments
- Material design
- Architecture MVC
- Appels API
- RecyclerView
- Ajout d'objets à une liste sauvegardé en Internal Storage.
- Mise en cache des appels api.
- Graphe de statistiques des genres vue ( librairie : MPAndroidChart )
	

### Ecran de connexion : 

- L'utilisation requier que l'utilisateur choisisse un nom pour pouvoir sauvegarder ses données.

<img src="img_readme/connexion_design.jpg" alt="design" width="250">   <img src="img_readme/connexion.jpg" alt="connect" width="250">


### Ecran Principale : Fragment - Liste d'animé :

- Affiche les informations de base d'un animé et des boutons "favoris" pour les ajoutés à la liste de l'utilisateur.

<img src="img_readme/main_page.jpg" alt="liste" width="250"> <img src="img_readme/main_page_favoris.jpg" alt="boutons" width="250"> <img src="img_readme/bouton.jpg" alt="next" width="250">

- Drawer pour changer de fragment : 

<img src="img_readme/drawer.jpg" alt="drawer">


### Ecran Principale : Fragment - Profile :

- Affiche des nombres d'épisode regardés ainsi que les statistiques des genres regardés dans un graphe circulaire et possibilité de cliquer sur les éléments du graphe.

<img src="img_readme/profile_page.jpg" alt="profile" width="250"> <img src="img_readme/profile_page_click.jpg" alt="profile_click" width="250">

### Ecran Principale : Fragment - Quiter :

- Permet à l'utilisateur de quitter la vue principale et sauvegarder sa liste ou alors de retourné à la page de liste d'animé.

<img src="img_readme/quit.jpg" alt="quit" width="250">

- On peut retrouver que si on s'identifie avec un nom déjà entré la liste de favoris sera récupérer à partir des fichier de sauvegarde: 

<img src="img_readme/otherAccout.jpg" alt="otherAccount" width="250">
