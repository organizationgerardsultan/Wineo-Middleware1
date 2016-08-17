package fr.doranco.wineo.middleware.webservices;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import fr.doranco.wineo.middleware.objetmetier.bouteille.Bouteille;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleDejaExistanteException;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleInexistanteException;
import fr.doranco.wineo.middleware.objetmetier.bouteille.BouteilleInvalideException;
import fr.doranco.wineo.middleware.services.IBouteilleService;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Link.Builder;


@WebService
@Path("/bouteille")
@Transactional
public class BouteilleWebService {
	
	@Inject
	private IBouteilleService bouteilleService;

	@GET
	@Path("/{reference}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response obtenirBouteille(
			@PathParam("reference") final String reference
	) {
		
		Response reponse = null;
		
		try
		{
			/*
			 * Nous déléguons au service, la responsabilité
			 * de connaitre les règles métier d'obtention d'une bouteille.
			 */
			final Bouteille bouteille = bouteilleService.obtenirBouteille(reference);
			reponse = Response.ok(bouteille).build();
		}
		catch (final BouteilleInexistanteException e)
		{
			reponse = Response.status(NOT_FOUND).build();
		}
		
		return reponse; 
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenirBouteilles() {
		
		List<Bouteille> bouteilles = bouteilleService.obtenirBouteilles();
		
		return Response
				.ok(bouteilles)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerBouteille(final Bouteille bouteille){			
                        		
		/*
		 * Nous transformons les paramètres d'entrée du WS en un objet métier. 
		 *  
		 */
		
                 ResponseBuilder builder;
		
				
		try
		{
                    final Bouteille bouteilleCree = bouteilleService.creerBouteille(bouteille);
			final String reference = bouteilleCree.getReference();
			builder = Response.ok(reference);
                        
		}
		catch (BouteilleDejaExistanteException e)
		{
			builder = Response.status(BAD_REQUEST);
                       
		}
                    
		return builder.build();
                
	}
	
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response modifierBouteille(
			@FormParam("reference") final String reference,
			@FormParam("contenance") final double contenance,
			@FormParam("designation") final String designation,
			@FormParam("annee") final int annee
	) throws BouteilleInexistanteException, BouteilleInvalideException {
		
		final Bouteille bouteille = new Bouteille();
		bouteille.setAnnee(annee);
		bouteille.setContenance(contenance);
		bouteille.setDesignation(designation);
		bouteille.setReference(reference);

		Response reponse = null;
		
		try {
			
			// Nous tentons de modifier notre bouteille.
			final Bouteille bouteilleModifiee = bouteilleService.modifierBouteille(bouteille);
			
			reponse = ok(bouteilleModifiee).build();
			
		} catch (final BouteilleInexistanteException e) {
			
			// Nous n'avons pu modifié une entitée inexistante : 404.
			reponse = status(NOT_FOUND).build();
			
		} catch (final BouteilleInvalideException e) {
			
			// Nous n'avons pu modifié une entitée invalide : 400.
			reponse = status(BAD_REQUEST).build();
			
		}
		
		return reponse;
	}
	
	@DELETE
	@Path("/{reference}")
	public Response supprimerBouteille(
			@PathParam("reference") final String reference
	) {
		
		Response reponse = null;
		
		try {
			
			// Nous tentons de supprimer la bouteille depuis sa référence
			bouteilleService.supprimerBouteille(reference);
			// Nous répondons un HTTP 200 à notre client.
			reponse = Response.ok().build();
			
		} catch(final BouteilleInexistanteException e) {
			
			// Nous répondons un HTTP 404 à notre client.
			reponse = Response.status(Status.NOT_FOUND).build();
			
		}
		
		// Nous retournons la réponse.
		return reponse;
	}
	
}
