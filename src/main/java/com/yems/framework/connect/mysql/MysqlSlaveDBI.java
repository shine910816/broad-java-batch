package com.yems.framework.connect.mysql;

import java.sql.ResultSet;

import com.yems.framework.connect.mysql.property.MysqlConnect;
import com.yems.framework.utility.Request;

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
