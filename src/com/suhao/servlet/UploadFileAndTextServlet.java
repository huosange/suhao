package com.suhao.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 上传文本信息和图片
 * @author suhao
 *
 */
@WebServlet("/uploadFileAndText")
@MultipartConfig
public class UploadFileAndTextServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//获取文本信息
		Part part=req.getPart("param");
		BufferedReader reader=new BufferedReader(new InputStreamReader(part.getInputStream(),"utf-8"));
		StringBuilder sb=new StringBuilder();
		String line=null;
		while((line=reader.readLine())!=null){
			sb.append(line);
		}
		resp.getWriter().write("我收到了：\n"+sb.toString());
		//获取文件
		Part part2=req.getPart("file");
		String str=part2.getHeader("Content-Disposition");
		String filename=getFilename(str);
		part2.write("E:/upload_images/"+filename);
		resp.getWriter().write("\nfile upload success!");
	}
	
	private String getFilename(String disposition){
		String[] ss=disposition.split(";");
		String s1=ss[ss.length-1].trim();
		String s2=s1.substring(s1.indexOf("\"")+1);
		String filename=s2.replace("\"", "");
		return filename;
	}
}
