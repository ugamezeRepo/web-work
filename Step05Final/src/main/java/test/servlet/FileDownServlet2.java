package test.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.file.dao.FileDao;
import test.file.dto.FileDto;

// 파일을 다운로드해주는 서블릿
@WebServlet("/file/download")
public class FileDownServlet2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 다운로드 작업에 필요한 3가지 정보 (원본 파일명, 저장된 파일명, 파일크기) 얻어오기
		// 지금은 파라미터로 전달되지만, 실제로는 DB에 저장된 정보를 읽어와서 다운로드해야한다.
		int num = Integer.parseInt(req.getParameter("num"));
		FileDto dto = FileDao.getInstance().getData(num); 
		System.out.printf("num: %d%nwriter: %s%ntitle: %s%nofn: %s%nsfn: %s%nsize: %s%nregdate: %s%n" 
				, num, dto.getWriter(), dto.getTitle(), dto.getOrgFileName(), dto.getSaveFileName(), dto.getFileSize(), dto.getRegdate());
		
		// 응답 헤더 정보 설정
		resp.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
		// 다운로드 시켜줄 파일명 인코딩
		String encodedName = URLEncoder.encode(dto.getOrgFileName(), "utf-8");
		// 파일명에 공백이 있는 경우 처리
		encodedName = encodedName.replaceAll("\\+", " ");
		
		resp.setHeader("Content-Disposition", "attachment; filename=" + encodedName);
		resp.setHeader("Content-Transfer-Encoding", "binary");
		
		// 다운로드할 파일 크기
		resp.setContentLengthLong(dto.getFileSize());
		
		// 다운로드 시켜줄 파일 실제 경로
		String path = req.getServletContext().getRealPath("/upload")+File.separator+dto.getSaveFileName();
		
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		try {
			// 파일에서 byte를 읽어들일 객체
			fis = new FileInputStream(path);
			// 클라이언트에게 출력할 수 있는 스트림 객체 얻어오기
			bos = new BufferedOutputStream(resp.getOutputStream());
			// 한 번에 최대 1Mbyte씩 읽어올 수 있는 버퍼
			byte[] buffer = new byte[1024*1024];
			int readedByte=0;
			// 반복문 돌려 출력
			while(true) {
				// byte[] 객체를 이용해 파일에서 byte 알갱이 읽어오기
				readedByte = fis.read(buffer);
				if (readedByte == -1) break; // 더이상 읽을 데이터가 없다면 반복문 빠져나오기
				// 읽은만큼 출력
				bos.write(buffer, 0, readedByte);
				bos.flush(); // 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) bos.close();
			// FileInputStream 닫아주기
			if (fis != null) fis.close();
		}
		
	}
}
