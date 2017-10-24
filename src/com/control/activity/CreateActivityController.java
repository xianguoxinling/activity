package com.control.activity;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.activity.Activity;
import com.platform.base.UserCookieManager;
import com.service.activity.ActivityService;
import com.service.interfaces.ActivityServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CreateActivityController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String filePath = "/srv/www/htdocs/ty";
        HttpSession session = request.getSession(); 
        String token = (String)session.getAttribute("token");
        if (null == token)
        {
            token =  UserCookieManager.getCookieValueByName(request, "token");
            if (null == token)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }
        
        String keyID = (String)session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
            if(null == keyID)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }

        String name = request.getParameter("name");
        if (null == name || "".equals(name))
        {
            return new ModelAndView("/store/error.html");
        }
        
        String briefIntroduction = request.getParameter("brief");
        String detail = request.getParameter("detail");
        String begin_time = request.getParameter("begin_time");
        String end_time = request.getParameter("end_time");
        
        Activity activity = new Activity();
        activity.setName(name);
        activity.setBegin_time(begin_time);
        activity.setEnd_time(end_time);
        activity.setDetailedIntroduction(detail);
        activity.setBriefIntroduction(briefIntroduction);
        activity.setMagicKey(keyID);
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        if(null != multipartFile)
        {
            filePath += "/activity/";
            filePath += keyID;
            filePath += "/";
            filePath += UUID.randomUUID().toString();
            filePath += "/";

            File folder = new File(filePath);
            if (!folder.exists())
            {
                folder.mkdirs();
            }
            String fileName =  multipartFile.getOriginalFilename();
            if(null != fileName && (!"".equals(fileName)))
            {
                String attacheFile = filePath + fileName;
                File source = new File(attacheFile);
                multipartFile.transferTo(source);
                activity.setMain_pic(attacheFile);
            }

        }
        ActivityServiceInterface service = new ActivityService();
        int result = service.createActivity(token, activity);
        if(MAGICCODE.OK != result)
        {
            return new ModelAndView("/store/error.html");
        }
        
//        List<Activity> activityList = service.queryStoreActivity(token, keyID);
//        if(null == activityList)
//        {
//            return new ModelAndView("/store/error.html");
//        }
//        else
//        {
//            Iterator<Activity> it = activityList.iterator();
//            while(it.hasNext())
//            {
//                Activity activity2= it.next();
//                if(null != activity2)
//                {
//                    activity2.setMain_pic(ReplaceSrvToHttp.replace(activity2.getMain_pic()));
//                }
//            }
//        }
        return new ModelAndView("/general/querystoreactivity.action");
    }

}