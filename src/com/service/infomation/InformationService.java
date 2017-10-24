package com.service.infomation;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.InformationDBManager;
import com.model.information.Information;
import com.service.interfaces.InfomationServiceInterface;
import com.until.errorcode.MAGICCODE;

public class InformationService implements InfomationServiceInterface
{

    private InformationDBManager dbManager = null;
    public InformationService()
    {
        dbManager = new InformationDBManager();
    }
    
    @Override
    public int createInformation(String token,Information information)
    {
        int result = MAGICCODE.OK;
        result = dbManager.createInfomation(information);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public List<Information> queryStoreInfomation(String token,String magicKey)
    {
        int result = MAGICCODE.OK;
        List<Information> infoList = new ArrayList<Information>();
        result = dbManager.queryStoreInfomation(infoList, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return infoList;
    }

    @Override
    public Information queryInformation(String token,String id, String magicKey)
    {
        Information infomation = dbManager.queryInfomationByID(id, magicKey);
        return infomation;
    }

    @Override
    public int deleteInformation(String token,String id, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.deleteInfomation(id, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public int updateInformation(String token, Information information)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateInfomation(information);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

}
