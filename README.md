# GestionBaseDocumentaire-ORM
Application Java - Système de gestion de base documentaire contenant des articles scientifiques

## Sujet

Objectif
On souhaite créer un modèle de données pour la gestion d’une base documentaire contenant des
articles scientifiques.
Description du modèle de données
Un   article   est   défini   par   son   titre   et   son   contenu.   Un   article   contient   des   références
bibliographiques : donc un article est lié à d’autres articles. Un article est rédigé par un ou plusieurs
auteurs.
Un auteur est défini par son nom et son domaine de spécialité (par exemple physique, chimie…).
Un auteur est rattaché optionnellement à un centre de recherche.
Un centre de recherche est défini par son nom et son pays.
Vous devez définir un modèle objet et son mapping relationnel en créant les classes Java pour les
entités Article, Auteur et CentreRecherche.
Méthodes à implémenter
Vous devez créer une classe Java utilisant l’API JPA pour fournir les méthodes suivantes :
• Une méthode permettant d’ajouter un centre de recherche dans la base de données.
• Une  méthode  permettant  de chercher  un  centre de  recherche  en  donnant  son  nom  en
paramètre.
• Une méthode permettant d’ajouter en base de données un nouvel auteur ainsi que l’ensemble
de ses articles.
• Une méthode pour retourner la liste des articles pour un domaine de spécialité donné (c’est-
à-dire écrits par un auteur dont le domaine de spécialité correspond à celui donné en
paramètre).
• Une méthode pour retourner la liste des articles pour un pays donné (c’est-à-dire les articles
écrits par un auteur dont le centre de recherche se trouve dans le pays donné).
• Une méthode pour mettre à jour les références bibliographiques. Cette méthode prend en
paramètre le nom d’un article. Elle recherche les articles qui référencent l’article en question
et elle met à jour les références bibliographiques. Un article référence un autre article si son
contenu contient le titre de l’autre article.
Certaines des méthodes ci-dessus nécessitent la création de requêtes. Vous devez utiliser pour cela
le langage JPQL (Java Persistence Query Language).
Vous devez fournir un ou des programmes d’exemple qui utilisent les méthodes demandées ci-
dessus. Ces programmes peuvent rester simples en utilisant des données codées en dur.
Mise en place du projet
Utilisez le modèle de projet Maven téléchargeable à cette adresse :
https://spoonless.github.io/epsi-b3-orm/_downloads/template-orm.zip
Il s’agit d’un projet Maven avec une dépendance vers Hibernate et le pilote JDBC de MySQL.
Dans   le   fichier  persistence.xml,   vous   pouvez   ajouter   la   propriété   suivante   pour   demander   à
Hibernate de créer ou de mettre à jour automatiquement les tables dans la base de données :
<property name="hibernate.hbm2ddl.auto" value="update" />
