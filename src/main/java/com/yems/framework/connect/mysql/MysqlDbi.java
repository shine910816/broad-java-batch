package com.yems.framework.connect.mysql;

import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;

public abstract class MysqlDbi extends MainClass
{
    private MysqlMasterDbi m_master;
    private MysqlSlaveDbi m_slave;

    public MysqlDbi(Request request)
    {
        if (m_master == null)
        {
            m_master = new MysqlMasterDbi(request);
        }
        if (m_slave == null)
        {
            m_slave = new MysqlSlaveDbi(request);
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
}
