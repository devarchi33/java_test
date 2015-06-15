package skyfly33.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import skyfly33.util.TestConfig;

public class CreateConnection {

	public static void main(String[] args) {
		try {
			Connection conn = null;

			String url = TestConfig.getInstance().getProperties("db.url");
			String id = TestConfig.getInstance().getProperties("db.id");
			String pass = TestConfig.getInstance().getProperties("db.pass");

			conn = DriverManager.getConnection(url, id, pass);

			Statement stmt = null;
			ResultSet rs = null;
			stmt = conn.createStatement();
			String query = "show databases";

			rs = stmt.executeQuery(query);

			if (stmt.execute(query)) {
				rs = stmt.getResultSet();
			}

			while (rs.next()) {
				String str = rs.getNString(1);
				System.out.println(str);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQLException : " + e.getMessage());
			System.out.println("SQLState : " + e.getSQLState());
		}
	}
}
