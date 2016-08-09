package fr.doranco.wineo.middleware.objetmetier.bouteille;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.rits.cloning.Cloner;

/**
 * Une collection de {@link Recompense récompenses}.
 * 
 * @author Snekkja JFDC
 */
public class Recompenses implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	/** Liste de récompenses */
	private List<Recompense> recompenses;
	
	/** Poids exprimé en décimal*/
	private BigDecimal poids;

	public List<Recompense> getRecompenses() {
		return recompenses;
	}

	public void setRecompenses(List<Recompense> recompenses) {
		this.recompenses = recompenses;
	}

	public BigDecimal getPoids() {
		return poids;
	}

	public void setPoids(BigDecimal poids) {
		this.poids = poids;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.poids)
				.append(this.recompenses)
				.build();
	}

	@Override
	public boolean equals(Object candidat) {
		
		if (candidat == null)
			return false;
		if (candidat == this)
			return true;
		if (!(candidat instanceof Recompenses))
			return false;
		
		Recompenses autre = (Recompenses) candidat;
		
		return new EqualsBuilder()
				.append(this.poids, autre.poids)
				.append(this.recompenses, autre.recompenses)
				.build();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return Cloner.shared().deepClone(this);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("Poids", this.poids)
				.append("Liste des récompenses", this.recompenses)
				.build();
	}
	
}
