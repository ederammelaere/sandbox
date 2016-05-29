package be.schaubroeck.bookshelf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "boek")
public class Boek {

	private Long id;
	private String titel;
	private Auteur auteur;

	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	@Column(name="titel")
	public void setTitel(String titel) {
		this.titel = titel;
	}

	@ManyToOne
	@JoinColumn(name="auteurid")
	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

}
