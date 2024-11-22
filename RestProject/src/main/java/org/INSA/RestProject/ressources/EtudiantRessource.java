package org.INSA.RestProject.ressources;

import org.INSA.RestProject.Etudiant;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("etudiant")
public class EtudiantRessource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Etudiant getEtudiant(@Context UriInfo uriInfo) {
		Etudiant etudiant=new Etudiant();
		etudiant.setId(1);
		etudiant.setNom("Fau");
		etudiant.setPrenom("Benoit");
		etudiant.addLink(getUriforSelf(uriInfo, etudiant), "self", "GET");
		etudiant.addLink(getUriforStage(uriInfo), "Stage", "GET");
		return etudiant;
	}
	
	@GET
	@Path("/{idEtudiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Etudiant getEtudiant(@PathParam("idEtudiant") int id) {
		Etudiant etudiant=new Etudiant();
		etudiant.setNom("Saad");
		etudiant.setPrenom("Mongi");
		return etudiant;
	}
	
	@POST
	@Path("/{idEtudiant}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addEtudiant(Etudiant etudNouv) {
		System.out.println("Ajout de l'étudiant " + etudNouv.getNom() + " " + etudNouv.getPrenom() + " réussie!");
		System.out.println("Son binome est " + etudNouv.getBinome().getPrenom() + " " + etudNouv.getBinome().getNom());
		return "Success";
	}
	
	private String getUriforStage(UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder()
				.path(StageRessource.class)
				.build()
				.toString();
		return url;
	}
	
	private String getUriforSelf(UriInfo uriInfo, Etudiant etudiant) {
		String url = uriInfo.getBaseUriBuilder()
				.path(StageRessource.class)
				.path(Long.toString(etudiant.getId()))
				.build()
				.toString();
		return url;
	}
}
