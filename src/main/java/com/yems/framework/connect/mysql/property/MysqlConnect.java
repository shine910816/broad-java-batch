package com.yems.framework.connect.mysql.property;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.yems.framework.utility.MainClass;
import com.yems.framework.utility.Request;
import com.yems.framework.utility.parameters.property.OptionElement;

public class MysqlConnect extends MainClass
{
    private final String m_request;
    private final String m_userName;
    private final String m_password;

    private Connection m_connect;
    private Statement m_statement;
    private ResultSet m_result;

    public MysqlConnect(Request request)
    {
        m_request = "jdbc:mysql://" + request.getParameter("url") + ":" + request.getParameter("port") + "/" + request.getParameter("db");
        m_userName = request.getParameter("name");
        m_password = request.getParameter("pswd");
        if (m_connect == null)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                m_connect = (Connection) DriverManager.getConnection(m_request, m_userName, m_password);
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
            }
        }
    }

    protected ResultSet slaveQuery(String sql)
    {
        LOG.info("SQL query: " + sql);

        try
        {
            m_statement = (Statement) m_connect.createStatement();
            m_result = m_statement.executeQuery(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m_result;
    }

    protected Integer masterQuery(String sql)
    {
        Integer result = 0;
        try
        {
            m_statement = (Statement) m_connect.createStatement();
            result = m_statement.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void free()
    {
        try
        {
            if (m_result != null)
            {
                m_result.close();
                m_result = null;
            }
            if (m_statement != null)
            {
                m_statement.close();
                m_statement = null;
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
        }
    }

    public enum DatabaseType implements OptionElement
    {
        MASTER,

        SLAVE;

        @Override
        public String val()
        {
            return name();
        }
    }

    public enum OptionType implements OptionElement
    {
        INSERT,

        UPDATE,

        DELETE,

        SELECT;

        @Override
        public String val()
        {
            return name();
        }

        public boolean check(String sql)
        {
            if (name().toLowerCase().equals(sql.substring(0, 6).toLowerCase()))
            {
                return true;
            }
            return false;
        }
    }
}
