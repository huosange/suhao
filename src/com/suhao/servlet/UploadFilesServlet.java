package com.suhao.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 处理多个文件的上传
 * @author suhao
 *
 */
@WebServlet("/uploadFiles")
@MultipartConfig
public class UploadFilesServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Collection<Part> col=req.getParts();
		Iterator<Part> iter=col.iterator();
		while(iter.hasNext()){
			Part part=iter.next();
			part.write("e:/upload_images/"+getFileName(part.getHeader("Content-Disposition")));
		}
		resp.getWriter().write("all files upload success!");
	}
	
	private String getFileName(String disposition){
		String[] ss=disposition.split(";");
		String s1=ss[ss.length-1].trim();
		String s2=s1.substring(s1.indexOf("\"")+1);
		String filename=s2.replace("\"", "");
		return filename;
	}
}
