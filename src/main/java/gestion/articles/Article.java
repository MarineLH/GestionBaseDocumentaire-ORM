package gestion.articles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(length = 25, nullable = false)
	private String titre;

	@Basic
	@Column(nullable = false, columnDefinition = "TEXT")
	private String contenu;

	@ManyToMany
	private List<Article> articlesAssocies = new ArrayList<>();

	@ManyToMany(mappedBy = "articlesAssocies")
	private List<Article> articles = new ArrayList<>();

	@ManyToMany
	private List<Auteur> auteurs = new ArrayList<>();

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public List<Article> getArticlesAssocies() {
		return articlesAssocies;
	}

	public void setArticlesAssocies(List<Article> articlesAssocies) {
		this.articlesAssocies = articlesAssocies;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(Auteur unAuteur) {
		this.auteurs.add(unAuteur);
	}

	public Long getId() {
		return id;
	}

}
