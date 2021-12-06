# LABO 01 COURS AMT

---

## Utilisation de l'application

---

Afin de mettre en place l'application, vous pouvez procéder comme suit :

- Faire un clone du projet sur votre ordinateur
- Se déplacer dans le dossier ~/Labo01_AMT au moyen d'un terminal et exécuter la commande `docker compose up --build`. Celle-ci devrait lancer le container mysql et phpmyadmin
- Ouvrir le dossier que vous venez de cloner au moyen de IntelijIdea
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

`Si vous avez utiliser le container docker proposé dans docker-compose, vous n'avez pas besoin de réaliser cette étape`

- Retourner dans le dossier cloner dans le sous-dossier qui contient le _pom.xml_ et exécuter la commande _mvn clean install_. Ceci créera dans le dossier target l'application web au format .war
- Retourner dans la console **localhost:4848** dans la section **Applications** cliquer sur **deploy** et sélectionner le fichier .war que vous venez de créer
- Vous pouvez utiliser l'application en allant sur localhost:8080/<APP_NAME>
