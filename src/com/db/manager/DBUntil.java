package com.db.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.activity.Activity;
import com.model.information.Information;
import com.model.production.Production;

public class DBUntil
{
    public Activity getActivityFromResultSet(ResultSet resultSet)
    {
        Activity activity = new Activity();
        try
        {
            activity.setId(resultSet.getString("id"));
            activity.setName(resultSet.getString("name"));
            activity.setBriefIntroduction(resultSet.getString("brief_introduction"));
            activity.setDetailedIntroduction(resultSet.getString("detail_introduction"));
            activity.setMain_pic(resultSet.getString("mainpic"));
            activity.setBegin_time(resultSet.getString("begin_time"));
            activity.setEnd_time(resultSet.getString("finish_time"));
            activity.setCreate_time(resultSet.getString("create_time"));
            activity.setMagicKey(resultSet.getString("magic_key"));
            activity.setState(resultSet.getString("state"));
            
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return activity;
    }
    
    public Information getInformationFromResultSet(ResultSet resultSet)
    {
        Information information = new Information();
        try
        {
            information.setId(resultSet.getString("id"));
            information.setName(resultSet.getString("name"));
            information.setDetail(resultSet.getString("detail"));
            information.setCreateTime(resultSet.getString("create_time"));
            
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return information;
    }
    
    public Production getProductionFromResultSet(ResultSet resultSet)
    {
        Production production = new Production();
        try
        {
            production.setId(resultSet.getString("a.id"));
            production.setName(resultSet.getString("a.name"));
            production.setBriefIntroduction(resultSet.getString("a.brief_introduction"));
            production.setDetailedIntroduction(resultSet.getString("a.detailed_introduction"));
            production.setCategory(resultSet.getString("a.category"));
            production.setMainPic(resultSet.getString("a.main_pic"));
            production.setNumber(resultSet.getLong("a.number"));
            production.setUpdateTime(resultSet.getString("a.update_time"));
            production.setMagicKey(resultSet.getString("a.magic_key"));
            production.setPrice(resultSet.getDouble("a.price"));
            production.setUuid(resultSet.getString("a.uuid"));
            production.setState(resultSet.getString("a.state"));
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return production;
    }
    
    public Production getProductionFromResultSet2(ResultSet resultSet)
    {
        Production production = new Production();
        try
        {
            production.setId(resultSet.getString("id"));
            production.setName(resultSet.getString("name"));
            production.setBriefIntroduction(resultSet.getString("brief_introduction"));
            production.setDetailedIntroduction(resultSet.getString("detailed_introduction"));
            production.setCategory(resultSet.getString("category"));
            production.setMainPic(resultSet.getString("main_pic"));
            production.setNumber(resultSet.getLong("number"));
            production.setUpdateTime(resultSet.getString("update_time"));
            production.setMagicKey(resultSet.getString("magic_key"));
            production.setPrice(resultSet.getDouble("price"));
            production.setUuid(resultSet.getString("uuid"));
            production.setState(resultSet.getString("state"));
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return production;
    }
}
