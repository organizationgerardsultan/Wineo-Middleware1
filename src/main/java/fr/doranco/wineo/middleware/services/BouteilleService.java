
package fr.doranco.wineo.middleware.services;

import java.util.List;
import java.util.function.Predicate;


import org.apache.commons.lang3.RandomStringUtils;

import fr.doranco.wineo.middleware.dao.BouteilleDao;
import fr.doranco.wineo.middleware.objetmetier.bouteille.Bouteille;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleDejaExistanteException;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleInexistanteException;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleInvalideException;
import fr.doranco.wineo.middleware.objetmetier.contexte.ContexteConsommation;
import fr.doranco.wineo.middleware.objetmetier.contexte.ContexteConsommationInvalideException;
import fr.doranco.wineo.middleware.objetmetier.fournisseur.Fournisseur;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class BouteilleService implements IBouteilleService {
	
	@Inject
	private BouteilleDao bouteilleDao;
	
	// private CaveService caveService;

	@Override
	public Bouteille obtenirBouteille(final String reference) throws BouteilleInexistanteException {
		
		/*
		 * Nous pouvons implémenter des règles métier ici.
		 */
		// caveService.estPresente(reference);
		
		/* 
		 * Nous déléguons la responsabilité de récupération
		 * des informations d'une bouteille en base au DAO.
		 */
		return bouteilleDao.obtenir(reference);
	}
	
	
	public Bouteille creerBouteille(Bouteille bouteille) throws BouteilleDejaExistanteException {

		/*
		 * Nous créons une référence unique,
		 * que nous assignons à notre bouteille.
		 */
		bouteille.setReference(RandomStringUtils.randomAlphanumeric(10));
                
		
		// Nous persistons notre bouteille nouvellement crée.
		return bouteilleDao.creer(bouteille);
                
                
                
	}
	
	@Override
	public void supprimerBouteille(final String reference) throws BouteilleInexistanteException {
		
		// Nous retirons la bouteille à supprimer de l'entrepot.
		bouteilleDao.retirer(reference);
	}
	
	@Override
	public Bouteille modifierBouteille(final Bouteille bouteille)
			throws BouteilleInexistanteException, BouteilleInvalideException {

		return bouteilleDao.modifier(bouteille);
	}
	
	@Override
	public List<Bouteille> obtenirBouteilles() {
		
		return bouteilleDao.obtenir();
	}
	
	@Override
	public List<Bouteille> obtenirBouteilles(final Predicate<Bouteille> condition) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exister(final Predicate<Bouteille> condition) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Bouteille> selectionnerBouteilles(final ContexteConsommation contexte)
			throws IllegalArgumentException, ContexteConsommationInvalideException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bouteille> obtenirBouteillesSemblables(final Bouteille bouteille)
			throws BouteilleInvalideException, BouteilleInexistanteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fournisseur> obtenirVendeurs(final Bouteille bouteille)
			throws BouteilleInvalideException, BouteilleInexistanteException {
		// TODO Auto-generated method stub
		return null;
	}

}
