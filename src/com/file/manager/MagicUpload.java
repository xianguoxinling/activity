package com.file.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.file.manager.ImgCompress;
import com.platform.base.UserCookieManager;
import com.until.random.RandomString;
import com.until.replace.ReplaceSrvToHttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Servlet implementation class FileUpLoad
 */
@WebServlet("/MagicUpload")
public class MagicUpload extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	// 后期做单独的存储管理
	// private

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MagicUpload()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
	    HttpSession session = request.getSession(); 
        String keyID = (String)session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
        }
	    if(null != keyID)
	    {
	        uploadProductionPic(request,response,keyID);
	    }
	}
	
	public int compress(String filePath,String filename)
	{
		//压缩文件路径
		
		String compressMidPath = filePath + "/midcompress";
		File compressMidFolder = new File(compressMidPath);
		if (!compressMidFolder.exists())
		{
			compressMidFolder.mkdirs();
		}
		
		//压缩
		String oldName = filePath + "/" +filename;
		String newMidName = compressMidPath + "/" +filename;
		try
		{
			ImgCompress mid = new ImgCompress(oldName,newMidName);
			mid.resizeFix(720, 720);
		} catch (IOException e)
		{
			return 1;
		} catch (Throwable throwable)
		{
			return 1;
		}
		return 0;

	}
	
	public void uploadProductionPic(HttpServletRequest request, HttpServletResponse response,String uuid)
            throws ServletException, IOException
	{
	    
        OutputStream stream = response.getOutputStream();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errno", "0");
        List<String> data = new ArrayList<String>();
        
	       String filePath = "/srv/www/htdocs/ty/rich/activity/images/";
	        request.setCharacterEncoding("utf-8"); // 设置编码
	        HttpSession session = request.getSession(); 
	        String token = (String)session.getAttribute("token");
	        if (null == token)
	        {
	            token =  UserCookieManager.getCookieValueByName(request, "token");
	            if (null == token)
	            {
	                return ;
	            }
	        }
	        
	        String keyID = (String)session.getAttribute("keyID");
	        if (null == keyID)
	        {
	            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
	            if(null == keyID)
	            {
	                return;
	            }
	        }
	        
	        List<String> fileNameList = new ArrayList<String>();
	        
	        filePath = filePath + keyID + "/" + uuid;

	        // 获得磁盘文件条目工厂
	        DiskFileItemFactory factory = new DiskFileItemFactory();

	        File folder = new File(filePath);
	        if (!folder.exists())
	        {
	            folder.mkdirs();
	        }

	        factory.setRepository(new File(filePath));
	        // 设置 缓存的大小
	        factory.setSizeThreshold(1024 * 1024);

	        // 高水平的API文件上传处理
	        ServletFileUpload upload = new ServletFileUpload(factory);

	        try
	        {   
	            // 可以上传多个文件
	            List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
	            for (FileItem item : list)
	            {

	                // 获取表单的属性名
	                String name = item.getFieldName();

	                if (item.isFormField())
	                {
	                    String value = item.getString();
	                    request.setAttribute(name, value);
	                } else
	                {
	                    String value = item.getName();
	                    int start = value.lastIndexOf("\\");
	                    String filename = value.substring(start + 1);
	                    request.setAttribute(name, filename);
	                    
	                    String fileNameStr[] = filename.split("\\.");
	                    String houzhui = fileNameStr[fileNameStr.length-1];
	                    filename = RandomString.getRandomString(5);
	                    filename += ".";
	                    filename += houzhui;
	                    
	                    OutputStream out = new FileOutputStream(new File(filePath, filename));
	                    String picFile = filePath +"/";
	                    picFile += filename;
	                    fileNameList.add(picFile);
	                    InputStream in = item.getInputStream();
	                    
	                    int length = 0;
	                    byte[] buf = new byte[1024];

	                    while ((length = in.read(buf)) != -1)
	                    {

	                        out.write(buf, 0, length);

	                    }

	                    in.close();
	                    out.close();
	                    
	                    compress(filePath,filename);
	                    
	                    String compressMidPath = filePath + "/midcompress/";
	                    String zzName = compressMidPath + filename;
	                    zzName = ReplaceSrvToHttp.replace(zzName);
	                    data.add(zzName);
	                }

	            }
	            JSONObject json = null;
	            map.put("data", data);
	            json = JSONObject.fromObject(map);
	            
	            stream.write(json.toString().getBytes("UTF-8"));

	        } catch (FileUploadException e)
	        {
	        } catch (Exception e1)
	        {
	        } catch (Throwable throwable)
	        {
	        }
	}
	
}
