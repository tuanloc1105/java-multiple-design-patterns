package MyProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class removeHistory implements History {
	public void execute(String username, int starVertex, int endVertex, String type_) {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjectDB;user=sa;password=sa";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			String insert = "DELETE FROM HISTORY WHERE Username = ? AND Star = ? AND Desm = ?";
			PreparedStatement preparedStatement = con.prepareStatement(insert);

			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, starVertex);
			preparedStatement.setInt(3, endVertex);
			preparedStatement.executeUpdate();

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
