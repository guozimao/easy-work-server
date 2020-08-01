package com.maozi.easywork.controller;

import com.maozi.easywork.common.annotations.UserLoginToken;
import com.maozi.easywork.entity.MemberConsumptionTrackQuery;
import com.maozi.easywork.entity.ResposeEntity;
import com.maozi.easywork.entity.UpdateMemberName;
import com.maozi.easywork.service.MemberConsumptionTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/member/consumption/rack")
public class MemberConsumptionTrackController {
    @Autowired
    private MemberConsumptionTrackService memberConsumptionTrackService;

    @UserLoginToken
    @PostMapping("/list")
    @ResponseBody
    public Object queryList(@RequestBody MemberConsumptionTrackQuery mCTQ,@RequestHeader(name = "token") String token) {
        Map map = memberConsumptionTrackService.queryList(mCTQ,token);
        return new ResposeEntity(map);
    }

    @UserLoginToken
    @PostMapping("/markedMember")
    @ResponseBody
    public Object getMarkedMember() {
        List<String> list = memberConsumptionTrackService.getMarkedMember();
        return new ResposeEntity(list);
    }

    @UserLoginToken
    @PostMapping("/updateMarkedMember")
    @ResponseBody
    public Object updateMarkedMember(@RequestBody List<String> memberName) {
        memberConsumptionTrackService.updateMarkedMember(memberName);
        return new ResposeEntity("success");
    }

    @UserLoginToken
    @PostMapping("/updateMemberName")
    @ResponseBody
    public Object updateMemberName(@RequestBody UpdateMemberName updateMemberName) {
        memberConsumptionTrackService.updateMemberName(updateMemberName);
        return new ResposeEntity("success");
    }
}
