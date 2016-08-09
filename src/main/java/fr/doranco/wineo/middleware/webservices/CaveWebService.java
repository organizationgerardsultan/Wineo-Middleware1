package fr.doranco.wineo.middleware.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.doranco.wineo.middleware.objetmetier.bouteille.Bouteille;
import fr.doranco.wineo.middleware.objetmetier.cave.Cave;
import fr.doranco.wineo.middleware.services.ICaveService;

@WebService
@Path(value = "/cave")
public class CaveWebService {

	@EJB
	private ICaveService caveService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenirCaves() {
		
		final List<Cave> caves = caveService.obtenirCaves();
		
		return Response
				.ok(caves)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@POST
	public Response creerCave(
			@FormParam("nomProprietaire") String nomProprietaire,
			@FormParam("capaciteMaximale") int capaciteMaximale
	) {
	
		final Cave cave = new Cave();
		cave.setNomProprietaire(nomProprietaire);
		cave.setCapaciteMaximale(capaciteMaximale);
		cave.setBouteilles(new ArrayList<Bouteille>());
		
		final String referenceCree = caveService.creerCave(cave);
		
		return Response
				.ok(referenceCree)
				.build();
	}
	
}
