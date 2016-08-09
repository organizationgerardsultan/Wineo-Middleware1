package fr.doranco.wineo.middleware.objetmetier.cave;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.rits.cloning.Cloner;

/**
 * Un entrepot
 * 
 * @author Snekkja JFDC
 */
@Entity
@Table(name = "T_ENTREPOT")
public class Entrepot implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Numéro unique de l'entrepot */
	private int numero;

	/** Ensemble de caves uniques */
	private Set<Cave> caves;
	
	/** Capacité maximale */
	private Integer capaciteMaximale;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENT_NUMERO")
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@OneToMany
	public Set<Cave> getCaves() {
		return caves;
	}

	public void setCaves(Set<Cave> caves) {
		this.caves = caves;
	}

	@Column(name = "ENT_CAPACITE_MAX")
	public Integer getCapaciteMaximale() {
		return capaciteMaximale;
	}

	public void setCapaciteMaximale(Integer capaciteMaximale) {
		this.capaciteMaximale = capaciteMaximale;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.capaciteMaximale)
				.append(this.caves)
				.build();
	}

	@Override
	public boolean equals(Object candidat) {
		
		if (candidat == null)
			return false;
		if (candidat == this)
			return true;
		if (!(candidat instanceof Entrepot))
			return false;
		
		Entrepot autre = (Entrepot) candidat;
		
		return new EqualsBuilder()
				.append(this.capaciteMaximale, autre.capaciteMaximale)
				.append(this.caves, autre.caves)
				.build();
	}

	@Override
	public Entrepot clone() {
		return Cloner.shared().deepClone(this);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("Capacité maximale", this.capaciteMaximale)
				.append("Caves", this.caves)
				.build();
	}
	
}
