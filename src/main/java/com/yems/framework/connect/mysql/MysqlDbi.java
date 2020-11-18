package com.yems.framework.connect.mysql;

import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;

public abstract class MysqlDbi extends MainClass
{
    private MysqlMasterDbi m_master;
    private MysqlSlaveDbi m_slave;
    private Request m_request;

    public MysqlDbi(Request request)
    {
        m_request = request;
        if (m_master == null)
        {
            m_master = new MysqlMasterDbi(m_request);
        }
        if (m_slave == null)
        {
            m_slave = new MysqlSlaveDbi(m_request);
        }
    }

    protected MysqlMasterDbi master()
    {
        return m_master;
    }

    protected MysqlSlaveDbi slave()
    {
        return m_slave;
    }

    protected Request request()
    {
        return m_request;
    }
}
