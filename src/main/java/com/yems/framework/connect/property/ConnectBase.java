package com.yems.framework.connect.property;

import com.yems.framework.utility.MainClass;

public abstract class ConnectBase extends MainClass
{
    protected Boolean judge(String param)
    {
        return param.equals("0") ? false : true;
    }

    protected String toString(Object param)
    {
        return (String) param;
    }
}
