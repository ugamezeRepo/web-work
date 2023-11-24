package test.music.dto;

public class MusicDto {
	private int num;
	private String name;
	private String artist;
	private String release;
	
	public MusicDto() {}
	
	public MusicDto(String name, String artist, String release) {
		super();
		this.name = name;
		this.artist = artist;
		this.release = release;
	}
	
	public MusicDto(int num, String name, String artist, String release) {
		super();
		this.num = num;
		this.name = name;
		this.artist = artist;
		this.release = release;
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

	public String getartist() {
		return artist;
	}

	public void setartist(String artist) {
		this.artist = artist;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}	
}
