package com.maozi.easywork.service;

import com.maozi.easywork.common.utils.ClassUtil;
import com.maozi.easywork.common.utils.POIUtils;
import com.maozi.easywork.entity.MemberConsumptionTrack;
import com.maozi.easywork.entity.MemberConsumptionTrackQuery;
import com.maozi.easywork.entity.UpdateMemberName;
import com.maozi.easywork.mapper.MemberConsumptionTrackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberConsumptionTrackService {

    private static Logger logger  = LoggerFactory.getLogger(POIUtils.class);

    @Autowired
    MemberConsumptionTrackMapper memberConsumptionTrackMapper;

    @Autowired
    TokenService tokenService;

    public Map queryList(MemberConsumptionTrackQuery queryFrom, String token){
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("memberName", queryFrom.getMemberName());
        filterMap.put("storeName", queryFrom.getStoreName());
        filterMap.put("startDate", queryFrom.getScopeDate()[0]);
        filterMap.put("endDate", queryFrom.getScopeDate()[1]);
        filterMap.put("promoters", queryFrom.getPromoters());
        filterMap.put("position", queryFrom.getPageNum() * queryFrom.getPageSize() - queryFrom.getPageSize());
        filterMap.put("offset", queryFrom.getPageSize());

        Boolean isRoot = isRoot4user(token);

        Map<String,Object> resultMap = new HashMap();
        List<MemberConsumptionTrack> dSList = null;

        List<String> markNameList = getMarkedMember();
        List<Object> result = new ArrayList<>();

        if(isRoot){
            dSList = memberConsumptionTrackMapper.queryListWithRoot(filterMap);
        }else{
            dSList = memberConsumptionTrackMapper.queryList(filterMap);
        }

        try {
            for(MemberConsumptionTrack mCT : dSList){
                HashMap addMap = new HashMap();
                HashMap addValMap = new HashMap();
                addMap.put("markFlag", Class.forName("java.lang.Integer"));
                if (!StringUtils.isEmpty(mCT.getMemberName()) &&
                        markNameList.indexOf(mCT.getMemberName()) > -1){
                    addValMap.put("markFlag", 1);
                }else{
                    addValMap.put("markFlag", 0);
                }

                result.add(new ClassUtil().dynamicClass(mCT,addMap,addValMap));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        resultMap.put("list", result);
        resultMap.put("pageNum", queryFrom.getPageNum());
        resultMap.put("pageSize", queryFrom.getPageSize());
        if(isRoot){
            resultMap.put("total", memberConsumptionTrackMapper.total4QueryListWithRoot(filterMap));
        }else{
            resultMap.put("total", memberConsumptionTrackMapper.total4QueryList(filterMap));
        }

       return  resultMap;
    }

    private Boolean isRoot4user(String token) {
        List<String> roles = memberConsumptionTrackMapper.getRoleByUserId(tokenService.getUserId(token));
        if(roles == null){
            return false;
        }
        return roles.indexOf("admin") > -1;
    }

    public String addMemberConsumptionTracks(List<MemberConsumptionTrack> list){
        memberConsumptionTrackMapper.addMemberConsumptionTracks(list);
        return "ok";
    }

    public List<String> getMarkedMember(){
        return memberConsumptionTrackMapper.getMarkedMemberNameList();
    }

    public void updateMarkedMember(List<String> memberName) {
        memberConsumptionTrackMapper.deleteMarkedMember();
        int code = memberName.size() > 0 ? memberConsumptionTrackMapper.addMarkedMember(memberName) : 0;
    }

    public void updateMemberName(UpdateMemberName updateMemberName) {
        memberConsumptionTrackMapper.updateMemberName(updateMemberName);
    }
}
