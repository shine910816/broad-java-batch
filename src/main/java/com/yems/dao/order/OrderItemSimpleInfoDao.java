package com.yems.dao.order;

import java.util.Date;

import com.yems.framework.utility.parameters.property.Dao;
import com.yems.framework.utility.parameters.property.DaoBuilder;
import com.yems.framework.utility.parameters.property.Parameters;

public class OrderItemSimpleInfoDao implements Dao
{
    private final Integer m_orderItemId;
    private final String m_contractNumber;
    private final Integer m_schoolId;
    private final Integer m_studentId;
    private final Integer m_orderItemAmount;
    private final Float m_orderItemHoursAmount;
    private final Float m_orderItemTransPrice;
    private final OrderItemStatus m_orderItemStatus;
    private final Float m_orderItemRemain;
    private final Float m_orderItemArrange;
    private final Float m_orderItemConfirm;
    private final Integer m_assignMemberId;
    private final Date m_assignDate;
    private final Integer m_operatedBy;
    private final Date m_insertDate;

    private OrderItemSimpleInfoDao(ContainerBuilder builder)
    {
        m_orderItemId = builder.m_orderItemId;
        m_contractNumber = builder.m_contractNumber;
        m_schoolId = builder.m_schoolId;
        m_studentId = builder.m_studentId;
        m_orderItemAmount = builder.m_orderItemAmount;
        m_orderItemHoursAmount = builder.m_orderItemHoursAmount;
        m_orderItemTransPrice = builder.m_orderItemTransPrice;
        m_orderItemStatus = builder.m_orderItemStatus;
        m_orderItemRemain = builder.m_orderItemRemain;
        m_orderItemArrange = builder.m_orderItemArrange;
        m_orderItemConfirm = builder.m_orderItemConfirm;
        m_assignMemberId = builder.m_assignMemberId;
        m_assignDate = builder.m_assignDate;
        m_operatedBy = builder.m_operatedBy;
        m_insertDate = builder.m_insertDate;
    }

    public Integer orderItemId()
    {
        return m_orderItemId;
    }

    public String contractNumber()
    {
        return m_contractNumber;
    }

    public Integer schoolId()
    {
        return m_schoolId;
    }

    public Integer studentId()
    {
        return m_studentId;
    }

    public Integer orderItemAmount()
    {
        return m_orderItemAmount;
    }

    public Float orderItemHoursAmount()
    {
        return m_orderItemHoursAmount;
    }

    public Float orderItemTransPrice()
    {
        return m_orderItemTransPrice;
    }

    public OrderItemStatus orderItemStatus()
    {
        return m_orderItemStatus;
    }

    public Float orderItemRemain()
    {
        return m_orderItemRemain;
    }

    public Float orderItemArrange()
    {
        return m_orderItemArrange;
    }

    public Float orderItemConfirm()
    {
        return m_orderItemConfirm;
    }

    public Integer assignMemberId()
    {
        return m_assignMemberId;
    }

    public Date assignDate()
    {
        return m_assignDate;
    }

    @Override
    public Integer createId()
    {
        return m_operatedBy;
    }

    @Override
    public Date createDate()
    {
        return m_insertDate;
    }

    public static class ContainerBuilder implements DaoBuilder<OrderItemSimpleInfoDao>
    {
        private Integer m_orderItemId;
        private String m_contractNumber;
        private Integer m_schoolId;
        private Integer m_studentId;
        private Integer m_orderItemAmount;
        private Float m_orderItemHoursAmount;
        private Float m_orderItemTransPrice;
        private OrderItemStatus m_orderItemStatus;
        private Float m_orderItemRemain;
        private Float m_orderItemArrange;
        private Float m_orderItemConfirm;
        private Integer m_assignMemberId;
        private Date m_assignDate;
        private Integer m_operatedBy;
        private Date m_insertDate;

        public ContainerBuilder orderItemId(Integer orderItemId)
        {
            m_orderItemId = orderItemId;
            return this;
        }

        public ContainerBuilder contractNumber(String contractNumber)
        {
            m_contractNumber = contractNumber;
            return this;
        }

        public ContainerBuilder schoolId(Integer schoolId)
        {
            m_schoolId = schoolId;
            return this;
        }

        public ContainerBuilder studentId(Integer studentId)
        {
            m_studentId = studentId;
            return this;
        }

        public ContainerBuilder orderItemAmount(Integer orderItemAmount)
        {
            m_orderItemAmount = orderItemAmount;
            return this;
        }

        public ContainerBuilder orderItemHoursAmount(Float orderItemHoursAmount)
        {
            m_orderItemHoursAmount = orderItemHoursAmount;
            return this;
        }

        public ContainerBuilder orderItemTransPrice(Float orderItemTransPrice)
        {
            m_orderItemTransPrice = orderItemTransPrice;
            return this;
        }

        public ContainerBuilder orderItemStatus(OrderItemStatus orderItemStatus)
        {
            m_orderItemStatus = orderItemStatus;
            return this;
        }

        public ContainerBuilder orderItemRemain(Float orderItemRemain)
        {
            m_orderItemRemain = orderItemRemain;
            return this;
        }

        public ContainerBuilder orderItemArrange(Float orderItemArrange)
        {
            m_orderItemArrange = orderItemArrange;
            return this;
        }

        public ContainerBuilder orderItemConfirm(Float orderItemConfirm)
        {
            m_orderItemConfirm = orderItemConfirm;
            return this;
        }

        public ContainerBuilder assignMemberId(Integer assignMemberId)
        {
            m_assignMemberId = assignMemberId;
            return this;
        }

        public ContainerBuilder assignDate(Date assignDate)
        {
            m_assignDate = assignDate;
            return this;
        }

        public ContainerBuilder operatedBy(Integer operatedBy)
        {
            m_operatedBy = operatedBy;
            return this;
        }

        public ContainerBuilder insertDate(Date insertDate)
        {
            m_insertDate = insertDate;
            return this;
        }

        @Override
        public OrderItemSimpleInfoDao build()
        {
            return new OrderItemSimpleInfoDao(this);
        }
    }

    public enum OrderItemStatus implements Parameters
    {
        ORDER_ITEM_STATUS_1("1", "待审核"),

        ORDER_ITEM_STATUS_2("2", "进行中"),

        ORDER_ITEM_STATUS_3("3", "已结课"),

        ORDER_ITEM_STATUS_4("4", "已退款"),

        UNKNOWN("", "");

        private String m_val;
        private String m_name;

        private OrderItemStatus(String val, String name)
        {
            m_val = val;
            m_name = name;
        }

        @Override
        public String val()
        {
            return m_val;
        }

        public String getName()
        {
            return m_name;
        }

        @Override
        public Parameters unknown()
        {
            return UNKNOWN;
        }
    }
}
