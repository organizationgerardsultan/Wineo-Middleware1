package fr.doranco.wineo.middleware.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.doranco.wineo.middleware.objetmetier.bouteille.Bouteille;
import fr.doranco.wineo.middleware.objetmetier.cave.Cave;

/**
 * Un DAO JPA de gestion des caves. 
 * 
 * @author Snekkja JFDC
 */
@Stateless
@Transactional
public class CaveDao {

	@PersistenceContext
	private EntityManager em;

	public void persister(final Cave cave) {
		
		em.persist(cave);
	}
	
	public void modifier(final Cave cave) {
		
		em.merge(cave);
	}
	
	public void retirer(final String reference) {
		
		em.remove(em.getReference(Cave.class, reference));
	}
	
	public Cave obtenir(final String reference) {
		
		return em.find(Cave.class, reference);
	}
	
	public List<Cave> obtenir() {
		
		final String requeteJpql = "SELECT c FROM Cave c";
		
		final TypedQuery<Cave> requeteTypee = em.createQuery(requeteJpql, Cave.class);
		
		return requeteTypee.getResultList();
	}
	
	public boolean exister(final String reference) {

		boolean resultat = false;
		
		try {
			em.getReference(Cave.class, reference);
			resultat = true;
		} catch (EntityNotFoundException e) {
			resultat = false;
		}
		
		return resultat;
	}
	
}
