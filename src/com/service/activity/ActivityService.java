package com.service.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.db.manager.ActivityDBManager;
import com.model.activity.Activity;
import com.model.activity.ActivityState;
import com.model.production.Production;
import com.service.interfaces.ActivityServiceInterface;
import com.until.errorcode.MAGICCODE;

public class ActivityService implements ActivityServiceInterface
{

    private ActivityDBManager dbManager = null;

    public ActivityService()
    {
        dbManager = new ActivityDBManager();
    }

    @Override
    public int createActivity(String token, Activity activity)
    {
        int result = MAGICCODE.OK;
        result = dbManager.checkActivityName(activity.getName(), activity.getMagicKey());
        if(MAGICCODE.ACTIVITY_EXIST == result)
        {
            return MAGICCODE.OK;
        }else if(MAGICCODE.DB_ERROR == result)
        {
            return result;
        }
        result = dbManager.createActivity(activity);
        if (MAGICCODE.OK != result)
        {

        }

        return result;
    }

    @Override
    public Activity queryActivity(String token,String id, String magicKey)
    {
        int result = MAGICCODE.OK;
        Activity activity = dbManager.queryActivityByID(id, magicKey);
        if (null != activity)
        {
            List<Production> productionList = new ArrayList<Production>();
            result = dbManager.queryActivityProduction(id, magicKey, productionList);
            if (MAGICCODE.OK == result)
            {
                activity.setProductionList(productionList);
            }

        }
        return activity;
    }

    @Override
    public List<Activity> queryStoreActivity(String token, String magicKey)
    {
        List<Activity> activityList = new ArrayList<Activity>();
        int result = MAGICCODE.OK;
        result = dbManager.queryActivityList(activityList, magicKey);
        if (MAGICCODE.OK != result)
        {

        }

        return activityList;
    }

    @Override
    public int addActivityProduction(String token, String magicKey, String activityID, String productionID)
    {
        int result = MAGICCODE.OK;
        result = dbManager.checkProductionInActivity(activityID, productionID, magicKey);
        if (MAGICCODE.PRODUCTION_IN_ACTIVITY == result)
        {
            return MAGICCODE.OK;
        } else if (MAGICCODE.DB_ERROR == result)
        {
            return result;
        }

        result = dbManager.addActivityProduction(activityID, productionID, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        return result;
    }

    @Override
    public int deleteActivityProduction(String token, String magicKey, String activityID, String productionID)
    {
        int result = MAGICCODE.OK;
        result = dbManager.deleteActivityProduction(activityID, productionID, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        return result;
    }

    @Override
    public List<Production> queryCanActivityProduction(String token,String id, String magicKey)
    {
        int result = MAGICCODE.OK;
        List<Production> productionList = new ArrayList<Production>();
        List<Production> activityProductionList = new ArrayList<Production>();
        result = dbManager.queryAllProduction(productionList, magicKey);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        
        result = dbManager.queryActivityProduction(id, magicKey, activityProductionList);
        if(MAGICCODE.OK != result)
        {
            return null;
        }
        
        Iterator<Production> it2 = activityProductionList.iterator();
        while (it2.hasNext())
        {
            Production activityProduction = it2.next();
            for (int i = 0; i < productionList.size(); i++)
            {
                Production production = productionList.get(i);
                if (production.getId().equals(activityProduction.getId()))
                {
                    productionList.remove(i);
                }
            }
        }

//        Iterator<Production> it3 = productionList.iterator();
//        while(it3.hasNext())
//        {
//            System.out.println("PRODUCITON NAME:"+it3.next().getName());
//        }
        
        return productionList;
    }

    @Override
    public List<Production> queryActivityProduction(String token,String id,  String magicKey)
    {
        int result = MAGICCODE.OK;
        List<Production> productionList = new ArrayList<Production>();
        result = dbManager.queryActivityProduction(id, magicKey, productionList);
        if(MAGICCODE.OK != result)
        {
            
        }
        return productionList;
    }

    @Override
    public int deleteActivity( String token,String id, String magicKey)
    {
        int result = MAGICCODE.OK;
        
        //删除所有activity所有商品信息
        result = dbManager.deleteAllActivityProduction(id, magicKey);
        if(MAGICCODE.OK != result)
        {
//            return result;
        }
        result = dbManager.deleteActivity(id, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public String queryActivityDetail(String token, String id, String magicKey)
    {
        String detail = null;
        detail = dbManager.queryActivityDetail(id, magicKey);
        return detail;
    }

    @Override
    public int updateActivityDetail(String token, String id, String detail, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateActivityDetail(id, detail, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int updateActivity(String token, Activity activity)
    {
        int result = MAGICCODE.OK ;
        result = dbManager.updateActivity(activity);
        if(MAGICCODE.OK != result)
        {
            
        }
        if(null != activity.getMain_pic())
        {
            result = dbManager.updateActivityPic(activity.getId(), activity.getMain_pic(), activity.getMagicKey());
            if(MAGICCODE.OK != result)
            {
                
            }
        }
        return result;
    }

    @Override
    public int showActivity(String token, String id, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateActivityState(id, ActivityState.ACTIVITY_ONLINE, magicKey);
        if(MAGICCODE.OK!=result)
        {
            
        }
        return result;
    }

    @Override
    public int unshowActivity(String token, String id, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateActivityState(id, ActivityState.ACTIVITY_OFFLINE, magicKey);
        if(MAGICCODE.OK!=result)
        {
            
        }
        return result;
    }

}
