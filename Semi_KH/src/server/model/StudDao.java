package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudDao {

/*	
         ������ ������ �����ϴ� ��ü�̴�. 
         Ŀ�ؼ� ���� ���� �ϳ��� �ΰ� ���� ����ڰ� DAO�� �������̽��� ����Ͽ� �ʿ��� �ڷῡ ���� �ϵ��� �ϴ� ���� DAO�� �����̴�.
    DB�� ����� �����͸� ��ȸ�ϰų� �����ϴ� ����� �����ϵ��� ���� ������Ʈ�� ���Ѵ�.

	DAO�� ��� ������ ȿ������ Ŀ�ؼ� ������ ���ȼ� �����̴�.
	
	dao�� SQL�� �����ϱ� ���� ���� �� �̴�. �׸��� �۾��� Main���� ���ְ� �� �л����� ������ Dto�� ������ �ִ�.

*/	
	
	Connection con = null; // ������ ���̽��� ������ ����
	Statement stmt = null; // sql ���࿡ �ʿ��� ����
	ResultSet rs = null;   // select ������ �����Ͽ��� �� ��� ���� ������ ����
	String sql = null;
	
	public StudDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // sql�� ������ �÷��׸� ����� �ش�.
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl",
					"hr","hr"
					);
			
			stmt = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/////����Ʈ��������
	public ArrayList<StudDto> list()
	{
		ArrayList<StudDto> res = new ArrayList<>();

		try {

			sql ="select * from stud";
			rs = stmt.executeQuery(sql);
			
			//birth, tel, email, regdate, grade
			while(rs.next())
			{
				StudDto dto = new StudDto();
				dto.id = rs.getInt("id");
				dto.nnn = rs.getString("name");
				dto.bbb = rs.getDate("birth");
				dto.tel = rs.getString("tel");
				dto.email = rs.getString("email");
			//	dto.regdate = rs.getDate("regdate");
				dto.grade = rs.getInt("grade");
				
				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

		return res;
	}
	
	
	///����
	public void insert(StudDto dto)
	{
		try {

			sql = "insert into stud (id,birth, name, email) "
					+ "values ("+dto.id+
					",'"+dto.getBbbStr()+
					"','"+dto.nnn+
					"','"+dto.email+
					"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	
	
	public void insertList()
	{
		try {

			//sql = "insert all "
			    con.setAutoCommit(false);
				sql = "insert into stud (id, name, grade) values (71,'�׶���',1)";
				System.out.println(stmt.executeUpdate(sql));
				sql = "insert into stud (id, name, grade) values (72,'����ĸƾ���罺',2)";
				System.out.println(stmt.executeUpdate(sql));
				sql = "insert into stud (id, name, grade) values (73,'�Ǻ�','abcd')";
				System.out.println(stmt.executeUpdate(sql));
				//"select * from dual";
			
		        //	System.out.println(sql);
		    	// System.out.println(stmt.executeUpdate(sql));
		    	//stmt.executeUpdate(sql);
 
				
				con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			close();
		}
	}
	
	
	
	public void close()
	{
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		
	}
	
}
