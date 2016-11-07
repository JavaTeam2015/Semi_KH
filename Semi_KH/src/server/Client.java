package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.model.UserDto;

import GUI.IdFindMain;
import GUI.PwFindMain;
import GUI.JoinMain;


//import server.ClientMain.Receiver;

class IdfindButton implements ActionListener 
{
	int kind = 0;
	public IdfindButton(int num) {
		this.kind = num;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(kind==0)new IdFindMain();
		if(kind==1)new PwFindMain();
		if(kind==2)new JoinMain();
	}
}

public class Client extends JFrame {

	JTextField ip_F = new JTextField();// ip 입력창
	JTextField port_F = new JTextField();// 포트입력창

	JButton ip_chk = new JButton("접속"); // ip 확인버튼

	JTextArea txt_area= new JTextArea(); // 채팅창
	JScrollPane txt_scrol = new JScrollPane(txt_area);// 채팅창스크롤

	JTextField chat_area= new JTextField();//채팅입력창
	JButton chat_chk = new JButton("전송"); // 전송버튼

	Socket socket;

	String ip;
	int port;

	UserDto dto = new UserDto();

	JLabel id = new JLabel("I  D:");
	JLabel pw = new JLabel("PW:");
	JTextField idtf = new JTextField();
	JPasswordField pwtf = new JPasswordField();
	JButton chk = new JButton("로그인");
	JButton idfind = new JButton("아이디찾기");
	JButton pwfind = new JButton("비밀번호찾기");
	JButton join = new JButton("회원가입");
	public Client() 
	{
		setTitle("세영이뿌네");
		setBounds(10,20,920,690);
		setLayout(null);
		id.setBounds(300, 400, 70, 30);
		id.setFont(new Font("Serif", Font.BOLD, 25));
		add(id);
		pw.setBounds(300, 450, 70, 30);
		pw.setFont(new Font("Serif", Font.BOLD, 25));
		add(pw);
		idtf.setBounds(360, 400, 240, 30);
		add(idtf);
		pwtf.setBounds(360, 450, 240, 30);
		add(pwtf);
		chk.setBounds(500, 500, 100, 40);
		add(chk);
		chk.addActionListener(new Chk_Button());
		idfind.setBounds(300, 550, 100, 40);
		add(idfind);
		idfind.addActionListener(new IdfindButton(0));
		pwfind.setBounds(400, 550, 100, 40);
		pwfind.addActionListener(new IdfindButton(1));
		add(pwfind);
		join.setBounds(500, 550, 100, 40);
		join.addActionListener(new IdfindButton(2));
		add(join);

		//id.setText("admin");
		//	pw.setText("admin");


		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	class Chk_Button implements ActionListener {// 로그인 누를때
		@Override
		public void actionPerformed(ActionEvent e) {

			String id_chk ="admin";
			String pw_chk ="admin";
			/*ip = "127.0.0.1";//"192.168.219.124";
			port = 7777;
			socket(ip,port);*/

			// if(id.equals("admin")){
			if((idtf.getText().equals(id_chk))) {
				if((pwtf.getPassword().equals(pw_chk))) {
					System.out.println("로그인 성공");
				}
			}
			else
				System.out.println("로그인 실패");
			
			
//			else if(pw.equals("admin"))
//			{
//			dto.setPw(pw);
//			System.out.println("로그인 성공");
//			}
//
//			else{
//				System.out.println("로그인 실패");
//
//			}
		}
	}

	class TCPSender {
		DataOutputStream output;
		String name;
		String chat;
		public TCPSender(Socket socket, String str) {
			this.chat = str;
			try {
				output = new DataOutputStream(socket.getOutputStream());
				name = "["+socket.getLocalAddress()+"]";
				output.writeUTF(name+chat);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	class Receiver extends Thread
	{
		DataInputStream input;
		public Receiver(Socket socket) {
			try {
				input = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while(input!=null)
			{
				try {
					txt_area.append(input.readUTF()+"\n");
					txt_area.setCaretPosition(txt_area.getDocument().getLength());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	void socket(String ip, int port) {
		try {
			Socket socket = new Socket(ip,port);
			this.socket = socket;

			txt_area.append("서버 연결 성공\n");
			new Receiver(socket).start();
		} 

		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		new Client();
	}

}
