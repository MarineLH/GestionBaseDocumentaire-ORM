package gestion.articles;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CentreRecherche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Long getId() {
		return id;
	}

	@Basic
	@Column(length = 50, nullable = false)
	private String nom;

	@Basic
	@Column(length = 25, nullable = false)
	private String pays;

	public String toString() {

		return "Centre : " + this.nom + " - Pays : " + this.pays;
	}

}
