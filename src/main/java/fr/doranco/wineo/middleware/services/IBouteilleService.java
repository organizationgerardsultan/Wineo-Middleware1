package fr.doranco.wineo.middleware.services;

import java.util.List;
import java.util.function.Predicate;

import fr.doranco.wineo.middleware.objetmetier.bouteille.Bouteille;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleDejaExistanteException;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleInexistanteException;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleInvalideException;
import fr.doranco.wineo.middleware.objetmetier.contexte.ContexteConsommation;
import fr.doranco.wineo.middleware.objetmetier.contexte.ContexteConsommationInvalideException;
import fr.doranco.wineo.middleware.objetmetier.fournisseur.Fournisseur;

/**
 * Un contrat d'un service de gestion des bouteilles.
 * 
 * @author Snekkja JFDC
 */
public interface IBouteilleService {

	public Bouteille obtenirBouteille(final String reference)
		throws BouteilleInexistanteException;
	
	public List<Bouteille> obtenirBouteilles();
	
	public String consignerBouteille(final Bouteille bouteille)
		throws BouteilleInvalideException, BouteilleDejaExistanteException;
	
	public void supprimerBouteille(final String reference)
		throws BouteilleInexistanteException;
	
	public Bouteille modifierBouteille(final Bouteille bouteille)
		throws BouteilleInexistanteException, BouteilleInvalideException;
	
	/**
	 * Obtenir les bouteilles du catalogue selon une condition discriminante.
	 * 
	 * @param condition Une condition discriminante.
	 * @return Les bouteilles du catalogue répondant à la condition discriminante.
	 * 
	 * @throws IllegalArgumentException Si la condition fournie est invalide.
	 */
	public List<Bouteille> obtenirBouteilles(final Predicate<Bouteille> condition)
			throws IllegalArgumentException;
	
	/**
	 * Vérifier l'existence d'au moins une bouteille répondant à la condition discriminante.
	 * 
	 * @param condition Une condition discriminante.
	 * @return Vrai si il existe au moins une bouteille répondant à la condition discriminante.
	 * 
	 * @throws IllegalArgumentException Si la condition fournie est invalide.
	 */
	public boolean exister(final Predicate<Bouteille> condition)
			throws IllegalArgumentException;
	
	/**
	 * Selectionner de une à n bouteilles adaptées à un {@link ContexteConsommation contexte de consommation}.
	 * 
	 * @param contexte Un contexte de consommation.
	 * @return Les bouteilles adaptées au contexte de consommation.
	 * 
	 * @throws IllegalArgumentException Si l'un des paramètres fournis n'est pas valide.
	 * @throws ContexteConsommationInvalideException Si le contexte de consommation fourni est invalide.
	 */
	public List<Bouteille> selectionnerBouteilles(final ContexteConsommation contexte)
			throws IllegalArgumentException, ContexteConsommationInvalideException;
	
	/**
	 * Obtenir les bouteilles considérées comme semblable à une bouteille candidate.
	 *  
	 * @param bouteille Une bouteille candidate.
	 * @return Les bouteilles considérées comme semblable à la bouteille candidate.
	 * 
	 * @throws BouteilleInvalideException Si la bouteille fournie est invalide.
	 * @throws BouteilleInexistanteException Si la bouteille fournie n'existe pas.
	 */
	public List<Bouteille> obtenirBouteillesSemblables(final Bouteille bouteille)
			throws BouteilleInvalideException, BouteilleInexistanteException;
	
	/**
	 * Obtenir les fournisseurs vendant une bouteille candidate.
	 * 
	 * @param bouteille Une bouteille candidate.
	 * @return Les fournisseurs vendant la bouteille candidate.
	 * @throws BouteilleInexistanteException Si la bouteille candidate n'existe pas.
	 * @throws BouteilleInvalideException Si la bouteille candidate n'est pas valide.
	 */
	public List<Fournisseur> obtenirVendeurs(final Bouteille bouteille)
			throws BouteilleInvalideException, BouteilleInexistanteException;
	
}
