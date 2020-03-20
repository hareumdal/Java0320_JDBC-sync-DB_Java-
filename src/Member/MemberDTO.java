package Member;

public class MemberDTO { // data transfer object
	// 박스, 포장
	private int no;
	private String id;
	private String name;
	private float point;

	
	
	
	public void prt(MemberDTO nowUser) {
		//for(MemberDTO searchDTO: memberDTOList) {
		if(nowUser != null) {
			System.out.print(nowUser.getNo()+"\t");
			System.out.print(nowUser.getId()+"\t");
			System.out.print(nowUser.getName()+"\t");
			System.out.print(nowUser.getPoint()+"\t");
			System.out.println();
		} else {
			System.out.println("등록된 회원이 아닙니다.");
		}
		//}
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

}
