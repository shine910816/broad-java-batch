package com.broad.batch.framework.utility.parameters.constant;

import com.broad.batch.framework.utility.parameters.property.Parameters;

public enum ConstBreak implements Parameters
{
    WINDOWS("\r\n"),

    LINUX("\n"),

    UNKNOWN("");

    private String m_val;

    private ConstBreak(String val)
    {
        m_val = val;
    }

    @Override
    public String val()
    {
        return m_val;
    }

    @Override
    public Parameters unknown()
    {
        return UNKNOWN;
    }
}
