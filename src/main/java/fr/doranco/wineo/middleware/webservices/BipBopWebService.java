package fr.doranco.wineo.middleware.webservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@WebService
@Path(value="/bipbop")
public class BipBopWebService {

	@GET
	@Path(value = "/heure")
	public String obtenirHeure() {
		
		// Nous créons le format de notre date. 22/12/2016
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy");

		// Nous utilisons le format pour créer
		// une chaine de caractère représentant la date du jour.
		return formatter.format(LocalDateTime.now());
	}
	
	@GET
	@Path(value = "/bonjour")
	public String saluer(
			@QueryParam("nom") String nom
	) {
		return "Bonjour, " + nom;
	}
	
	@POST
	@Path(value = "/miammiam")
	public String prendreCommande(
			@FormParam("menu") String referenceMenu
	) {
		return "Bon miammiam avec notre menu " + referenceMenu;
	}
	
	@GET
	@Path(value = "/{duree}/hammertime")
	@Produces(MediaType.APPLICATION_JSON)
	public String partirPause(
			@PathParam("duree") int tempsPause
	) {
		
		return "Il est temps de partir en pause pour " + tempsPause + "mins.";
	}
	
}
