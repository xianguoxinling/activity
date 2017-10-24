package com.model.activity;

import java.util.List;

import com.model.production.Production;
/**
 * 基本活动，后期活动需集成
 * @author xbayonet
 *
 */
public class Activity
{
    private String id = null;
    private String name = null;
    private String main_pic = null;
    private List<String> picList = null;
    private String briefIntroduction = null;
    private String detailedIntroduction = null;
    private List<Production> productionList = null;
    private String state = null;
    private String create_time = null;
    private String begin_time = null;
    private String end_time = null;
    private String magicKey = null;
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getMain_pic()
    {
        return main_pic;
    }
    public void setMain_pic(String main_pic)
    {
        this.main_pic = main_pic;
    }
    public List<String> getPicList()
    {
        return picList;
    }
    public void setPicList(List<String> picList)
    {
        this.picList = picList;
    }
    public String getBriefIntroduction()
    {
        return briefIntroduction;
    }
    public void setBriefIntroduction(String briefIntroduction)
    {
        this.briefIntroduction = briefIntroduction;
    }
    public String getDetailedIntroduction()
    {
        return detailedIntroduction;
    }
    public void setDetailedIntroduction(String detailedIntroduction)
    {
        this.detailedIntroduction = detailedIntroduction;
    }
    public List<Production> getProductionList()
    {
        return productionList;
    }
    public void setProductionList(List<Production> productionList)
    {
        this.productionList = productionList;
    }
    public String getCreate_time()
    {
        return create_time;
    }
    public void setCreate_time(String create_time)
    {
        this.create_time = create_time;
    }
    public String getBegin_time()
    {
        return begin_time;
    }
    public void setBegin_time(String begin_time)
    {
        this.begin_time = begin_time;
    }
    public String getEnd_time()
    {
        return end_time;
    }
    public void setEnd_time(String end_time)
    {
        this.end_time = end_time;
    }
    public String getMagicKey()
    {
        return magicKey;
    }
    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
}
