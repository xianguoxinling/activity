package com.service.interfaces;

import java.util.List;

import com.model.information.Information;

public interface InfomationServiceInterface
{
    public int createInformation(String token,Information information);
    public List<Information> queryStoreInfomation(String token,String magicKey);
    public Information queryInformation(String token,String id,String magicKey);
    public int deleteInformation(String token,String id,String magicKey);
    public int updateInformation(String token,Information information);
}
