# LABO 01 COURS AMT

---

## Utilisation de l'application

---

Afin de mettre en place l'application, vous pouvez procéder comme suit :

### Mise en Oeuvre avec Docker

- Après avoir cloner le repositoire sur votre ordinateur, vous déplacer vers le dossier _Labo2 partie2/AMT_1_
- Exécuter la commande `mvn clean install`. Celle-ci construira l'archive war dans le dossier du repertoire afin que celui-ci soit déployer.
- Se déplacer dans le dossier ~/Labo01_AMT au moyen d'un terminal et exécuter la commande `docker compose up --build`. Celle-ci devrait lancer les images docker et les configurations nécessaires des images.

### Mise en Oeuvre en local

- Ouvrir le dossier _Labo2 partie2/AMT_1_ que vous venez de cloner au moyen de IntelijIdea
- Configurer et lancer le serveur payara sur IntelijID directement (Vous pouvez également ouvrir le fichier \_docker-compose.yml_décommenter la partie pour créer un docker avec Payara)
- Se connecter en admin sur **localhost:4848** et aller dans _JDBC/JDBC Connections Pools_ et configurer un pool de connection avec les informations suivantes :

  - pool Name : mysql-pool
  - Resource Type: javax.sql.DataSource
  - Database Driver Vendor : MySQL8
  - url : **jdbc:mysql://localhost:3306/lab1_AMT**
  - useSSL: **false**
  - password: **adm**
  - user : **adm**
  - serverTimeZone: **UTC**
  - allowPublicKeyRetrieval: **true**

- Aller dans \_JDBC/JDBC Resources

  - JNDI Name: jdbc/Lab1_AMT
  - Pool Name: mysql-pool

- Retourner dans le dossier cloner dans le sous-dossier qui contient le _pom.xml_ et exécuter la commande _mvn clean install_. Ceci créera dans le dossier target l'application web au format .war
- Retourner dans la console **localhost:4848** dans la section **Applications** cliquer sur **deploy** et sélectionner le fichier .war que vous venez de créer

Pour le lancement de l'application, vous pouvez vous rendre sur votre navigateur à l'adresse localhost:8080/locationApp/
