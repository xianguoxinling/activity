package com.control.activity;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.activity.Activity;
import com.platform.base.UserCookieManager;
import com.service.activity.ActivityService;
import com.service.interfaces.ActivityServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;
import com.until.replace.ReplaceSrvToHttp;

public class UpdateActivityDetailController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
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
        
        String id = request.getParameter("id");
        if(!UntilNum.isNumber(id))
        {
            return new ModelAndView("/store/error.html");
        }
        
        String detail = request.getParameter("detail");
        
        ActivityServiceInterface service = new ActivityService();
        int result = service.updateActivityDetail(token, id, detail, keyID);
        if(MAGICCODE.OK != result)
        {
            return new ModelAndView("/store/error.html");
        }
        
        List<Activity> activityList = service.queryStoreActivity(token, keyID);
        Iterator<Activity> it = activityList.iterator();
        while(it.hasNext())
        {
            Activity activtiy = it.next();
            activtiy.setMain_pic(ReplaceSrvToHttp.replace(activtiy.getMain_pic()));
        }
        return new ModelAndView("/general/activitylist.jsp","activityList",activityList);
    }
        

}
