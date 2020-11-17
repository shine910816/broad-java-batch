package com.yems.dao.order;

import java.util.Date;

import com.yems.framework.utility.parameters.property.Dao;
import com.yems.framework.utility.parameters.property.DaoBuilder;

public class OrderItemInfoDao implements Dao
{

    @Override
    public Integer createId()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date createDate()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public static class ContainerBuilder implements DaoBuilder<OrderItemInfoDao>
    {
        private Integer m_orderItemId;

        @Override
        public OrderItemInfoDao build()
        {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
