package com.bornfire.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entities.BTMDocumentMaster;

@Service
@Transactional
@ConfigurationProperties("output")
public class DocFileUploadServices {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	
	
	private static String UPLOAD_FOLDER = "C://test//";
	
	public String  MultipartToFile(BTMDocumentMaster btmDocumentMaster, MultipartFile file, String fileName,String formmode)
			throws IllegalStateException, IOException {
		Session hs = sessionFactory.getCurrentSession();
		String msg= "";
		if (formmode.equals("add")) {
		Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
		System.out.println(path);
		
		try (InputStream is = file.getInputStream(); OutputStream os = Files.newOutputStream(path)) {
			byte[] buffer = new byte[4096];
			BTMDocumentMaster up = btmDocumentMaster;
			hs.saveOrUpdate(up);
			
			is.read(buffer);
			
			
			int read = 0;
			while ((read = is.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
			
			
				
			}catch (Exception e) {
				return msg="Internal_server_error";
			}
		
		msg = "File Uploaded Successfully";
		}
		
		return msg;

}

}
