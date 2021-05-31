package gist.oop.hw.hw04;

//import Collection classes
import java.util.*;
//import FIle I/O classes
import java.io.*;

// 한 노래 정보 저장하는 Song 클래스
class Song extends MusicDB implements Comparable<Song> {
	String title; // 노래 제목
	String artist; // 가수 이름
	String album; // 앨범 이름
	String track; // 트랙 번호
	
	// Comparable 클래스 메소드를 구현
	public int compareTo(Song song) {
		return getTitle().compareTo(song.getTitle()); // 제목을 가나다 순으로 정렬
	}
	
	// 생성자
	Song(String t, String a, String a1, String tr) {
		title = t;
		artist = a;
		album = a1;
		track = tr;
	}
	public String getTitle() {
		return title;
	}
	public String getArtist() {
		return artist;
	}
	public String getAlbum() {
		return album;
	}
	public String getTrack() {
		return track;
	}
	
	// Song 클래스 정보를 문자열로 리턴하는 메소드
	@Override
	public String toString() {
    String str = this.title + ":" + this.artist + ":" + this.album + ":" + this.track +"\n"; // 출력 형태
    return str; // 이걸 리턴하겠다
	} 	
}

public class MusicDB {
	// Song 클래스를 인자로 하는 컬렉션 ArrayList 생성
	List<Song> list = new ArrayList<Song>();
	
	public static void main(String[] args) {
		new MusicDB().start(); // MusicDB 한 객체를 생성하여 start() 메소드 수행 
	}
	public void start() {
		// ArrayList의 요소를 파일로부터 읽어옴
		getSongs();
		// ArrayList의 요소를 노래 제목 순으로 정렬하도록 Collections 클래스 메소드 호출 
		Collections.sort(list);
		// 정렬된 ArrayList의 요소를 새 파일에 저장함
		putSongs();
	}
	void getSongs() {
		// 읽을 원본 파일
		File inFile = new File("/Users/leejia/Desktop/2021_GIST_OOP/src/gist/oop/hw/hw04/MusicSrcList.txt");
		try {
			// 파일 입력 문자 스트림에 연결하고, 다시 버퍼 출력 스트림에 연결
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// 한 줄씩 읽어서 Song 클래스에 저장하는 메소드 호출
				addSong(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void putSongs() {
		// 저장할 파일
		File outFile = new File("/Users/leejia/Desktop/2021_GIST_OOP/src/gist/oop/hw/hw04/MusicDstList.txt");
		try {
			// 파일 출력 문자 스트림에 연결하고, 다시 버퍼 출력 스트림에 연결
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			// ArrayList의 요소를 순서대로 접근하기 위해 Iterator 객체생성
			Iterator<Song> it = list.iterator();
			while (it.hasNext()) {
				// ArrayList의 한 요소를 파일에 저장
				writer.write(it.next().toString()); // 대박 그니까 파일에 저장할 걸 앞의 toString을 이용해서 적는다는 의미
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 문자열을 ":"로 구분하여 Song 클래스 객체에 저장후 컬렉션에 삽입하는 메소드
	void addSong(String lineToParse) {
		// 문자열을 ":"로 구분하여 분리
		String [] tokens = lineToParse.split(":");
		// Song 클래스 객체를 생성하여 문자열을 저장
		Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]); // constructor 실행!! 이 순서대로 타이틀 하고 다 지정해주는 거!
		// Song 클래스를 컬렉션에 삽입
		list.add(nextSong); 
	}

}
