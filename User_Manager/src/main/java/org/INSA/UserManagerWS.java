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
    public int addUser(@WebParam(name="username") String username) {
        //Créer un user
//        try (Connection connexion = DriverManager.getConnection(url, user, pswd)) {
//            String req = "INSERT INTO user (username, type, password) VALUES (?, ?, ?)";
//            try (PreparedStatement statement = connexion.prepareStatement(req)) {
//                statement.setString(1, username);
//                statement.setInt(2, type);
//                statement.setString(3, password);
//                statement.executeUpdate();
//                System.out.println("Cléa");
//            }
//        } catch (SQLException e) {
//            e.getErrorCode();
//            System.out.println("Bobi");
//        }
        return 12;
    }

    @WebMethod(operationName="removeUser")
    public int removeUser(@WebParam(name="id") int id) {
        //Créer un user
        return 13;
    }
}