package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao {

	Connection con = null; // ������ ���̽��� ������ ����
	Statement stmt = null; // sql ���࿡ �ʿ��� ����
	ResultSet rs = null;   // select ������ �����Ͽ��� �� ��� ���� ������ ����
	String sql = null;

	public UserDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // sql�� ������ �÷��׸� ����� �ش�.
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl",
					"hr","hr"
					);

			stmt = con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/////����Ʈ��������
	public ArrayList<UserDto> list()
	{
		ArrayList<UserDto> res = new ArrayList<>();

		try {
			sql ="select * from user_info";
			rs = stmt.executeQuery(sql);

			while(rs.next())
			{
				UserDto dto = new UserDto();

				dto.id = rs.getString("");
				dto.pw = rs.getString("");

				res.add(dto);
			}

		} catch (Exception e) {e.printStackTrace();} finally{close();}
		return res;
	}
	///����
	public void insert(UserDto dto)
	{
		try {
			sql = "insert into user_info (id,pw) "
					+" values ("+dto.id+",'"+dto.pw+"')";
			System.out.println(stmt.executeUpdate(sql));

		} catch (Exception e) {e.printStackTrace();} finally{close();}

	}

	public void close()
	{
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}

	}

}