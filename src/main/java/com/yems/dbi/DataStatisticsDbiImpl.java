package com.yems.dbi;

import java.util.Map;

import com.yems.dbi.property.DataStatisticsDbi;
import com.yems.framework.connect.mysql.MysqlDbi;
import com.yems.framework.utility.Request;

public class DataStatisticsDbiImpl extends MysqlDbi implements DataStatisticsDbi
{
    public DataStatisticsDbiImpl(Request request)
    {
        super(request);
    }

    @Override
    public void insertSurplus(Map<String, String> insertData)
    {
        master().insert("surplus_info", insertData);
    }
}
