package com.ktdsuniversity.edu.domain.file.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;


/**
 * Bean Container에 담긴 객체는 yml의 설정을 참조받을 수 있다, == yml의 설정을 읽어올 수 있다.
 */
@Component
public class MultipartFileHandler {

	private static final Logger logger = LoggerFactory.getLogger(MultipartFileHandler.class);
	
	// application.yml의 설정을 읽어오는 Annotation
	@Value("${app.multipart.whitelist}") // SpEL (스프링에서 쓰는 EL 문법이다)
	private List<String> whitelist;

	@Value("${app.multipart.base-dir.windows}")
	private String windowsBaseDirectory;

	@Value("${app.multipart.base-dir.macos}")
	private String macosBaseDirectory;

	@Value("${app.multipart.obfuscation.enable}")
	private boolean obfuscationEnable;

	@Value("${app.multipart.obfuscation.hide-ext.enable}")
	private boolean hideExtEnable;

	private Tika tika;

	public MultipartFileHandler() {
		this.tika = new Tika();
	}

	// 오버로딩
	public List<FileVO> upload(List<MultipartFile> file) {

		if (file == null) {
			return null;
		}

		List<FileVO> uploadResultList = new ArrayList<>();
		for (MultipartFile uploadFile : file) {
			FileVO uploadResult = this.upload(uploadFile);
			// 업로드를 하다가 예외 발생등이 생기면 null을 주기 때문에 null 체크를 한
			if (uploadResult != null) {
				uploadResultList.add(uploadResult);
			}
		}

		return uploadResultList;
	}

	public File makeTemporaryFile() {

		String os = System.getProperty("os.name"); // os

		String userHome = System.getProperty("user.home"); // 경로
		String directory = this.windowsBaseDirectory;
		if (os.toLowerCase().startsWith("mac")) {
			directory = userHome + this.macosBaseDirectory;
		}
		
		return new File(directory, UUID.randomUUID().toString());
	}

	public FileVO upload(MultipartFile file) { // 사용자가 우리에게 업로드 해준파일을 MultipartFile이라는 이름으로 가져
		// 업로드된 파일이 없으면 null을 반환.
		if (file == null || file.isEmpty()) {
			return null;
		}
		// 파일이 저장될 위치, 맥이냐 윈도우냐 분기처리
		/**
		 * Windows: Windows 10 Macos: Mac OS X
		 */
		String os = System.getProperty("os.name"); // os

		/**
		 * Windows : C:\\Users\\user Mac : /Users/user
		 */
		String userHome = System.getProperty("user.home"); // 경로

		String filename = file.getOriginalFilename();
		if (this.obfuscationEnable) {
			// 사용자가 업로드한 파일의 이름을 난독화.
			filename = this.makeObfuscationName(file.getOriginalFilename());
		}

		// 파일 저장
		// OS별 파일의 경로 지정
		String directory = this.windowsBaseDirectory;
		if (os.toLowerCase().startsWith("mac")) {
			directory = userHome + this.macosBaseDirectory;
		}
		// 업로드한 파일이 저장될 경로
		File storePath = new File(directory, filename);

		if (!storePath.getParentFile().exists()) {
			// 경로가 없으면 경로를 만들어라
			storePath.getParentFile().mkdirs();
		}

		// 업로드한 파일을 저장한다.
		// 에러원인 디스크가 부족한다,접근허용되지않은 경로에 세이브...
		try {
			file.transferTo(storePath);
		} catch (IllegalStateException | IOException e) {
			return null;
		}
		// 업로드한 결과를 반환
		FileVO uploadResult = new FileVO();
		uploadResult.setFlSz(storePath.length());
		uploadResult.setFlNm(file.getOriginalFilename());
		uploadResult.setObfsctinFlNm(storePath.getName());
		uploadResult.setFlPth(storePath.getAbsolutePath());
		// TODO MimeType 추출 후 세팅 해야 함
		// uploadResult.setFileType("파일의 MimeType을 작성");
		if (!this.availableStore(storePath, uploadResult, file.getOriginalFilename())) {
			// 그파일이 우리가 원하는 파일이 아니면 저장한 파일을 지우고 반환값을 null로하겠다
			storePath.delete();

			return null;
		}

		return uploadResult;
	}

	// 난독화한 이름 만들어주기
	private String makeObfuscationName(String filename) {

		// 확장자 분리
		String ext = filename.substring(filename.lastIndexOf(".")); // .jpg
		// 파일명 난독화 위해 난수 생성
		String newFilename = UUID.randomUUID().toString();
		// 확장자 분리 여부에 따라 이름에 확장자 조합 또는 폐기
		if (!this.hideExtEnable) {
			newFilename += ext;
		}
		// 완성된 파일의 이름을 반환
		return newFilename;
	}

	// 업로드 가능한지 검사하겠다.
	private boolean availableStore(File file, FileVO uploadResult, String displayFilename) {
		try {
			String mimeType = this.tika.detect(file);
			uploadResult.setFlTyp(mimeType);

			// 애매하면 text/plain으로 주니까 이때 체크하자
			if (mimeType.equals("text/plain")) {
				mimeType = displayFilename.substring(displayFilename.lastIndexOf("."));
			}
			logger.info("FileName : {}, MimeType: {}", file.getName(), mimeType);

			return this.whitelist.contains(mimeType);
		} catch (IOException e) {
			return false;
		}

	}
}
