package com.control.activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.activity.Activity;
import com.platform.base.UserCookieManager;
import com.service.activity.ActivityService;
import com.service.interfaces.ActivityServiceInterface;
import com.until.num.UntilNum;

public class QueryActivityByIDController implements Controller
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
        
        ActivityServiceInterface service = new ActivityService();
        Activity activity = service.queryActivity(token,id, keyID);
        if(null == activity)
        {
            return new ModelAndView("/store/error.html");
        }else
        {
            return new ModelAndView("/general/editactivity.jsp","activity",activity);
        }
    }

}
