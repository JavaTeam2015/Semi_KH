package server;

import java.util.ArrayList;

import jdbc_p.model.StudDao;
import jdbc_p.model.StudDto;

public class StudDataMain {

	public static void main(String[] args) {
		// DataMain���� �����ڸ� ���� ���⼭ ���� �� ���� , �߰��� �Ѵ�

		new User_info().insertList();
		ArrayList<UserDao> list = new UserDao().list();
		
		for (UserDto dto : list) {
			System.out.println(dto);
		}
		
	/*	StudDto dto = new StudDto();
		dto.setId(11);
		dto.setNnn("������");
		dto.setEmail("eee@eee.com");
		dto.setBbbStr("1988-08-08");
		new StudDao().insert(dto);
	*/
	}

}
