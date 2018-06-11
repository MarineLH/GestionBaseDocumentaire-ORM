package gestion.articles;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Auteur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(length = 25, nullable = false)
	private String nom;

	@Basic
	@Column(length = 25, nullable = false)
	private String domaineDeSpecialite;

	@ManyToOne
	private CentreRecherche centreRecherche;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDomaineDeSpecialite() {
		return domaineDeSpecialite;
	}

	public void setDomaineDeSpecialite(String domaineDeSpecialite) {
		this.domaineDeSpecialite = domaineDeSpecialite;
	}

	public CentreRecherche getCentreRecherche() {
		return centreRecherche;
	}

	public void setCentreRecherche(CentreRecherche unCentre) {
		this.centreRecherche = unCentre;
	}

	public Long getId() {
		return id;
	}

}
