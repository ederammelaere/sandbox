package be.schaubroeck.bookshelf;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="auteur")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Auteur {

	private Long id;
	private String naam;
	private Taal moedertaal;
	private Set<Boek> boeken;
	
	@Id
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="naam")
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="moedertaalid")
	public Taal getMoedertaal() {
		return moedertaal;
	}

	public void setMoedertaal(Taal moedertaal) {
		this.moedertaal = moedertaal;
	}

	@OneToMany(mappedBy="auteur")
	public Set<Boek> getBoeken() {
		return boeken;
	}

	public void setBoeken(Set<Boek> boeken) {
		this.boeken = boeken;
	}
			
}
