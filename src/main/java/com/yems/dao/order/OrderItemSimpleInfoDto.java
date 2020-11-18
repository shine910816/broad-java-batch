package com.yems.dao.order;

import java.sql.ResultSet;

import com.yems.dao.order.OrderItemSimpleInfoDao.OrderItemStatus;
import com.yems.framework.connect.property.DtoImpl;
import com.yems.framework.utility.parameters.property.Dto;

public class OrderItemSimpleInfoDto extends DtoImpl implements Dto<OrderItemSimpleInfoDao>
{

    public OrderItemSimpleInfoDto(ResultSet res)
    {
        super(res);
    }

    @Override
    public OrderItemSimpleInfoDao dao()
    {
        return new OrderItemSimpleInfoDao.ContainerBuilder() //
                .orderItemId(num("order_item_id")) //
                .contractNumber(get("contract_number")) //
                .schoolId(num("school_id")) //
                .studentId(num("student_id")) //
                .orderItemAmount(num("order_item_amount")) //
                .orderItemTransPrice(point("order_item_trans_price")) //
                .orderItemStatus(type("order_item_status", OrderItemStatus.class)) //
                .orderItemRemain(point("order_item_remain")) //
                .orderItemArrange(point("order_item_arrange")) //
                .orderItemConfirm(point("order_item_confirm")) //
                .assignMemberId(num("assign_member_id")) //
                .assignDate(date("assign_date")) //
                .operatedBy(num("operated_by")) //
                .insertDate(date("insert_date")) //
                .build();
    }

    public static OrderItemSimpleInfoDao getResult(ResultSet res)
    {
        return new OrderItemSimpleInfoDto(res).dao();
    }
}
