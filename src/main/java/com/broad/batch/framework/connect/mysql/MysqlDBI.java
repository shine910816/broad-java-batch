package com.broad.batch.framework.connect.mysql;

import com.broad.batch.framework.utility.MainClass;
import com.broad.batch.framework.utility.Request;

public abstract class MysqlDBI extends MainClass
{
    private MysqlMasterDBI m_master;
    private MysqlSlaveDBI m_slave;

    public MysqlDBI(Request request)
    {
        if (m_master == null)
        {
            m_master = new MysqlMasterDBI(request);
        }
        if (m_slave == null)
        {
            m_slave = new MysqlSlaveDBI(request);
        }
    }

    protected MysqlMasterDBI master()
    {
        return m_master;
    }

    protected MysqlSlaveDBI slave()
    {
        return m_slave;
    }
}
