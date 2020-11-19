package com.yems.dbi.property;

import java.util.Map;

import com.yems.framework.utility.parameters.property.Dbi;

public interface SurplusInfoDbi extends Dbi
{
    public void insertSurplus(Map<String, String> insertData);
}
