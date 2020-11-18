package com.yems.dbi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import com.yems.dao.order.OrderItemSimpleInfoDao;
import com.yems.dao.order.OrderItemSimpleInfoDao.OrderItemStatus;
import com.yems.dao.order.OrderItemSimpleInfoDto;
import com.yems.dbi.property.OrderItemInfoDbi;
import com.yems.framework.connect.mysql.MysqlDbi;
import com.yems.framework.utility.Request;

public class OrderItemInfoDbiImpl extends MysqlDbi implements OrderItemInfoDbi
{
    public OrderItemInfoDbiImpl(Request request)
    {
        super(request);
    }

    @Override
    public Map<Integer, OrderItemSimpleInfoDao> selectSimpleOrderItenForIncomeStats(Integer schoolId)
    {
        LOG.info("Select simple order iten for income stats by school id: " + schoolId);

        String sql = "SELECT order_item_id, contract_number, school_id, student_id," + //
                " order_item_amount, order_item_trans_price, order_item_status, order_item_remain, order_item_arrange, order_item_confirm," + //
                " assign_member_id, assign_date, operated_by, insert_date" + //
                " FROM order_item_info WHERE del_flg = 0 AND order_item_status = " + OrderItemStatus.ORDER_ITEM_STATUS_2.val() + //
                " AND school_id = " + schoolId;

        ResultSet result = slave().query(sql);
        Map<Integer, OrderItemSimpleInfoDao> output = new TreeMap<>();
        try
        {
            while (result.next())
            {
                OrderItemSimpleInfoDao dao = OrderItemSimpleInfoDto.getResult(result);
                output.put(dao.orderItemId(), dao);
            }
        }
        catch (SQLException e)
        {
            LOG.error("Select simple order iten for income stats failed: " + e.getMessage());
        }
        return output;
    }

}
