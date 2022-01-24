# LABO 01 COURS AMT

---

## Utilisation de l'application

---

Une fois la branche main du projet cloner, on peut procéder comme suit :

- se déplacer dans le dossier image et lancer les machines docker avec la commande 
>```docker compose up --build```

- Se déplacer dans le dossier __location-Car-API-Swagger__
vous y trouverez le fichier pom.xml pour l'api de location de voiture. Exécuter 
>```mvn clean install```

Vous pouvez ainsi lancer le programme en exécutant la ligne :

>```java -jar target/location-car-api-1.0.0.jar```

Pour voir la documentation swagger, se rendre à l'adresse __http://localhost:9095/api/__

- Se déplacer également dans le dossier __Amt-Labo3-User__
vous y trouverez le fichier pom.xml pour l'api de gestion des utilisateurs. Exécuter aussi 
>```mvn clean install```

Vous pourrez alors lancer l'application au moyen de la commande
>```java -jar target\users-1.0.0.jar```

Pour voir la documentation swagger de l'API "Utilisateurs" se rendre à l'adresse __http://localhost:8080/api/__


---
## Problème connu
---
Nous n'avons pas eu assez de temps pour réaliser le point 7, la communication entre les APIs.