package com.control.activity.xcx;

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

import com.model.activity.Activity;
import com.service.activity.ActivityService;
import com.service.interfaces.ActivityServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.replace.ReplaceSrvToHttp;

public class QueryStoreActivityController implements Controller
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

        ActivityServiceInterface service = new ActivityService();
        List<Activity> activityList = service.queryStoreActivity(token, keyID);
        Iterator<Activity> it = activityList.iterator();
        while (it.hasNext())
        {
            Activity activtiy = it.next();
            activtiy.setMain_pic(ReplaceSrvToHttp.replace(activtiy.getMain_pic()));
        }

        JSONArray activityJsonList = null;

        activityJsonList = JSONArray.fromObject(activityList);
        stream.write(activityJsonList.toString().getBytes("UTF-8"));
        return null;
    }
}
