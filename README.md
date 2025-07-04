# Projet de chat en Java

## Installation
1. Clonez le dépôt.
2. Placez-vous dans le répertoire ```/src```.
3. Compilez les fichiers java : ```javac -d /bin *```
4. Lancez le serveur depuis le terminal :
``` java EchoServerGUI ```
5. Lancez le client depuis le terminal
``` java EchoClientGUI ```

## Fonctionnement
Lorsque l'on lance le client, une fenêtre s'ouvre pour demander le nom de l'utilisateur qui se connecte. Saisir le nom du client et cliquer sur le bouton "Se connecter". Le client est alors connecté au chat et peut discuter avec d'autres utilisateurs.

Pour envoyer un message privé à un utilisateur : écrire "@Nom_de_l'utilisateur " avant votre message : ceci envoie un seul message à l'utilisateur en question, s'il est en ligne.
Lorsqu'un utilisateur se connecte, vous recevez l'information.

Pour se déconnecter du chat, cliquez sur le bouton "Déconnexion".
Les autres utilisateurs reçoivent l'information de votre déconnexion.