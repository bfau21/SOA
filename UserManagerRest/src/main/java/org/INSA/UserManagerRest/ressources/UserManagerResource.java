package org.INSA.UserManagerRest.ressources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.*;

@Path("/users")
public class UserManagerResource {
    private static final String URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_062";
    private static final String USER = "projet_gei_062";
    private static final String PASSWORD = "Uph3Quie";

    @POST
    @Path("/add/{username}/{type}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("username") String username,
                            @PathParam("type") int type,
                            @PathParam("password") String password) {
        try (Connection connexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String req = "INSERT INTO user (username, type, mdp) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                statement.setString(1, username);
                statement.setInt(2, type);
                statement.setString(3, password);
                statement.executeUpdate();
                return Response.status(Response.Status.CREATED)
                        .entity("{\"message\":\"Utilisateur ajouté avec succès\"}").build();
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Contrainte unique
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"error\":\"Nom d'utilisateur déjà existant\"}").build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erreur lors de l'ajout de l'utilisateur\"}").build();
        }
    }

    @DELETE
    @Path("/remove/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("username") String username) {
        try (Connection connexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String req = "DELETE FROM user WHERE username = ?";
            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                statement.setString(1, username);
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    return Response.status(Response.Status.OK)
                            .entity("{\"message\":\"Utilisateur supprimé avec succès\"}").build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("{\"error\":\"Utilisateur non trouvé\"}").build();
                }
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erreur lors de la suppression de l'utilisateur\"}").build();
        }
    }
}
