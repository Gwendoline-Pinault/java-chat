# Projet de chat en Java

Lien vers le [projet GitHub](https://github.com/Gwendoline-Pinault/java-chat)

## Installation
1. Clonez le dépôt.
2. Placez-vous dans le répertoire ```/src```.
3. Compilez les fichiers java : ```javac -d ./bin *```
4. Lancez le serveur depuis le terminal :
``` java EchoServerGUI ```
5. Lancez le client depuis le terminal
``` java EchoClientGUI ```

## Fonctionnement
Lorsque l'on lance le client, une fenêtre s'ouvre pour demander le nom de l'utilisateur qui se connecte. Saisir le nom du client et cliquer sur le bouton "Se connecter". Le client est alors connecté au chat et peut discuter avec d'autres utilisateurs.

Pour envoyer un message privé à un utilisateur : écrire "@Nom_de_l'utilisateur " avant votre message : ceci envoie un seul message à l'utilisateur en question, s'il est en ligne.
Lorsqu'un utilisateur se connecte, vous recevez l'information de la personne qui vient de se connecter au serveur.

Pour se déconnecter du chat, cliquez sur le bouton "Déconnexion".
Les autres utilisateurs reçoivent l'information de votre déconnexion.

## Fonctionnalités implémentées
- Horodatage des messages
- Connexion de plusieurs clients au serveur
- Communication possible entre deux clients via les messages privés
- Déconnexion du client grâce au bouton de déconnexion

Je n'ai pas eu le temps d'implémenter les conversations en tant que salon dédié et donc de garder un même fil de discussion sur une conversation séparée.