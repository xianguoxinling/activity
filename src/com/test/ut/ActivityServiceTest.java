package com.test.ut;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.model.activity.Activity;
import com.model.production.Production;
import com.service.activity.ActivityService;
import com.service.interfaces.ActivityServiceInterface;
import com.until.errorcode.MAGICCODE;

public class ActivityServiceTest
{

    ActivityServiceInterface service =null;
    String token = "3koos2wlg2sr9urfrd79deziqhlixo1s25";
    String keyID = "eb07c48a400a4288a0ee8322250cff04";
    
    @Before
    public void setUp() throws Exception
    {
        service = new ActivityService();
    }

//    @Test
    public void testCreateActivity()
    {
        Activity activity = new Activity();
        activity.setBegin_time("2017-09-23");
        activity.setBriefIntroduction("test");
        activity.setCreate_time("2017-09-23");
        activity.setDetailedIntroduction("test");
        activity.setEnd_time("2017-09-23");
        activity.setMain_pic("zzz");
        activity.setMagicKey(keyID);
        activity.setName("test");
        
        int result = MAGICCODE.OK;
        result = service.createActivity(token, activity);
        assertEquals(MAGICCODE.OK,result);
        
    }

    @Test
    public void testQueryActivity()
    {
        Activity activity = service.queryActivity("1", token, keyID);
        System.out.println(activity.getBegin_time());
        System.out.println(activity.getBriefIntroduction());
        System.out.println(activity.getCreate_time());
        System.out.println(activity.getDetailedIntroduction());
        System.out.println(activity.getEnd_time());
        System.out.println(activity.getId());
        System.out.println(activity.getMagicKey());
        System.out.println(activity.getMain_pic());
        System.out.println(activity.getName());
        List<Production> productionList = activity.getProductionList();
        System.out.println("XXXXIIIII:"+productionList.size());
        Iterator<Production> it = productionList.iterator();
        while(it.hasNext())
        {
            Production production  = it.next();
            System.out.println("XXXXXXX:"+production.getName());
        }
    }

    @Test
    public void testQueryStoreActivity()
    {
        List<Activity> activityList = service.queryStoreActivity(token, keyID);
        Iterator<Activity> it = activityList.iterator();
        while(it.hasNext())
        {
            Activity activity = it.next();
            System.out.println(activity.getBegin_time());
            System.out.println(activity.getBriefIntroduction());
            System.out.println(activity.getCreate_time());
            System.out.println(activity.getDetailedIntroduction());
            System.out.println(activity.getEnd_time());
            System.out.println(activity.getId());
            System.out.println(activity.getMagicKey());
            System.out.println(activity.getMain_pic());
            System.out.println(activity.getName());
        }
        
    }
    
    @Test
    public void testAddProductionInActivity()
    {
        String productionID = "78";
        String activityID = "1";
        int result = service.addActivityProduction(activityID, keyID, activityID, productionID);
        assertEquals(MAGICCODE.OK,result);
    }

}
