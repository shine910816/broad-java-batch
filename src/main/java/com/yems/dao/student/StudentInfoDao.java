package com.yems.dao.student;

import java.util.Date;

import com.yems.framework.utility.Utility;
import com.yems.framework.utility.parameters.constant.ConstDatetime;
import com.yems.framework.utility.parameters.constant.ConstGender;
import com.yems.framework.utility.parameters.property.Dao;
import com.yems.framework.utility.parameters.property.DaoBuilder;
import com.yems.framework.utility.parameters.property.Parameters;

public class StudentInfoDao implements Dao
{
    private final Integer m_currentYear;
    private final Integer m_studentId;
    private final Integer m_schoolId;
    private final String m_studentName;
    private final String m_studentMobileNumber;
    private final Integer m_studentEntranceYear;
    private final ConstGender m_studentGender;
    private final String m_studentSchoolName;
    private final Integer m_assignMemberId;
    private final Date m_assignDate;
    private final Integer m_operatedBy;
    private final Date m_insertDate;

    private StudentInfoDao(ContainerBuilder builder)
    {
        int currentMonthDay = Utility.toInteger(Utility.getCurrentDate(ConstDatetime.DATE_MONTH_DAY_NONE));
        int currentYear = Utility.toInteger(Utility.getCurrentDate(ConstDatetime.DATE_YEAR));
        if (currentMonthDay < 901)
        {
            currentYear--;
        }
        m_currentYear = currentYear;
        m_studentId = builder.m_studentId;
        m_schoolId = builder.m_schoolId;
        m_studentName = builder.m_studentName;
        m_studentMobileNumber = builder.m_studentMobileNumber;
        m_studentEntranceYear = builder.m_studentEntranceYear;
        m_studentGender = builder.m_studentGender;
        m_studentSchoolName = builder.m_studentSchoolName;
        m_assignMemberId = builder.m_assignMemberId;
        m_assignDate = builder.m_assignDate;
        m_operatedBy = builder.m_operatedBy;
        m_insertDate = builder.m_insertDate;
    }

    public Integer studentId()
    {
        return m_studentId;
    }

    public Integer schoolId()
    {
        return m_schoolId;
    }

    public String studentName()
    {
        return m_studentName;
    }

    public String studentMobileNumber()
    {
        return m_studentMobileNumber;
    }

    public String studentCoveredMobile()
    {
        return m_studentMobileNumber.substring(0, 3) + "****" + m_studentMobileNumber.substring(7);
    }

    public Integer studentEntranceYear()
    {
        return m_studentEntranceYear;
    }

    public GradeNames studentGrade()
    {
        int diffYears = m_currentYear - m_studentEntranceYear + 1;
        if (diffYears < 0)
        {
            return GradeNames.CHILD;
        }
        else if (diffYears > 12)
        {
            return GradeNames.UNKNOWN;
        }
        return Utility.getEnum(Utility.toString(diffYears), GradeNames.class);
    }

    public ConstGender studentGender()
    {
        return m_studentGender;
    }

    public String studentSchoolName()
    {
        return m_studentSchoolName;
    }

    public Integer assignMemberId()
    {
        return m_assignMemberId;
    }

    public Date assignDate()
    {
        return m_assignDate;
    }

    @Override
    public Integer createId()
    {
        return m_operatedBy;
    }

    @Override
    public Date createDate()
    {
        return m_insertDate;
    }

    public static class ContainerBuilder implements DaoBuilder<StudentInfoDao>
    {
        private Integer m_studentId;
        private Integer m_schoolId;
        private String m_studentName;
        private String m_studentMobileNumber;
        private Integer m_studentEntranceYear;
        private ConstGender m_studentGender;
        private String m_studentSchoolName;
        private Integer m_assignMemberId;
        private Date m_assignDate;
        private Integer m_operatedBy;
        private Date m_insertDate;

        public ContainerBuilder studentId(Integer studentId)
        {
            m_studentId = studentId;
            return this;
        }

        public ContainerBuilder schoolId(Integer schoolId)
        {
            m_schoolId = schoolId;
            return this;
        }

        public ContainerBuilder studentName(String studentName)
        {
            m_studentName = studentName;
            return this;
        }

        public ContainerBuilder studentMobileNumber(String studentMobileNumber)
        {
            m_studentMobileNumber = studentMobileNumber;
            return this;
        }

        public ContainerBuilder studentEntranceYear(Integer studentEntranceYear)
        {
            m_studentEntranceYear = studentEntranceYear;
            return this;
        }

        public ContainerBuilder studentGender(ConstGender studentGender)
        {
            m_studentGender = studentGender;
            return this;
        }

        public ContainerBuilder studentSchoolName(String studentSchoolName)
        {
            m_studentSchoolName = studentSchoolName;
            return this;
        }

        public ContainerBuilder assignMemberId(Integer assignMemberId)
        {
            m_assignMemberId = assignMemberId;
            return this;
        }

        public ContainerBuilder assignDate(Date assignDate)
        {
            m_assignDate = assignDate;
            return this;
        }

        public ContainerBuilder operatedBy(Integer operatedBy)
        {
            m_operatedBy = operatedBy;
            return this;
        }

        public ContainerBuilder insertDate(Date insertDate)
        {
            m_insertDate = insertDate;
            return this;
        }

        @Override
        public StudentInfoDao build()
        {
            return new StudentInfoDao(this);
        }
    }

    public enum GradeNames implements Parameters
    {
        CHILD("0", "幼小年级"),

        GRADE_1("1", "小学一年级"),

        GRADE_2("2", "小学二年级"),

        GRADE_3("3", "小学三年级"),

        GRADE_4("4", "小学四年级"),

        GRADE_5("5", "小学五年级"),

        GRADE_6("6", "小学六年级"),

        JUNIOR_GRADE_1("7", "初中一年级"),

        JUNIOR_GRADE_2("8", "初中二年级"),

        JUNIOR_GRADE_3("9", "初中三年级"),

        SENIOR_GRADE_1("10", "高中一年级"),

        SENIOR_GRADE_2("11", "高中二年级"),

        SENIOR_GRADE_3("12", "高中三年级"),

        UNKNOWN("", "高中毕业");

        private String m_val;
        private String m_name;

        private GradeNames(String val, String name)
        {
            m_val = val;
            m_name = name;
        }

        @Override
        public String val()
        {
            return m_val;
        }

        public String gradeName()
        {
            return m_name;
        }

        @Override
        public Parameters unknown()
        {
            return UNKNOWN;
        }
    }
}
