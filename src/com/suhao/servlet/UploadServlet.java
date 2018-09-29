package com.suhao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Part part=req.getPart("aFile");
		String str=part.getHeader("Content-Disposition");
		
		String[] ss=str.split(";");
		String s1=ss[ss.length-1].trim();
		String s2=s1.substring(s1.indexOf("\"")+1);
		String filename=s2.replace("\"", "");
		
		part.write("E:/upload_images/"+filename);
		resp.getWriter().write("upload success!"+filename);
	}
}
