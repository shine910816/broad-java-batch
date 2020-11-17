package com.yems.services.property;

import com.yems.framework.utility.parameters.property.Parameters;

public enum ServicePages implements Parameters
{
    STUDENT_INCOME,

    TEMPLATE;

    @Override
    public String val()
    {
        return name().toLowerCase();
    }

    @Override
    public Parameters unknown()
    {
        return TEMPLATE;
    }
}
