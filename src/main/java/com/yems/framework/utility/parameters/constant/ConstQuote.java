package com.yems.framework.utility.parameters.constant;

import com.yems.framework.utility.parameters.property.Parameters;

public enum ConstQuote implements Parameters
{
    DOUBLE_QUOTE("\""),

    SINGLE_QUOTE("'"),

    BACK_QUOTE("`"),

    UNKNOWN("");

    private String m_val;

    private ConstQuote(String val)
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
