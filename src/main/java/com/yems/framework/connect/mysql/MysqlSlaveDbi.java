package com.yems.framework.connect.mysql;

import java.sql.ResultSet;

import com.yems.framework.connect.mysql.property.MysqlConnect;
import com.yems.framework.utility.Request;

public class MysqlSlaveDbi extends MysqlConnect
{
    public MysqlSlaveDbi(Request request)
    {
        super(request);
    }

    public ResultSet query(String sql)
    {
        LOG.info("Slave query: " + sql);
        return slaveQuery(sql);
    }
}
