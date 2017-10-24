package com.control.information;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.information.Information;
import com.platform.base.UserCookieManager;
import com.service.infomation.InformationService;
import com.until.errorcode.MAGICCODE;
import com.until.replace.ReplaceSrvToHttp;

public class CreateInformationController implements Controller
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
        
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        
        InformationService service = new InformationService();
        Information information = new Information();
        information.setName(name);
        information.setDetail(detail);
        information.setMagicKey(keyID);
        
        int result = service.createInformation(token,information);
        if(MAGICCODE.OK != result)
        {
            return new ModelAndView("/store/error.html");
        }
        
        List<Information> infoList = service.queryStoreInfomation(token,keyID);
        if(null != infoList)
        {
            Iterator<Information> it = infoList.iterator();
            while(it.hasNext())
            {
                Information tempInfo = it.next();
                tempInfo.setDetail(ReplaceSrvToHttp.replace(tempInfo.getDetail()));
            }
        }
        return new ModelAndView("/information/infolist.jsp","infoList",infoList);
        
    } 
}
