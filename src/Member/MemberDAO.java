package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO { // data access object
	private Connection conn;
	private Statement stmt;
	private ResultSet rs; // rs 자체는 테이블 전부를 가리키고, 튜플 하나(속성이 여러개..)를 통째로 가리키고 있다가-그걸 가져 왔으면 다음 튜플 하나를 통째로 가리킴

	private String sql = "";

	private ArrayList<MemberDTO> memberDTOList = new ArrayList<>();
	
	MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
		}
	}

	public ArrayList<MemberDTO> getMemberList(){
		return memberDTOList;
	}
	
	
	
	private boolean connect() { // oracle DB에 접속하기 위한 자원
		boolean cFlag = false;
		try {
			conn = DriverManager.getConnection("" + "jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			cFlag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cFlag;
	}

	public void delOne(String id) {
		if (connect()) {
			try {
				stmt = conn.createStatement();
				sql = "delete from mmember where id='" + id + "'";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 접속 오류!! ");
		}
	}

	public void deleteOne(MemberDTO m) { // 객체로 받아도 되고 String으로 받아도 상관 ㄴㄴ
		if (connect()) {
			try {
				sql = "delete from mmember where id=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());

				psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 접속 오류!! ");
		}
	}

	public void updateOne(MemberDTO m) {
		if (connect()) {
			try {
				sql = "update mmember set name=? where id=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getName());
				psmt.setString(2, m.getId());
				psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 접속 오류!! ");
		}
	}

	public int insertOne(MemberDTO m) {
		// Oracle에 m변수가 가리키는 객제를 저장
		// 절차 : db에 접속 > sql query를 만들어야 한다 > query 실행
		if (connect()) {
			try {
				// query 생성
				sql = "insert into mmember values(mmember_no.nextval,?,?,5)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId()); // 첫번
				psmt.setString(2, m.getName());
				// query 실행 => executeUpdate();
				int k = psmt.executeUpdate(); // 성공한 만큼 int 반환
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 접속 오류!! ");
		}
		return 0;
	}

	public MemberDTO selOne(String id) {
		MemberDTO searchDTO = null;
		if (connect()) {
			try {
				stmt = conn.createStatement();
				sql = "select * from mmember where id='" + id + "'";
				rs = stmt.executeQuery(sql); // 객체를 받아와서
				if (rs.next()) { // 가져올 다음 튜플이 있다면 //StringTokenizer의 hasmoretoken().. 이런 거 // 가져올 데이터가 많으면 while
					searchDTO = new MemberDTO();
					searchDTO.setId(rs.getString("id"));
					searchDTO.setName(rs.getString("name")); // 필드명을 써줌
					searchDTO.setPoint(rs.getFloat("point"));
					return searchDTO;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 접속 오류!! ");
		}
		return searchDTO;
	}
	
	public MemberDTO selAll() {
		MemberDTO searchDTO = null;
		if (connect()) {
			try {
			//	attPrt();
				stmt = conn.createStatement();
				sql = "select * from mmember";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					searchDTO = new MemberDTO();
					searchDTO.setNo(rs.getInt("no"));
					searchDTO.setId(rs.getString("id"));
					searchDTO.setName(rs.getString("name")); // 필드명을 써줌
					searchDTO.setPoint(rs.getFloat("point"));
					memberDTOList.add(searchDTO);
				
					//searchDTO.prt(searchDTO);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return searchDTO;
	}
	public void attPrt() {
		System.out.println("==============================");
		System.out.print("NO");
		System.out.print("	ID");
		System.out.print("	NAME");
		System.out.println("	POINT");
		System.out.println("==============================");
		
	}



}
