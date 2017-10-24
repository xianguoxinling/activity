package com.control.information;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.information.Information;
import com.platform.base.UserCookieManager;
import com.service.infomation.InformationService;
import com.until.num.UntilNum;
import com.until.replace.ReplaceSrvToHttp;

public class QueryInformatioByIDnController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        
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
        
        InformationService service = new InformationService();
        Information info = service.queryInformation(token,id, keyID);
        if(null != info)
        {
            info.setDetail(ReplaceSrvToHttp.replace(info.getDetail()));
        }
        
        json = JSONObject.fromObject(info);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    } 
}
