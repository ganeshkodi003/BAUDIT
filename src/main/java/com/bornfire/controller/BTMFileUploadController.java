package com.bornfire.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.services.PlacementServices;
@RestController

@RequestMapping("excel")
public class BTMFileUploadController {
	
	@Autowired
	PlacementServices placementServices;
	
	/*
	 * private static String UPLOAD_FOLDER = "C://test//";
	 * 
	 * 
	 * public ModelAndView showUpload() { return new ModelAndView("upload"); }
	 */

	/*
	 * @PostMapping("/upload") public ModelAndView fileUpload(@RequestParam("file")
	 * MultipartFile file, RedirectAttributes redirectAttributes) {
	 * 
	 * if (file.isEmpty()) { return new ModelAndView("status", "message",
	 * "Please select a file and try again"); }
	 * 
	 * try { // read and write the file to the selected location- byte[] bytes =
	 * file.getBytes(); Path path = Paths.get(UPLOAD_FOLDER +
	 * file.getOriginalFilename()); Files.write(path, bytes);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * return new ModelAndView("status", "message", "File Uploaded sucessfully"); }
	 */
	
	
	/*
	 * @PostMapping public PlacementMaintenance uploadFile(@RequestParam("file")
	 * MultipartFile file) throws IOException {
	 * 
	 * return placementServices.store(file);
	 * 
	 * }
	 * 
	 * @GetMapping("/{id}")
	 * 
	 * public PlacementMaintenance getFile(@PathVariable String id) { return
	 * placementServices.getFileById(id);
	 * 
	 * }
	 * 
	 * @GetMapping("/list") public List<PlacementMaintenance> getFileList() { return
	 * placementServices.getFileList();
	 * 
	 * }
	 */
	
	
	
	
	
	  @PostMapping("/upload")
	  public String uploadFile(@RequestParam("file") MultipartFile file) {
	      
	    String msg = "";
	    if (ExcelHelper.hasExcelFormat(file)) {
	      try {
	    	  placementServices.save(file);
	
	        return msg;
	      } catch (Exception e) {
	   
	        return msg;
	      }
	    }
	    msg = "Please upload an excel file!";
	    return msg;
	  }
	
	

}
