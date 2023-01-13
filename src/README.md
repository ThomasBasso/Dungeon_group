Version JDK utilisée : **19**

## Présentation du projet :

Le but du projet était de réaliser un jeu du donjon. Ce jeu consiste à aller dans les différents donjons.
Lorsque l'on est dans un donjon pour passer au prochain, nous devons gagner le combat de chaque room. En effet,
un donjon est composé de plusieurs rooms dans ces dernières un combat attend le joueur contre différents monstres.
Lors de son combat le joueur à la capacité d'avoir accès à son inventaire pour reprendre des forces.

## Déroulement du jeu :
### 1. Initialisation du jeu :
Lorsque la partie commence, deux scénarios sont possibles :
1. Aucunes parties n'a été enregistré du coup, on demande au joueur son nom
2. Il y a des parties d'enregistré, on demande au joueur s'il souhaite en reprendre une ou en commencer une nouvelle

### 2. Début du jeu :
Lorsque la partie se lance le joueur entre dans le premier donjon le niveau EASY. Lorsqu'il se trouve dans le hall du
donjon, il se retrouve à pouvoir choisir une direction. Cette direction permettra de déplacer dans la première room
ce qui signifie le début de votre aventure. 

### 3. Durant le combat :
Durant le combat, le joueur n'est attaqué que s'il attaque un monstre. Il a donc la possibilité de faire 2 actions :
- Attaquer le monstre 
- Accéder à son inventaire
Lorsqu'un monstre meurt le joueur récupère l'argent que ce dernier posséder.

### 4. Dans l'inventaire :
Le joueur à accès a son inventaire lorsqu'il se retrouve dans une room, cela lui permet d'utiliser ou non des items 
présents dans ce dernier pour lui apporter de la vie supplémentaire (la vie du joueur est limité à 100).

### 5. Fin du combat :
A la fin d'un combat, si la room possède des objets a loot le joueur les récupèrent, puis il aura différentes actions 
possibles s'il ne s'agit pas de la dernière room du donjon :
- Aller dans la room suivante
- Regarder la composition des autres pièces à visiter
- Arrêter la partie

### 6. Fin du jeu :
Le jeu se termine lorsque le joueur gagne tous ces combats dans tous les donjons. Mais il a aussi la possibilité  de
mettre fin au jeu à la fin d'un donjon ou à la fin d'une room même en l'ayant gagné, dans ce cas-là, on lui proposera 
alors de sauvegarder sa partie pour la reprendre plus tard.

## Améliorations possibles :
- Ajout d'un magasin pour acheter des items à rajouter à l'inventaire
- Amélioration du système de combat (ajout de différents types de coups)
- Ajout d'une interface graphique



