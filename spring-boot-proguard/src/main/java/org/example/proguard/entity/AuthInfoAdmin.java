package org.example.proguard.entity;

import java.io.Serializable;
import java.util.Date;

public class AuthInfoAdmin implements Serializable {
    private String adminNo;
    private String adminLoginNo;
    private String adminLoginPwd;
    private String adminStatus;
    private String adminName;
    private String adminMobile;
    private String adminMail;
    private String orgId;
    private String deptId;
    private Date createDate;
    private String createUser;
    private String isSupper;
    private Date dataStamp;
	private String isTime;

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getAdminLoginNo() {
        return adminLoginNo;
    }

    public void setAdminLoginNo(String adminLoginNo) {
        this.adminLoginNo = adminLoginNo;
    }

    public String getAdminLoginPwd() {
        return adminLoginPwd;
    }

    public void setAdminLoginPwd(String adminLoginPwd) {
        this.adminLoginPwd = adminLoginPwd;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getIsSupper() {
        return isSupper;
    }

    public void setIsSupper(String isSupper) {
        this.isSupper = isSupper;
    }

    public Date getDataStamp() {
        return dataStamp;
    }

    public void setDataStamp(Date dataStamp) {
        this.dataStamp = dataStamp;
    }

    public String getIsTime() {
        return isTime;
    }

    public void setIsTime(String isTime) {
        this.isTime = isTime;
    }
}