package br.usjt.web.projetointegrado.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
    static {
    	try {
            Class.forName("com.mysql.jdbc.Driver");
    	} catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
    	}
    }
	
    public static Connection conectar() throws SQLException {		
    	return DriverManager.getConnection("jdbc:mysql://localhost/projetointegrado",
    			"Alunos", "alunos");
    }
}

