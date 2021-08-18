package com.bms.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bms.goods.dto.ImageFileDTO;

import net.coobird.thumbnailator.Thumbnails;


@Controller
public class FileController {
	
	private static final String CURR_IMAGE_REPO_PATH = "C:\\file_repo";
	String seperatorPath = "\\";	// window

	//private static final String CURR_IMAGE_REPO_PATH = "/var/lib/tomcat8/file_repo";
	//String seperatorPath = "/";		// linux
	
	
	@RequestMapping("/download")
	public void download(@RequestParam("fileName") String fileName,
		                 	@RequestParam("goodsId") String goodsId,
			                 HttpServletResponse response) throws Exception {
		
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + seperatorPath + goodsId + seperatorPath + fileName;
		File image = new File(filePath);

		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		FileInputStream in = new FileInputStream(image); 
		byte[] buffer = new byte[1024*8];
		while (true){
			int count = in.read(buffer); 
			if (count==-1)  
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
		
	}
	
	
	@RequestMapping("/thumbnails.do")
	public void thumbnails(@RequestParam("fileName") String fileName,
                              @RequestParam("goodsId") String goodsId,
			                 HttpServletResponse response) throws Exception {
		
		
		OutputStream out = response.getOutputStream();

		String filePath = CURR_IMAGE_REPO_PATH + seperatorPath + goodsId + seperatorPath + fileName;
		
		File image = new File(filePath);

		if (image.exists()) { 
			Thumbnails.of(image).size(121,154).outputFormat("png").toOutputStream(out);
		}
		
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
		
		
	}
	
	
	
	
	public List<ImageFileDTO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		
		List<ImageFileDTO> fileList= new ArrayList<ImageFileDTO>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while (fileNames.hasNext()) {
			
			ImageFileDTO imageFileDTO = new ImageFileDTO();				// 빈 파일 dto생성
			
			String fileName = fileNames.next();							//
			imageFileDTO.setFileType(fileName);
			
			MultipartFile mFile = multipartRequest.getFile(fileName); 	// 멀티파트의 객체
			
			String originalFileName = mFile.getOriginalFilename();
			imageFileDTO.setFileName(originalFileName);
			
			fileList.add(imageFileDTO);
			
			File file = new File(CURR_IMAGE_REPO_PATH + "/" + fileName);	// 새로 생성한 파일
			if (mFile.getSize() != 0) { 									// File Null Check
				if (!file.exists()) {										// 새로생성한 파일이 존재하지 않으면
					if (file.getParentFile().mkdirs()){ 					// 부모파일의 폴더를 만듬
						System.out.println("========getParent : " + file.getParentFile());
						
						file.createNewFile(); 								// 빈파일을 만든다.
					}
				}
				// 멀티파트의 파일을 저장경로에 옮긴다. c:file_repo\temp\원본파일
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH  + seperatorPath + "temp" + seperatorPath + originalFileName)); 
			}
		}
		return fileList;		// 파일리스트를 반환
		
	}
	
	
	public void deleteFile(String fileName) {
		File file = new File(CURR_IMAGE_REPO_PATH + seperatorPath + fileName);
		try{
			file.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
