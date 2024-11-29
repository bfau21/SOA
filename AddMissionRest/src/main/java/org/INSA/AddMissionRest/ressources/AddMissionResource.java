package org.INSA.AddMissionRest.ressources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("missions")
public class AddMissionResource {
    private static final String URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_062";
    private static final String USER = "projet_gei_062";
    private static final String PASSWORD = "Uph3Quie";

    @POST
    @Path("add/{user_id}/{titre}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMission(@PathParam("user_id") int user_id, @PathParam("titre") String titre) {
        try {
           	Connection connexion = DriverManager.getConnection(URL, USER, PASSWORD);   
         	System.out.println("*****");
            int type = -2;
            String req0 = "SELECT type FROM user WHERE id = ?";
            try (PreparedStatement statement = connexion.prepareStatement(req0)) {
                statement.setInt(1, user_id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        type = resultSet.getInt("type");
                    }
                }
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Erreur get User Type: " + e.getMessage())
                        .build();
            }

            String req;
            switch (type) {
                case -1: // CLIENT
                    req = "INSERT INTO mission (titre, client_id, status) VALUES (?, ?, 'waiting')";
                    break;
                case 0: // VALIDEUR
                    req = "INSERT INTO mission (titre, valideur_id, status) VALUES (?, ?, 'validated')";
                    break;
                case 1: // BENEVOLE
                    req = "INSERT INTO mission (titre, benevole_id, status) VALUES (?, ?, 'waiting')";
                    break;
                default:
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Type d'utilisateur non reconnu")
                            .build();
            }

            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                statement.setString(1, titre);
                statement.setInt(2, user_id);
                statement.executeUpdate();
                return Response.ok("Mission ajoutée avec succès").build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Erreur lors de l'ajout de la mission: " + e.getMessage())
                        .build();
            }

        } catch (SQLException e) {
        	System.out.println("/////");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur connexion base de données: " + e.getMessage())
                    .build();
        }
    }
	
	@GET
	public int test() {
		System.out.println("test");
		return 12;
	}
}
