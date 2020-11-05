package com.yems.dbi;

import com.yems.dbi.property.StudentInfoDbi;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.connect.mysql.MysqlDbi;
import com.yems.framework.utility.Request;

public class YemsDbiImpl extends MysqlDbi implements YemsDbi
{
    private Request m_request;
    private StudentInfoDbi m_student;

    public YemsDbiImpl(Request request)
    {
        super(request);
        m_request = request;
    }

    @Override
    public StudentInfoDbi student()
    {
        if (m_student == null)
        {
            m_student = new StudentInfoDbiImpl(m_request);
        }
        return m_student;
    }
}
