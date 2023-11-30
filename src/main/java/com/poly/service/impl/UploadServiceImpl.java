package com.poly.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poly.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	ServletContext app;

	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("/assets/" + folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String str = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(str.hashCode()) + str.substring(str.lastIndexOf("."));
		try {
			File savedFile = new File(dir, name);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
