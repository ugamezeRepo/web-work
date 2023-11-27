package test.music.dto;

import test.music.validator.DateValidator;

public class MusicDto {
	private int num;
	private String name;
	private String artist;
	private String rdate;
	
	public MusicDto() {}
	
	public MusicDto(String name, String artist, String rdate) {
		super();
		this.name = name;
		this.artist = artist;
		this.rdate = rdate;
	}
	
	public MusicDto(int num, String name, String artist, String rdate) {
		super();
		this.num = num;
		this.name = name;
		this.artist = artist;
		this.rdate = rdate;
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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		boolean isValid = DateValidator.validate(rdate);
		
		if (isValid) {
			this.rdate = rdate;
		} else {
			throw new IllegalArgumentException("유효한 날짜 형식이 아닙니다.");
		}	
	}	
}
