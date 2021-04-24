package MyProject;

import java.sql.*;

public class addHistory implements History {

	public void execute(String username, int starVertex, int endVertex, String type_) {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjectDB;user=sa;password=sa";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			String insert = "INSERT INTO HISTORY VALUES(?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.prepareStatement(insert);

			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, starVertex);
			preparedStatement.setInt(3, endVertex);
			preparedStatement.setString(4, type_);
			preparedStatement.executeUpdate();

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
