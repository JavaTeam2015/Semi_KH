package server;

import java.util.ArrayList;

import server.model.UserDao;
import server.model.UserDto;


public class User_info {

	public static void main(String[] args) {
		// DataMain���� �����ڸ� ���� ���⼭ ���� �� ���� , �߰��� �Ѵ�

		ArrayList<UserDto> list = new UserDao().list();
		
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
