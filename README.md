# Projet Revolutmiage
Auteur: Clément Mercier

## Informations-Generales
***
Le projet Bibliothèque est réalisé dans le cadre du cours de Patrons d'analyse et de conception.


## Technologies
***
Les technologies utilisées dans ce projet sont:

* [JAVA](https://openjdk.java.net/) - Programming langage and runtime environnement JAVA 11
* [SpringBoot](https://start.spring.io/) - Framework de développement JAVA pour microservices
* [H2](https://www.javatpoint.com/spring-boot-h2-database) - Relational database management system written in Java

## Prerequis
***
Les technologies suivantes doivent être installées :

* JAVA 11
* MAVEN 3
* Docker

## Installation
***
Ouvrir un CMD pour que les deux services soit exécutés
Executer la commande suivante pour lancer les services ```docker-compose up```


## Usage
***
* Banque service
  * Vous pouvez accéder à la doc de l'API avec ``http://localhost:8000/swagger-ui/index.html``

* Vente service
  * Pour créer une opération, il suffit de faire appel à la route ``http://localhost:8080/api/payer`` avec le corps suivant : 
  ```
  {
  "numeroCarte": "3649143529604623",
  "code": "5400",
  "sansContact": false,
  "montant": 150.50
  }
  ```
