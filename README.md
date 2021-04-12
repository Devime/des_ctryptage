# des_ctryptage

---
Petite application en kotlin qui effectue quelques chiffrement symétrique.
---
### César et Vigenere
César et Vigenere ont utilise tous deux le chiffrement défini sur l'activité de César.
Il suffit d'entrer un entier en tans que clef, effectifs sur tous les caractères affichable mais pas de caractère accentuer.

### Atbash 

Effectifs sur tous les affichable sauf accentue. On garde le principe de atbash mais au lieu d'utiliser tous l'alphabet on prends tous les affichable.

### Hill

Ici on fais les calculs avec une matrice 2x2 et étendu à tous les caractères affichable non accentuer
Si la matrice ne convient pas un Toast apparaît.

### Transposition rectangulaire

On effectue la transmission rectangulaire , le texte chiffré est rendu avec les espace qui correspondent au texte en claire pas 
d'autre espace pour séparer les différentes colone comme sur le site.
On affiche le tableau qui correspond à la transposition.

### Playfair

On effectue le chiffrement de playfair avec un carré de Polybe 6x6 pour pouvoir intégrer tous l'alphabet et les chiffres deb0 à 9.
On affiche les tableaux servant au chiffrement.

### Polybe

Carré de Polybe 6x6 codé en dure de a à z et 0 à 9.
On l'affiche.
Il est egalement possible de créer un carre remplis aleatoirement( c'est plutot un remplis normalement et on intervertie les élèments aleatoirement dedans)

### Delastelle

On utilise un carré créé par l'activité Polybe( ou un carré aléatoire graçe au boutton) et on fait le surchiffrement. Il faut juste entrer un entier en tant que clef.
On affiche le carre de polybe utiliser.


