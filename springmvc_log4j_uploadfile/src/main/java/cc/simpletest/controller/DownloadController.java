package cc.simpletest.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/download" })
public class DownloadController {
	
	 @RequestMapping(value = { "/file" })
	   public void downloadMm2tl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        String fileName =req.getParameter("fileName");// "春江花月夜.txt";
	        
//	        String realPath = req.getSession().getServletContext().getRealPath("/download");
	        String realPath = req.getSession().getServletContext().getRealPath("/upload");
	        File file = new File(realPath, fileName);
	        String userAgent = req.getHeader("user-agent");
	        String encodeFileName = encodeFileName(fileName, userAgent);
	        resp.setHeader("Content-disposition", "attachment; filename=" + encodeFileName);
	        resp.setContentType("application/octet-stream");
	        FileUtils.copyFile(file, resp.getOutputStream());
	    }
	
    @RequestMapping(value = { "/txt" })
    public void txt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = "春江花月夜.txt";
        String realPath = req.getSession().getServletContext().getRealPath("/download");
        File file = new File(realPath, fileName);
        String userAgent = req.getHeader("user-agent");
        String encodeFileName = encodeFileName(fileName, userAgent);
        resp.setHeader("Content-disposition", "attachment; filename=" + encodeFileName);
        resp.setContentType("application/octet-stream");
        FileUtils.copyFile(file, resp.getOutputStream());
    }

    @RequestMapping(value = { "/csv" })
    public void csv(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = "月計.csv";
        String userAgent = req.getHeader("user-agent");
        String encodeFileName = encodeFileName(fileName, userAgent);
        resp.setHeader("Content-disposition", "attachment; filename=" + encodeFileName);
        resp.setContentType("application/octet-stream");
        OutputStreamWriter osw = new OutputStreamWriter(resp.getOutputStream(), "Shift-JIS");
        osw.write("UサD");
        osw.close();
    }
    
    private String encodeFileName(String fileName, String userAgent) throws UnsupportedEncodingException {
        if (userAgent.indexOf("MSIE") == -1) {
            //firefox, opera, chrome等
            return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        } else {
            //IE
            return URLEncoder.encode(fileName, "UTF-8");
        }
    }
}
