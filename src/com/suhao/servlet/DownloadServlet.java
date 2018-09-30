package com.suhao.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		File file=new File("e:/net_videos/wangyiyun.mp4");
		FileInputStream fis=new FileInputStream(file);
		byte[] b=new byte[fis.available()];
		fis.read(b);
		ServletOutputStream out=resp.getOutputStream();
		out.write(b);
		out.flush();
		out.close();
		fis.close();
	}
}
