package com.mall.exception;

public enum AttendanceStatusEnum {
    NORMAL("正常",1),
    LATE("迟到",2),
    LEAVE_EARLY("早退",3),
    MISSING_HALF_DAY("旷工0.5天",4),
    ABSENTEEISM("旷工1天",5),

    WORK("上班",6),
    REST("休息",7);

    /**
     * 考勤状态信息
     */
    private String name;

    /**
     * 考勤状态码
     */
    private Integer status;

    AttendanceStatusEnum(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
