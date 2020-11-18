package com.yems.framework.connect.property;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Utility;
import com.yems.framework.utility.parameters.property.Parameters;

public abstract class DtoImpl extends MainClass
{
    private ResultSet m_res;

    public DtoImpl(ResultSet res)
    {
        m_res = res;
    }

    /**
     * 获取文字
     * 
     * @param column 字段名
     * @return String 文字
     */
    protected String get(String column)
    {
        try
        {
            return m_res.getString(column);
        }
        catch (SQLException e)
        {
            LOG.error("Column get failed: " + column + ", SQL exception: " + e.getMessage());
            return "";
        }
    }

    /**
     * 获取数字
     * 
     * @param column 字段名
     * @return Integer 数字
     */
    protected Integer num(String column)
    {
        return Utility.toInteger(get(column));
    }

    /**
     * 获取小数
     * 
     * @param column 字段名
     * @return Float 小数
     */
    protected Float point(String column)
    {
        return Utility.toFloat(column);
    }

    /**
     * 获取是非
     * 
     * @param column 字段名
     * @return Boolean 是非
     */
    protected Boolean diff(String column)
    {
        return get(column).equals("0") ? false : true;
    }

    /**
     * 获取日期
     * 
     * @param column 字段名
     * @return Date 日期
     */
    protected Date date(String column)
    {
        return Utility.toDate(get(column));
    }

    /**
     * 获取类型
     * 
     * @param column 字段名
     * @param clazz 类型枚举类
     * @return 类型枚举值
     */
    protected <T extends Enum<T> & Parameters> T type(String column, Class<T> clazz)
    {
        return Utility.getEnum(get(column), clazz);
    }
}
