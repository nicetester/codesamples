package cc.simpletest.qa.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class BaseClass {
	
//	@RequestMapping(value = { "/submit" }, method = { RequestMethod.POST })
//    public String submit(HttpServletRequest req, @RequestParam MultipartFile image) throws IOException {
//        String realPath = req.getSession().getServletContext().getRealPath("/upload");
//        FileUtils.copyInputStreamToFile(image.getInputStream(), new File(realPath, image.getOriginalFilename()));
//        return "/upload/submit";
//    }

}
