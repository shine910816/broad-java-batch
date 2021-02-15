package com.yems.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.yems.dao.order.OrderItemSimpleInfoDao.OrderItemStatus;
import com.yems.dbi.property.YemsDbi;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.Utility;
import com.yems.framework.utility.Variable;
import com.yems.services.property.ServiceControllerImpl;

public class ChangeToEndingController extends ServiceControllerImpl<YemsDbi>
{
    private Set<Integer> m_orderItemIdSet = new HashSet<>();

    protected ChangeToEndingController(Request request, YemsDbi dbi)
    {
        super(request, dbi);
    }

    @Override
    public boolean doMainValidate()
    {
        m_orderItemIdSet = dbi().orderItem().selectNoChangeEndingOrderItem(Variable.SCHOOL_ID).keySet();
        return true;
    }

    @Override
    public boolean doMainExecute()
    {
        if (!Utility.isEmpty(m_orderItemIdSet))
        {
            Map<String, String> data = new HashMap<>();
            data.put("order_item_status", OrderItemStatus.ORDER_ITEM_STATUS_3.val());
            for (Integer orderItemId : m_orderItemIdSet)
            {
                LOG.info("Change ending for order item id: " + orderItemId);
                if (!dbi().orderItem().updateOrderItemInfo(orderItemId, data))
                {
                    LOG.error("Change ending failed for order item id: " + orderItemId);
                }
            }
        }
        return true;
    }
}
