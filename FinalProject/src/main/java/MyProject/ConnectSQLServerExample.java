package MyProject;

import java.sql.*;

public class ConnectSQLServerExample {
	public static void main(String[] args) {
		/*
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ProjectDB;user=sa;password=sa";

		try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
			String SQL = "SELECT TOP 10 * FROM ACCOUNT";
			ResultSet rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				System.out.println(rs.getString("Username") + " " + rs.getString("Pssword"));
			}
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		*/
		
		/*
		modifyHistory mod = new modifyHistory();
		History his = mod.getType("luu lich su");
		his.execute("acc1", 1, 111, "tat ca");
		his.execute("acc1", 1, 113, "tat ca");
		History his2 = mod.getType("xoa lich su");
		his2.execute("acc1", 1, 111, "tat ca");
		*/
		/*
		Account acc = new Account("tuanloc1105", "Tuanloc123@");
		
		LogInOut in = new LogIn(acc);
		LogInOut out = new LogOut(acc);
		
		Invoker ivk = new Invoker();
		System.out.print(ivk.active(out));
		*/
		
		doValidate val = new doValidate(new UsernameValidation());
		System.out.println(val.execute("tuanloc1105"));
		System.out.println(val.execute("quocduy"));
		
		val = new doValidate(new PasswordValidation());
		System.out.println(val.execute("Tuanloc123@"));
		System.out.println(val.execute("Quocduy321@"));
		
		
	}
	
	
}
