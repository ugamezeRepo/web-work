package test.member.dto;

public class MemberDto {
	private int num;
	private String name, addr;
	
	public MemberDto() {}
	
	public MemberDto(String name, String addr) {
		super();
		this.name = name;
		this.addr = addr;
	}

	public MemberDto(int num, String name, String addr) {
		super();
		this.num = num;
		this.name = name;
		this.addr = addr;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
