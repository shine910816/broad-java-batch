package com.yems.framework.utility.parameters.constant;

import com.yems.framework.utility.parameters.property.Parameters;

public enum ConstGender implements Parameters
{
    MALE("1"),

    FEMALE("0"),

    UNKNOWN("");

    private String m_val;

    private ConstGender(String val)
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
