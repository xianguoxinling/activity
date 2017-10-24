package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.activity.Activity;
import com.model.production.Production;
import com.model.production.ProductionState;
import com.until.errorcode.MAGICCODE;

public class ActivityDBManager
{

    protected DBManager dbManager = null;

    public int createActivity(Activity activity)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "INSERT INTO activity (name,mainpic,magic_key,begin_time,finish_time,brief_introduction,detail_introduction,create_time) VALUES (?,?,?,?,?,?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, activity.getName());
            statement.setString(2, activity.getMain_pic());
            statement.setString(3, activity.getMagicKey());
            statement.setString(4, activity.getBegin_time());
            statement.setString(5, activity.getEnd_time());
            statement.setString(6, activity.getBriefIntroduction());
            statement.setString(7, activity.getDetailedIntroduction());
            statement.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public Activity queryActivityByID(String id,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from activity where id = ? and magic_key = ?";
        Activity activity = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(id));
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                activity = dbUntil.getActivityFromResultSet(resultSet);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return activity;
    }
    
    public int checkActivityName(String name,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select name from activity where name = ? and magic_key = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                return MAGICCODE.ACTIVITY_EXIST;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.ACTIVITY_NOT_EXIST;
    }
    
    public int queryActivityList(List<Activity> activityList,String magickey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from activity where magic_key = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, magickey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Activity activity = dbUntil.getActivityFromResultSet(resultSet);
                activityList.add(activity);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }

    public int queryActivityProduction(String activityID,String magicKey,List<Production> productionList)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from production a left join activity_production b on a.id = b.production_id where b.activity_id = ? and a.magic_key = ?";
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(activityID));
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Production production = dbUntil.getProductionFromResultSet(resultSet);
                productionList.add(production);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int addActivityProduction(String activityID,String productionID,String magicKey)
    {
        
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO activity_production (activity_id,production_id,magic_key) VALUES (?,?,?)";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(activityID));
            statement.setLong(2, Long.parseLong(productionID));
            statement.setString(3, magicKey);
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int checkProductionInActivity(String activityID,String productionID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from activity_production where activity_id = ? and production_id =? and magic_key=?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(activityID));
            statement.setLong(2, Long.parseLong(productionID));
            statement.setString(3, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                return MAGICCODE.PRODUCTION_IN_ACTIVITY;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.PRODUCTION_NOT_IN_ACTIVITY;
    }
    
    public int deleteActivityProduction(String activityID,String productionID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;

        String sql = "delete from  activity_production where activity_id = ? and production_id =? and magic_key=?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(activityID));
            statement.setLong(2, Long.parseLong(productionID));
            statement.setString(3, magicKey);
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int deleteAllActivityProduction(String activityID,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;

        String sql = "delete from activity_production where activity_id = ? and magic_key=?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(activityID));
            statement.setString(2, magicKey);
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int queryAllProduction(List<Production> productionList, String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "SELECT * from production WHERE magic_key = ? and state != ? order by update_time";
        PreparedStatement statement;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setString(2, ProductionState.DELETE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Production production = dbUntil.getProductionFromResultSet2(resultSet);
                productionList.add(production);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int deleteActivity(String id,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;

        String sql = "delete from activity where id = ? and magic_key=?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(id));
            statement.setString(2, magicKey);
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public String queryActivityDetail(String id,String magicKey)
    {
        String detail = null;
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select detail_introduction from activity where id = ? and magic_key = ?";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(id));
            statement.setString(2, magicKey);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                detail = resultSet.getString("detail_introduction");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return detail;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return detail;
    }
    
    public int updateActivityDetail(String id,String detail,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update activity set detail_introduction = ? where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, detail);
            statement.setLong(2, Long.parseLong(id));
            statement.setString(3, magicKey);
            
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int updateActivity(Activity activity)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update activity set name = ?,begin_time=?,finish_time=?,brief_introduction=? where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, activity.getName());
            statement.setString(2, activity.getBegin_time());
            statement.setString(3, activity.getEnd_time());
            statement.setString(4, activity.getBriefIntroduction());
            statement.setLong(5, Long.parseLong(activity.getId()));
            statement.setString(6, activity.getMagicKey());
            
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int updateActivityPic(String id,String mainpic,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update activity set mainpic = ? where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mainpic);
            statement.setLong(2, Long.parseLong(id));
            statement.setString(3, magicKey);
            
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int updateActivityState(String id,String state,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update activity set state = ? where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.setLong(2, Long.parseLong(id));
            statement.setString(3, magicKey);
            
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
}


