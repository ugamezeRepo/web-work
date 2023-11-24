package test.guest.dto;

public class GuestDto {
	private int num;
	private String writer;
	private String content;
	private String pwd;
	private String regdate;
	
	public GuestDto() {}
	
	public GuestDto(String writer, String content, String pwd) {
		super();
		this.writer = writer;
		this.content = content;
		this.pwd = pwd;
	}
	
	public GuestDto(int num, String writer, String content, String pwd) {
		super();
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.pwd = pwd;
	}
	
	public GuestDto(int num, String writer, String content, String pwd, String regdate) {
		super();
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.pwd = pwd;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
