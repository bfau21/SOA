package org.INSA;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.*;


@WebService(serviceName="UserManager")
public class UserManagerWS {
    String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_062";
    String user = "projet_gei_062";
    String pswd = "Uph3Quie";
    @WebMethod(operationName="addUser")
    public void addUser(@WebParam(name="username") String username, @WebParam(name="type") int type, @WebParam(name="password") String password) {
        try (Connection connexion = DriverManager.getConnection(url, user, pswd)) {
            String req = "INSERT INTO user (username, type, mdp) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                statement.setString(1, username);
                statement.setInt(2, type);
                statement.setString(3, password);
                statement.executeUpdate();
                System.out.println("OK");
            }
        } catch (SQLException e) {
        	if (e.getErrorCode() == 1062) {  
                System.out.println("Nom d'utilisateur déjà existant");
            } else {
                System.out.println("Erreur ajout user: " + e.getMessage());
            }
        }
    }

    @WebMethod(operationName="removeUser")
    public void removeUser(@WebParam(name="username") String username) {
    	try (Connection connexion = DriverManager.getConnection(url, user, pswd)) {
            String req = "DELETE FROM user WHERE username = ?";
            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                statement.setString(1, username);
                statement.executeUpdate();
                System.out.println("OK");
            }
        } catch (SQLException e) {
            e.getErrorCode();
            System.out.println("Erreur suppression user: " + e.getMessage());
        }
    }
}