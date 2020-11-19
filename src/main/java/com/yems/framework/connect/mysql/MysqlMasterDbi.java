package com.yems.framework.connect.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.yems.framework.utility.Request;
import com.yems.framework.utility.Utility;
import com.yems.framework.utility.Variable;
import com.yems.framework.utility.parameters.constant.ConstDatetime;
import com.yems.framework.utility.parameters.constant.ConstQuote;

public class MysqlMasterDbi extends MysqlSlaveDbi
{
    public MysqlMasterDbi(Request request)
    {
        super(request);
    }

    public Integer insert(String tableName, Map<String, String> insertData)
    {
        String column = "";
        String value = "";
        Iterator<String> keySet = insertData.keySet().iterator();
        while (keySet.hasNext())
        {
            String key = keySet.next();
            column += Utility.quote(key, ConstQuote.BACK_QUOTE) + ", ";
            value += Utility.quote(insertData.get(key)) + ", ";
        }
        String datatime = Utility.quote(Utility.getCurrentDate(ConstDatetime.DATETIME));
        column += "`operated_by`, `insert_date`, `update_date`, `del_flg`";
        value += Utility.quote(Variable.ADMIN_ID) + ", " + datatime + ", " + datatime + ", " + Utility.quote("0");
        String sql = String.format("INSERT INTO `%s` (%s) VALUES (%s);", tableName, column, value);
        LOG.info("Master query: " + sql);
        Integer res = 0;
        if (masterQuery(sql) == 0)
        {
            return res;
        }
        try
        {
            ResultSet lastId = slaveQuery("SELECT LAST_INSERT_ID();");
            lastId.next();
            res = Utility.toInteger(lastId.getString(1));
        }
        catch (SQLException e)
        {
            LOG.error(e.getMessage());
        }
        return res;
    }

    public boolean update(String tableName, Map<String, String> updateData, String where)
    {
        String update = "";
        Iterator<String> keySet = updateData.keySet().iterator();
        while (keySet.hasNext())
        {
            String key = keySet.next();
            update += Utility.quote(key, ConstQuote.BACK_QUOTE) + " = " + Utility.quote(updateData.get(key)) + ", ";
        }
        update += "`update_date` = " + Utility.quote(Utility.getCurrentDate(ConstDatetime.DATETIME));
        String sql = String.format("UPDATE `%s` SET %s WHERE %s;", tableName, update, where);
        LOG.info("Master query: " + sql);
        if (masterQuery(sql) > 0)
        {
            return true;
        }
        return false;
    }

    public boolean delete(String tableName, String where)
    {
        String sql = String.format("DELETE FROM `%s` WHERE %s;", tableName, where);
        LOG.info("Master query: " + sql);
        if (masterQuery(sql) > 0)
        {
            return true;
        }
        return false;
    }

    public void begin()
    {
        masterQuery("BEGIN;");
    }

    public void rollback()
    {
        masterQuery("ROLLBACK;");
    }

    public void commit()
    {
        masterQuery("COMMIT;");
    }
}
