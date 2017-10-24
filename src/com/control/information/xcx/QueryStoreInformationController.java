package com.control.information.xcx;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.information.Information;
import com.service.infomation.InformationService;
import com.until.errorcode.MAGICCODE;
import com.until.replace.ReplaceSrvToHttp;

public class QueryStoreInformationController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        String token = request.getParameter("token");
        if (null == token)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }
        
        InformationService service = new InformationService();
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
        JSONArray infoJsonList = null;

        infoJsonList = JSONArray.fromObject(infoList);
        stream.write(infoJsonList.toString().getBytes("UTF-8"));
        return null;
    } 
}
