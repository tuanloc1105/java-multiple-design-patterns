package MyProject;

import java.sql.*;

public class Account {
	private String username;
	private String password;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean login(String username, String password) {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjectDB;user=sa;password=sa";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			String select = "SELECT Username, Pssword FROM ACCOUNT WHERE Username = ? AND Pssword = ?";
			PreparedStatement preparedStatement = con.prepareStatement(select);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			String user = null, pass = null;
			while (rs.next()) {
				//System.out.println(rs.getString("Username") + " " + rs.getString("Pssword"));
				user = rs.getString("Username");
				pass = rs.getString("Pssword");
			}
			
			if (username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)) {
				String update = "UPDATE ACCOUNT SET Stt = 'on' WHERE Username = ?";
				PreparedStatement preparedStatement2 = con.prepareStatement(update);
				
				preparedStatement2.setString(1, username);
				preparedStatement2.executeUpdate();
				return true;
				
			}
			

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean logout() {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjectDB;user=sa;password=sa";
		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			String update = "UPDATE ACCOUNT SET Stt = 'off' WHERE Username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(update);
			
			preparedStatement.setString(1, username);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

}
