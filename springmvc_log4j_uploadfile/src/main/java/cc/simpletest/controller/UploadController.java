package cc.simpletest.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cc.simpletest.mm2testlink.ParseMm2TestLink;
import cc.simpletest.tl2mm.ParseTestLink2Mm;

@Controller
@RequestMapping(value = { "/upload" })
public class UploadController extends BaseClass {
	
	 final  Logger logger  =  LoggerFactory.getLogger(UploadController. class );

	//////////////////////////////////// =======mm2tl======================
	@RequestMapping(value = { "/mm2tl" })
	public String mm2tl() {
		return "/upload/mm2tl";
	}

	@RequestMapping(value = { "/submitmm2tl" }, method = { RequestMethod.POST })
	public String submitmm2tl(HttpServletRequest req, @RequestParam MultipartFile image) throws IOException {
		String realPath = req.getSession().getServletContext().getRealPath("/upload");
		logger.info(realPath);
		String mmFileName=image.getOriginalFilename();
		FileUtils.copyInputStreamToFile(image.getInputStream(), new File(realPath, mmFileName));
		String testLinkFileName=parceFreemind2Testlink(realPath, mmFileName);
		String str="redirect:/download/file?fileName="+testLinkFileName;
		return str;
	}
	
	public String parceFreemind2Testlink(String mmFileFolderPath,String mmFileName){
    	String encoding="utf8";
    	String mmFilePath=mmFileFolderPath+"/"+mmFileName;
    	String testLinkFileName=ParseMm2TestLink.pareTcFromFreeMind(mmFilePath, encoding);
    	return testLinkFileName;
    }

	//////////////////////////////////// ======tl2mm======================
	@RequestMapping(value = { "/tl2mm" })
	public String tl2mm() {
		return "/upload/tl2mm";
	}

	@RequestMapping(value = { "/submittl2mm" }, method = { RequestMethod.POST })
	public String submittl2mm(HttpServletRequest req, @RequestParam MultipartFile image) throws IOException {
		
		String realPath = req.getSession().getServletContext().getRealPath("/upload");
		logger.info(realPath);
		String testlinkFileName=image.getOriginalFilename();
		FileUtils.copyInputStreamToFile(image.getInputStream(), new File(realPath, testlinkFileName));
		String freemindFileName=ParseTestLink2Mm.pareTestLink2mm(realPath, testlinkFileName);
		String str="redirect:/download/file?fileName="+freemindFileName;
		return str;
		
		
////		String realPath = req.getSession().getServletContext().getRealPath("/upload");
////		FileUtils.copyInputStreamToFile(image.getInputStream(), new File(realPath, image.getOriginalFilename()));
////		
////		String freemindFileName="";
////		
//		
//		////TODO
////		ParseTestLink2Mm.pareTestLink2mm(xmlFilePath, xmlFileName);
//		
//		
////		String str="redirect:/download/file?fileName="+freemindFileName;
////		return str;
	}

}
