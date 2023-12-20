package dto;

public class MemberDto {
	private int num;
	private String name;
	private String addr;

	public MemberDto() {
		super();
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

	public String getName() {
		return name;
	}

	public String getAddr() {
		return addr;
	}

}
