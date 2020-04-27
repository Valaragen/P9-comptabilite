[![Build Status](https://travis-ci.org/Valaragen/P9-comptabilite.svg?branch=master)](https://travis-ci.org/Valaragen/P9-comptabilite) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Valaragen_P9-comptabilite&metric=coverage)](https://sonarcloud.io/dashboard?id=Valaragen_P9-comptabilite) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Valaragen_P9-comptabilite&metric=alert_status)](https://sonarcloud.io/dashboard?id=Valaragen_P9-comptabilite)
# MyERP
## Contexte
Votre équipe est en train de réaliser un système de facturation et de comptabilité pour un client. 
Le développement a débuté depuis quelques temps et vous devez commencer à vérifier que l'application fonctionne correctement, qu'elle répond bien aux règles de gestion et les respecte.

## Travail effectué
#### Tests unitaires
**Ajout de tests unitaires** pour tester les différents modules de l’application. 
**Le projet respecte une architecture maven multi modules**.  
Les tests unitaires se trouvent dans le répertoire « **src/main/test/Java** » du module associé.  
**Le nom des classes de tests unitaires** se terminent par le mot clé **'Test'**
 
#### Tests d'intégration
Ajouts de tests d’intégration pour tester la communication avec la base de données et les communications entre les différents modules.  
**Le nom des classes de tests d'integration** se terminent par le mot clé **'IT'**

#### Couverture de code et qualité
Des rapports de **couverture du code par les test** sont générés avec **Jacoco-maven-plugin**. L'analyse de ces rapports et de la qualité du code est faite avec **SonarCloud**.

#### Travis CI 
Mise en place d’un **service d’intégration continue** « **Travis CI** ».  
Dans ce projet, ce service a pour rôle d’exécuter tous les tests à chaque **modification du repository Githu**b et de **fournir un resultat en fonction de l'échec ou non des tests**.  
Il se charge aussi de :
 - Créer l'environnement de développement pour **executer les tests d'integration** à partir d'un fichier **docker-compose.yml**.  
 - Envoyer **les rapports de couverture du code** à **SonarCloud**. 
 
## Lancer les tests

**Les tests se lancent via le build maven**. Des **profiles maven** ont été créés pour **lancer les tests** et **activer les rapports de couverture de code**.
 - Lancer les tests unitaires : `Mvn test`  
 - Lancer uniquement uniquement les tests d’intégrations : `Mvn verify -Pit-only`  
 - Lancer les tests d’intégration et les tests unitaires : `Mvn verify -Pall-test`
 - Activer la couverture du code avec **-Pcoverage**  
_Exemple : couverture du code pour tous les tests : `Mvn verify -Pall-test,coverage`_

## Organisation du répertoire

*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
    *   `dev` : environnement de développement
*   `src` : code source de l'application


## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)

### Lancement

    cd docker/dev
    docker-compose up


### Arrêt

    cd docker/dev
    docker-compose stop


### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up
