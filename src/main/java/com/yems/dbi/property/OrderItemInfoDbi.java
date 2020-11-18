package com.yems.dbi.property;

import java.util.Map;

import com.yems.dao.order.OrderItemSimpleInfoDao;
import com.yems.framework.utility.parameters.property.Dbi;

public interface OrderItemInfoDbi extends Dbi
{
    public Map<Integer, OrderItemSimpleInfoDao> selectSimpleOrderItenForIncomeStats(Integer schoolId);
}
