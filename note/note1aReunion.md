**Note projet S4 :**

comment animer le jeu :
1 chrono qui toutes les x ms déplace les point coloré.
OU on a un gestionaitre de thread qui s'cocupe de déplacer les points.
(ca fera l'objet d'1 cours en L3)

On doit avoir des boulles qui ce ballade de facon alléatoire et a partir de la on complique pas bcp. (rebondir sur des surfaces, etc)
1 par 1 les choses ne sont pas difficile.
Il y a plein d'ajout possible : genre le virus passe par l'air et les gens ne ce touche pas.

Qu'est ce qu'on rend a la fin ?
le docu
Un diagramme qui explique l'architecture (C'est le point d'entré de notre projet)
Il faut que l'app soit facile a lancé en utilisant gradle ou maven.
On mettra les choses qu'on a essayer, les difficultés rencontrés et les fonctionnalités présentent dans le jeu dans le rapport.
Il faut aussi une doc (javadoc)
le seul document a rendre vers la fin est le rapport du projet (conception du projet ,contraintes recontrées ,diagramme et l'architecture du projet ,documentation interne du projet"les classes,les interfaces ..etc")
il faut d 'abord avoir une population aprés chaque objet qui bouge est considéré comme un individu de cette population 
chaque individu aura son etat de santé (malade ou pas )
au debut il faut considerer que le virus circule aléatoirement sur la surface
aprés pour chaque(paramétre : vitesse(temps de gurison ,mouvements des gens) isolement(couvre feu ,confinement) et contagion(contact avec les gens ,circulation du virus par l'air) c des classe héritée ou peut etre des méthodes a redéfinir)
un autre points trés important: il faut respecter l'approche model vue et controlleur
