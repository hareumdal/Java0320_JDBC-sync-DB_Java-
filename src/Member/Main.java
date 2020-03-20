package Member;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// 2020-03-20
		// DB연동
		
		// commit!!!!! 시켜주자
		
		// DAO 객체 생성
		MemberDAO myDao = new MemberDAO();

		// 1. 데이터 삽입
		MemberDTO a = new MemberDTO();
//		a.setId("aaa");
//		a.setName("aname");
//		int k = myDao.insertOne(a);
//		if(k==1) {
//			System.out.println("성공");
//		}else if(k==0) {
//			System.out.println("실패");
//		}
//		// 2. 데이터 삽입
//		a = new MemberDTO();
//		a.setId("bbb");
//		a.setName("bname");
//		myDao.insertOne(a);

		// 2-1. 갱신
		// 시나리오는 : aaa 라는 사람의 이름을 kim 변경
		// update mmembers set name='kim' where id='aaa';
//		a.setId("aaa");
//		a.setName("kim");
//		myDao.updateOne(a);

		// 2-1. 삭제
		// 시나리오는 : aaa라는 사람이 탈퇴함. 관련 튜플 삭제
		// 방법1
//		a.setId("aaa");
//		myDao.deleteOne(a);
		// 방법2
		// myDao.delOne("bbb");

		// 2-2. aaa라는 사람의 정보만 가져오기
//		MemberDTO nowUser = myDao.selOne("aaa");
//		if (nowUser != null) {
//			System.out.println("id : " + nowUser.getId());
//			System.out.println("id : " + nowUser.getName());
//			System.out.println("id : " + nowUser.getPoint());
//		} else {
//			System.out.println("등록된 회원이 아닙니다.");
//		}
		
		// 3. 데이터 모두 가져오기
		ArrayList<MemberDTO> userList = myDao.selAll();
		myDao.attPrt();
		for(MemberDTO user: userList) {
			user.prt(user);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
