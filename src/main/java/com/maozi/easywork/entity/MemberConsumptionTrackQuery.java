package com.maozi.easywork.entity;

public class MemberConsumptionTrackQuery {
    private String[] scopeDate;

    private String storeName;

    private String promoters;

    private String memberName;

    private int pageSize;

    private int pageNum;

    public String[] getScopeDate() {
        if (scopeDate == null){
            scopeDate = new String[] {"",""};
        }
        return scopeDate;
    }

    public void setScopeDate(String[] scopeDate) {
        this.scopeDate = scopeDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPromoters() {
        return promoters;
    }

    public void setPromoters(String promoters) {
        this.promoters = promoters;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
