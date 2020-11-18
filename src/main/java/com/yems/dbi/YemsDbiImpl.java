package com.yems.dbi;

import com.yems.dbi.property.OrderItemInfoDbi;
import com.yems.dbi.property.StudentInfoDbi;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.connect.mysql.MysqlDbi;
import com.yems.framework.utility.Request;

public class YemsDbiImpl extends MysqlDbi implements YemsDbi
{
    private StudentInfoDbi m_student;
    private OrderItemInfoDbi m_orderItem;

    public YemsDbiImpl(Request request)
    {
        super(request);
    }

    @Override
    public StudentInfoDbi student()
    {
        if (m_student == null)
        {
            m_student = new StudentInfoDbiImpl(request());
        }
        return m_student;
    }

    @Override
    public OrderItemInfoDbi orderItem()
    {
        if (m_orderItem == null)
        {
            m_orderItem = new OrderItemInfoDbiImpl(request());
        }
        return m_orderItem;
    }
}
