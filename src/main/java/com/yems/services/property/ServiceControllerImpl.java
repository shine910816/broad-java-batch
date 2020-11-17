package com.yems.services.property;

import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.parameters.property.Dbi;

public abstract class ServiceControllerImpl<T extends Dbi> extends MainClass implements ServiceController
{
    private Request m_request;
    private T m_dbi;

    public ServiceControllerImpl(Request request, T dbi)
    {
        m_request = request;
        m_dbi = dbi;
    }

    protected Request request()
    {
        return m_request;
    }

    protected T dbi()
    {
        return m_dbi;
    }
}
