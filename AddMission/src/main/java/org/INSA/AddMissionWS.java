package org.INSA;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.*;


@WebService(serviceName="AddMission")
public class AddMissionWS {
    String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_062";
    String user = "projet_gei_062";
    String pswd = "Uph3Quie";
    @WebMethod(operationName="addMission")
    public void addMission(@WebParam(name="user_id") int user_id, @WebParam(name="titre") String titre) {
        try (Connection connexion = DriverManager.getConnection(url, user, pswd)) {
        	int type = -2;
        	String req0 = "SELECT type from user WHERE id = ?";
        	try (PreparedStatement statement = connexion.prepareStatement(req0)) {
        		statement.setInt(1, user_id);
        		try (ResultSet resultSet = statement.executeQuery()) {
        	        if (resultSet.next()) {
        	            type = resultSet.getInt("type"); 
        	        }
        	    }
        	} catch (SQLException e) {
                System.out.println("Erreur get User Type: " + e.getMessage());
        	}
        	switch(type) {
	        	case -1: //CLIENT
	        		String req = "INSERT INTO mission (titre, client_id, status) VALUES (?, ?, ?)";
	        		try (PreparedStatement statement = connexion.prepareStatement(req)) {
	                    statement.setString(1, titre);
	                    statement.setInt(2, user_id);
	                    statement.setString(3, "waiting");
	                    statement.executeUpdate();
	                    System.out.println("OK");
	                }
	        	case 0: //VALIDEUR
	        		String req2 = "INSERT INTO mission (titre, valideur_id, status) VALUES (?, ?, ?)";
	        		try (PreparedStatement statement = connexion.prepareStatement(req2)) {
	                    statement.setString(1, titre);
	                    statement.setInt(2, user_id);
	                    statement.setString(3, "validated");
	                    statement.executeUpdate();
	                    System.out.println("OK");
	                }
	        	case 1: //BENEVOLE
	        		String req3 = "INSERT INTO mission (titre, benevole_id, status) VALUES (?, ?, ?)";
	        		try (PreparedStatement statement = connexion.prepareStatement(req3)) {
	                    statement.setString(1, titre);
	                    statement.setInt(2, user_id);
	                    statement.setString(3, "waiting");
	                    statement.executeUpdate();
	                    System.out.println("OK");
	                }
        	}
            
        } catch (SQLException e) {
                System.out.println("Erreur ajout mission: " + e.getMessage());
        }
    }
}