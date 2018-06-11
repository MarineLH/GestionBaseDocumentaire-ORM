package gestion.articles;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		GestionArticlesDAO articlesDAO = new GestionArticlesDAO();

		/** CREATE CENTRE DE RECHERCHE **/
		CentreRecherche centreRechercheFrance = new CentreRecherche();
		centreRechercheFrance.setNom("Centre de recherche Français");
		centreRechercheFrance.setPays("France");
		articlesDAO.ajouterCentreRecherche(centreRechercheFrance);

		/** GET CENTRE DE RECHERCHE BY NAME **/
		List<CentreRecherche> listeCentreRecherche = articlesDAO.getCentreRechercheParNom("Centre de recherche Français");
		System.out.println(listeCentreRecherche);

		/** INIT AUTEUR AND LISTE AUTEURS **/
		CentreRecherche unCentre = listeCentreRecherche.get(0);
		Auteur auteur = new Auteur();
		auteur.setNom("Marine");
		auteur.setCentreRecherche(unCentre);
		auteur.setDomaineDeSpecialite("physique");
		List<Auteur> auteurs = new ArrayList<>();
		auteurs.add(auteur);

		/** INIT ARTICLE AND LISTE ARTICLES **/
		Article article = new Article();
		article.setTitre("un joli titre");
		article.setContenu("un contenu");
		List<Article> articles = new ArrayList<>();
		articles.add(article);

		/** CREATE AUTEUR AND ARTICLE **/
		articlesDAO.creerAuteurEtArticle(auteur, articles);

		/** GET LISTE ARTICLES BY DOMAINE **/
		List<Article> listeArticles = articlesDAO.getArticleParDomaineDeSpecialite("physique");
		for (Article unArticle : listeArticles) {
			System.out.println("L'article trouvé pour la spécialité demandé est : " + unArticle.getTitre() + " - "
					+ unArticle.getContenu());
		}

		/** GET LISTE ARTICLES BY PAYS **/
		List<Article> articlesParPays = articlesDAO.getArticleParPays("angleterre");
		if (articlesParPays.isEmpty()) {
			System.out.print("Pas de resultat");
		} else {
			for (Article article1 : articlesParPays) {
				System.out.println(article1.getTitre() + " " + article1.getContenu());
			}
		}

		/** MAJ DES REFERENCES BIBLIOGRAPHIQUES **/
		articlesDAO.majReferencesBibliographiques("un jolie titre");
	}

}
