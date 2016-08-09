package fr.doranco.wineo.middleware.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.RandomStringUtils;

import fr.doranco.wineo.middleware.dao.CaveDao;
import fr.doranco.wineo.middleware.objetmetier.cave.Cave;

/**
 * Un service de gestion des caves.
 * 
 * @author Snekkja JFDC
 */
@Stateless
public class CaveService implements ICaveService {
	
	@EJB
	private CaveDao caveDao;

	@Override
	public Cave obtenirCave(String reference) {
		return caveDao.obtenir(reference);
	}

	@Override
	public List<Cave> obtenirCaves() {
		return caveDao.obtenir();
	}

	@Override
	public String creerCave(final Cave cave) {

		cave.setReference(RandomStringUtils.randomAlphanumeric(5));
		
		caveDao.persister(cave);
		
		return cave.getReference();
	}
	
}
