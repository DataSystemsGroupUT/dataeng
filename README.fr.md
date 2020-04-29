# Exercices : « containeriser » k8s-wordsmith-demo

Le projet k8s-wordsmith-demo est découpé en 3 parties :

- web : Serveur web en go, pour le frontend
- words : REST API en Java permettant d'interroger la BDD
- db : BDD PostgreSQL qui contient les mots à afficher

Le but de ces exercices est de « containeriser » cette application.
Cela se fait en trois étapes :

- écriture des Dockerfiles, afin de construire les images des containers
- écriture du Compose file, afin de faire tourner l'application en dev
- déploiement sur Kubernetes


## Exercice 1 : Dockerfiles

Le but de l'exercice est d'écrire les Dockerfiles pour les 3 containers.

Commencez par faire un `git clone` du projet. Vous allez devoir
créer un Dockerfile pour chacun des 3 services. Nous conseillons
de placer chaque Dockerfile dans le répertoire correspondant
(web, words, db).

Les paragraphes suivants décrivent les instructions d'installation
pour chaque service.

Note: à ce stade, on veut seulement construire les images et vérifier
qu'elles se lancent (`web` et `words` doivent afficher un bref message
pour indiquer qu'ils tournent), mais on ne cherche pas à lancer
l'application en entier ou à se connecter aux services.
Cela viendra plus tard.


### web

C'est un serveur web en Go. Pour compiler du Go, on peut utiliser
l'image `golang`, ou bien installer les paquetages Go dans
n'importe quelle image de base.

Tout le code est contenu dans un fichier
source unique (`dispatcher.go`), et se compile de la manière suivante :

```
go build dispatcher.go
```

Cela produit un exécutable appelé `dispatcher`, qui se lance comme suit :

```
./dispatcher
Listening on port 80
```

Le répertoire appelé `static` doit être dans le répertoire courant
lors du lancement du serveur.

Informations supplémentaires :

- le serveur écoute sur le port 80
- le compilateur go n'est plus nécessaire une fois le programme compilé


### words

C'est un serveur API REST en Java. Il se compile avec maven.

Sur une distribution Debian/Ubuntu, on peut installer Java et maven comme suit :

```
apt-get install maven openjdk-8-jdk
```

Voici la commande qui permet d'invoquer maven pour compiler le programme :

```
mvn verify
```

Le résultat est un fichier appelé `words.jar` placé dans le répertoire `target`.

Le serveur se lance ensuite avec la commande suivante :
```
java -Xmx8m -Xms8m -jar words.jar
```

(En se plaçant dans le répertoire où se trouve `words.jar`.)

Informations supplémentaires :

- le serveur écoute sur le port 8080
- pour la compilation il faut les paquetages maven et openjdk-8-jdk
- pour l'exécution il faut le paquetage openjdk-8-jdk (maven n'est pas nécessaire)


### db

C'est une base de données PostgreSQL.

La base de donnée doit être initialisée avec le schéma (création de
la base et des tables) et les données (utilisées par l'application).

Le fichier `words.sql` contient les commandes SQL nécessaires.

```
# cat words.sql
CREATE TABLE nouns (word TEXT NOT NULL);
CREATE TABLE verbs (word TEXT NOT NULL);
CREATE TABLE adjectives (word TEXT NOT NULL);

INSERT INTO nouns(word) VALUES
  ('cloud'),
  ('elephant'),
  ('gø language'),
  ('laptøp'),
  ('cøntainer'),
  ('micrø-service'),
  ('turtle'),
  ('whale'),
  ('gøpher'),
  ('møby døck'),
  ('server'),
  ('bicycle'),
  ('viking'),
  ('mermaid'),
  ('fjørd'),
  ('legø'),
  ('flødebolle'),
  ('smørrebrød');

INSERT INTO verbs(word) VALUES
  ('will drink'),
  ('smashes'),
  ('smøkes'),
  ('eats'),
  ('walks tøwards'),
  ('løves'),
  ('helps'),
  ('pushes'),
  ('debugs'),
  ('invites'),
  ('hides'),
  ('will ship');

INSERT INTO adjectives(word) VALUES
  ('the exquisite'),
  ('a pink'),
  ('the røtten'),
  ('a red'),
  ('the serverless'),
  ('a brøken'),
  ('a shiny'),
  ('the pretty'),
  ('the impressive'),
  ('an awesøme'),
  ('the famøus'),
  ('a gigantic'),
  ('the gløriøus'),
  ('the nørdic'),
  ('the welcøming'),
  ('the deliciøus');
```

Informations supplémentaires :

- il est fortement conseillé d'utiliser l'image officielle PostgreSQL qui se trouve sur le Docker Hub (elle s'appelle `postgres`)
- sur la [page de l'image officielle](https://hub.docker.com/_/postgres) sur le Docker Hub, vous trouverez une documentation abondante; la section "Initialization scripts" est particulièrement utile pour comprendre comment charger le fichier `words.sql`
- il est conseillé de protéger l'accès à la base avec un mot de passe, mais dans le cas présent, on acceptera de se simplifier la vie en autorisant toutes les connexions (en positionnant la variable `POSTGRES_HOST_AUTH_METHOD=trust`)


## Exercice 2 : Compose file

Une fois que les trois images se construisent correctement, vous pouvez
passer à l'écriture du Compose file. Nous conseillons de placer le Compose
file à la racine du projet.

À ce stade, on veut s'assurer que les services communiquent bien
entre eux, et que l'on peut se connecter à `web` de l'extérieur.

Note : seul le service `web` doit être accessible de l'extérieur.


## Exercice 3 : Kubernetes

On veut maintenant déployer wordsmith sur Kubernetes, de manière à ce qu'on puisse se connecter à l'interface web depuis l'extérieur.

On va devoir utiliser des images venant d'une *registry*. Pour nous faciliter la tâche, les images sont disponibles sur:

- jpetazzo/wordsmith-db:latest
- jpetazzo/wordsmith-words:latest
- jpetazzo/wordsmith-web:latest

Rappels utiles pour cet exercice :

- le service web écoute sur le port 80, et on souhaite qu'il soit accessible depuis l'extérieur du cluster
- le service `words` écoute sur le port 8080
- le service `db` écoute sur le port 5432
