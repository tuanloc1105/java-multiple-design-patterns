package MyProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import MyGUI.*;

public class showHistory implements History{
	
	@Override
	public void execute(String username, int starVertex, int endVertex, String type_) {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjectDB;user=sa;password=sa";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			String select = "SELECT * FROM HISTORY WHERE Username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(select);

			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
//			myFirstGUI lichsu;
			while (rs.next()) {
				myFirstGUI.setXemLichSu(rs.getString("Username") + " " + rs.getString("Star") + " " + rs.getString("Desm") + " " + rs.getString("Type_"));
//				System.out.println();
			}

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
