package cc.simpletest.qa.controller;
//package cc.simpletest.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.dom4j.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import cc.simpletest.mm2testlink.Freemind2TestlinkParser;
//import cc.simpletest.mm2testlink.ParseMm2TestLink;
//import cc.simpletest.mm2testlink.XmlTool;
//import cc.simpletest.model.FileBean;
//
///**
// * SpringMVC中的文件上传
// * @see 第一步：由于SpringMVC使用的是commons-fileupload实现，故将其组件引入项目中
// * @see       这里用到的是commons-fileupload-1.2.2.jar和commons-io-2.0.1.jar
// * @see 第二步：在####-servlet.xml中配置MultipartResolver处理器。可在此加入对上传文件的属性限制
// * @see 第三步：在Controller的方法中添加MultipartFile参数。该参数用于接收表单中file组件的内容
// * @see 第四步：编写前台表单。注意enctype="multipart/form-data"以及<input type="file" name="****"/>
// * @author 宏宇
// * @create May 12, 2012 1:26:21 AM
// */
//@Controller
//@RequestMapping("/tools")
//public class MM2TlController {
//	
//	 final  Logger logger  =  LoggerFactory.getLogger(MM2TlController. class );
//	 
////    private final static Map<String, FileBean> mm2tlList = new HashMap<String, FileBean>();
////    private final static Map<String, FileBean> tl2mmList = new HashMap<String, FileBean>();
//    private final static String mm2tlListFolderName="mm2tlList";
//    private final static String tl2mmListFolderName="tl2mmList";
//
//    //模拟数据源,构造初始数据
//    public MM2TlController() {
////    	mm2tlList.put("张起灵", new User("张起灵", "闷油瓶", "02200059", "menyouping@yeah.net"));
////    	mm2tlList.put("李寻欢", new User("李寻欢", "李探花", "08866659", "lixunhuan@gulong.cn"));
////    	mm2tlList.put("拓拔野", new User("拓拔野", "搜神记", "05577759", "tuobaye@manhuang.cc"));
////    	mm2tlList.put("孙悟空", new User("孙悟空", "美猴王", "03311159", "sunhouzi@xiyouji.zh"));
//    }
//    
//    @RequestMapping("/home")
//    public String home(Model model) {
////		model.addAttribute("mm2tlList", mm2tlList);
////		model.addAttribute("tl2mmList", tl2mmList);
//		return "tools/home";
//    }
//    
//    @RequestMapping(value = "/mm2tl", method = RequestMethod.GET)
//    public String mm2tl(Model model, HttpServletRequest request) {
//    	String currentFileName=request.getParameter("currentFileName");
//    	if(null==currentFileName){
//    		currentFileName="";
//    	}
//    	for (Map.Entry<String, FileBean> entry : mm2tlList.entrySet()) {  
//    		  String keyStr=entry.getKey();
//    	}  
//    	FileBean fileBean=mm2tlList.get(currentFileName);
//    	if(null!=fileBean){
//	    	Map<String, FileBean> mm2tlList2 = new HashMap<String, FileBean>();
//	    	mm2tlList2.put(currentFileName, fileBean);
//	    	model.addAttribute("mm2tlList", mm2tlList2);
//	    	if(!StringUtils.isBlank(currentFileName)){
//				model.addAttribute("currentFileName", currentFileName);
//			}
//    	}
//    	return "tools/mm2tl";
//    }
//    
//    
//    @RequestMapping(value = "/tl2mm", method = RequestMethod.GET)
//    public String tl2mm() {
//    	return "tools/tl2mm";
//    }
//
//    @RequestMapping("/list")
//    public String list(Model model) {
//	model.addAttribute("mm2tlList", mm2tlList);
//	return "user/list";
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String addUser() {
//	return "user/add";
//    }
//
//    /**
//     * 
//     * @param myfiles:上传的文件，目前jsp页面仅支持一个文件
//     * @param request
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping(value = "/mm2tl", method = RequestMethod.POST)
//    public String mm2tl(Model model,@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
//	//如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
//	//如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
//	//并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
//    String currentFileName="";
//    for (MultipartFile myfile : myfiles) {
//	    if (myfile.isEmpty()) {
//	    	System.out.println("文件未上传");
//	    } else {
//	    	FileBean fileBean=new FileBean();
//	    	String fileName=myfile.getOriginalFilename();
//	    	currentFileName=fileName+"_"+System.currentTimeMillis();
//	    	fileBean.setFileName(currentFileName);
//			System.out.println("文件长度: " + myfile.getSize());
//			System.out.println("文件类型: " + myfile.getContentType());
//			System.out.println("文件名称: " + myfile.getName());
//			System.out.println("文件原名: " + fileName);
//			System.out.println("========================================");
//			//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
//			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/"+mm2tlListFolderName);
//			System.out.println(realPath);
//			//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
//			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));//这一步是上传文件!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			
//			parceFreemind2Testlink(realPath, fileName);
//			
//			File uploadedFile=new File(realPath+"/"+myfile.getOriginalFilename());
//			 
//			String fileUrl="../../upload/"+mm2tlListFolderName;
//			fileBean.setFileUrl(fileUrl);
//			String getModifiedTime=Utils.getModifiedTime(uploadedFile);
//			fileBean.setLastModified(getModifiedTime);
//			mm2tlList.put(currentFileName,fileBean);
//			System.out.println(realPath);
//	    }
//	}
//    	return "redirect:/tools/mm2tl?currentFileName="+currentFileName;
//    }
//    
//    public void parceFreemind2Testlink(String mmFileFolderPath,String mmFileName){
//    	String encoding="utf8";
//    	String mmFilePath=mmFileFolderPath+"/"+mmFileName;
//    	ParseMm2TestLink.pareTcFromFreeMind(mmFilePath, encoding);
//    }
//    
//    
//    @RequestMapping(value = "/tl2mm", method = RequestMethod.POST)
//    public String tl2mm(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
//	//如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
//	//如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
//	//并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
//	for (MultipartFile myfile : myfiles) {
//	    if (myfile.isEmpty()) {
//		System.out.println("文件未上传");
//	    } else {
//		System.out.println("文件长度: " + myfile.getSize());
//		System.out.println("文件类型: " + myfile.getContentType());
//		System.out.println("文件名称: " + myfile.getName());
//		System.out.println("文件原名: " + myfile.getOriginalFilename());
//		System.out.println("========================================");
//		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
//		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
//		System.out.println(realPath);
//		//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
//		FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));//这一步是上传文件!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		File uploadedFile=new File(realPath+"/"+tl2mmListFolderName+"/"+myfile.getOriginalFilename());
//		System.out.println(uploadedFile.exists());
//		System.out.println(realPath);
//	    }
//	}
////	users.put(user.getUsername(), user);
//	return "redirect:/tools/tl2mm";
//    }
//}