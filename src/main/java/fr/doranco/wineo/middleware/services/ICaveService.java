package fr.doranco.wineo.middleware.services;

import java.util.List;

import fr.doranco.wineo.middleware.objetmetier.cave.Cave;

public interface ICaveService {

	public Cave obtenirCave(final String reference);
	
	public List<Cave> obtenirCaves();
	
	public String creerCave(final Cave cave);
	
}