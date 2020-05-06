package com.broad.batch.framework.connect.mysql;

import java.sql.ResultSet;

import com.broad.batch.framework.connect.mysql.property.MysqlConnect;
import com.broad.batch.framework.utility.Request;


public class MysqlSlaveDBI extends MysqlConnect
{
    public MysqlSlaveDBI(Request request)
    {
        super(request);
    }

    public ResultSet query(String sql)
    {
        return slaveQuery(sql);
    }
}
