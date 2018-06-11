package gestion.articles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GestionArticlesDAO {

	public void ajouterCentreRecherche(CentreRecherche centre) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("template-persistence-unit");
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(centre);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
			emf.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CentreRecherche> getCentreRechercheParNom(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("template-persistence-unit");
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			List<CentreRecherche> centre = null;
			centre = entityManager
					.createNativeQuery("select * from centrerecherche  where nom = ?", CentreRecherche.class)
					.setParameter(1, name).getResultList();
			return centre;
		} finally {
			entityManager.close();
			emf.close();
		}
	}

	protected void creerAuteurEtArticle(Auteur unAuteur, List<Article> listeArticle) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("template-persistence-unit");
		EntityManager entityManager = emf.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			boolean transactionOk = false;
			try {
				entityManager.persist(unAuteur);
				unAuteur = entityManager.find(Auteur.class, unAuteur.getId());
				entityManager.merge(unAuteur);

				for (Article unarticle : listeArticle) {
					unarticle.setAuteurs(unAuteur);
					entityManager.persist(unarticle);
					entityManager.merge(unarticle);
				}

				transactionOk = true;
			} finally {

				if (transactionOk) {
					entityManager.getTransaction().commit();
				} else {
					entityManager.getTransaction().rollback();
				}
			}
		} finally {
			entityManager.close();
		}
	}

	public List<Article> getArticleParDomaineDeSpecialite(String specif) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("template-persistence-unit");
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			List<Article> listArticle = new ArrayList<>();
			listArticle = entityManager.createQuery(
					"select article from Article as article JOIN article.auteurs as auteur where auteur.domaineDeSpecialite =:domaine",
					Article.class).setParameter("domaine", specif).getResultList();
			entityManager.getTransaction().commit();
			return listArticle;
		} finally {
			entityManager.close();
			emf.close();
		}
	}

	public List<Article> getArticleParPays(String country) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("template-persistence-unit");
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			List<Auteur> listeAuteur = new ArrayList<>();
			listeAuteur = (List<Auteur>) entityManager.createQuery(
					"select auteur from Auteur as auteur JOIN auteur.centreRecherche as centre where centre.pays = :country",
					Auteur.class).setParameter("country", country).getResultList();

			Long auteur_id = null;
			for (Auteur auteur : listeAuteur) {
				auteur_id = auteur.getId();
			}

			List<Article> listeArticle = (List<Article>) entityManager.createQuery(
					"select article from Article as article JOIN article.auteurs as auteur where auteur.id = :auteur_id",
					Article.class).setParameter("auteur_id", auteur_id).getResultList();

			entityManager.getTransaction().commit();
			return listeArticle;
		} finally {
			entityManager.close();
			emf.close();
		}
	}

	public void majReferencesBibliographiques(String nomArticle) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("template-persistence-unit");
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			List<Article> listeArticlesReference = new ArrayList<>();
			listeArticlesReference = (List<Article>) entityManager
					.createQuery("select article from Article as article where article.titre =:nomArticle",
							Article.class)
					.setParameter("nomArticle", nomArticle).getResultList();

			String articleRefNom = null;
			for (Article articleReference : listeArticlesReference) {
				articleRefNom = articleReference.getTitre();
			}

			List<Article> listeArticles = new ArrayList<>();
			listeArticles = (List<Article>) entityManager
					.createQuery("select article from Article as article WHERE article.contenu like :nomArticle",
							Article.class)
					.setParameter("nomArticle", articleRefNom).getResultList();

			Article article = new Article();

			for (Article unArticle : listeArticles) {
				unArticle.setArticlesAssocies(listeArticlesReference);
				unArticle.setArticles(listeArticles);
				article = unArticle;
			}
			entityManager.persist(article);
		} finally {
			entityManager.close();
			emf.close();
		}
	}
}