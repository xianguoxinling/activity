package com.service.interfaces;

import java.util.List;

import com.model.activity.Activity;
import com.model.production.Production;

public interface ActivityServiceInterface
{
    public int createActivity(String token,Activity activity);
    public int updateActivity(String token,Activity activity);
    
    public Activity queryActivity(String token,String id,String magicKey);
    
    public List<Activity> queryStoreActivity(String token,String magicKey);
    public int addActivityProduction(String token,String magicKey,String activityID,String productionID);
    public int deleteActivityProduction(String token,String magicKey,String activityID,String productionID);
    public List<Production> queryCanActivityProduction(String token,String id,String magicKey);
    
    public List<Production> queryActivityProduction(String token,String id,String magicKey);
    public int deleteActivity(String token,String id,String magicKey);
    
    public String queryActivityDetail(String token,String id,String magicKey);
    public int updateActivityDetail(String token,String id,String detail,String magicKey);
    public int showActivity(String token,String id,String magicKey);
    public int unshowActivity(String token,String id,String magicKey);
    
    
}
