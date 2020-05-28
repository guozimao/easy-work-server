package com.maozi.easywork.controller;

import com.maozi.easywork.common.annotations.UserLoginToken;
import com.maozi.easywork.common.utils.POIUtils;
import com.maozi.easywork.entity.MemberConsumptionTrack;
import com.maozi.easywork.entity.ResposeEntity;
import com.maozi.easywork.service.MemberConsumptionTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/file")
public class FileController {
    @Autowired
    MemberConsumptionTrackService memberConsumptionTrackService;

    @UserLoginToken
    @RequestMapping("/import/excel")
    @ResponseBody
    public Object save(@RequestParam("file") MultipartFile[] multipartfiles, String productId) throws IOException {
       try{
           List<String[]> data = POIUtils.readExcel(multipartfiles[0]);
           List<MemberConsumptionTrack> list = new ArrayList<>();
           for (int i = 1; i < data.size(); i++){
               if(data.get(i).length > 3 && !StringUtils.isEmpty(data.get(i)[3])){
                   MemberConsumptionTrack mCT = new MemberConsumptionTrack();
                   mCT.setTaskNo(StringUtils.isEmpty(data.get(i)[0]) ? "" : data.get(i)[0]);
                   mCT.setOrderDate(StringUtils.isEmpty(data.get(i)[1]) ? "" : data.get(i)[1]);
                   mCT.setStoreName(StringUtils.isEmpty(data.get(i)[2]) ? "" : data.get(i)[2]);
                   mCT.setMemberName(StringUtils.isEmpty(data.get(i)[3]) ? "" : data.get(i)[3]);
                   mCT.setActualFee(new BigDecimal(StringUtils.isEmpty(data.get(i)[4]) ? "0" : data.get(i)[4]));
                   mCT.setTotal(StringUtils.isEmpty(data.get(i)[5]) ? 0 : Integer.valueOf(data.get(i)[5]));
                   mCT.setNote(StringUtils.isEmpty(data.get(i)[7]) ? "" : data.get(i)[7]);
                   mCT.setShouldFee(new BigDecimal(StringUtils.isEmpty(data.get(i)[8]) ? "0" : data.get(i)[8]));
                   mCT.setCommission(new BigDecimal((StringUtils.isEmpty(data.get(i)[9]) || data.get(i)[9].equals("/")) ? "0" : data.get(i)[9]));
                   mCT.setPromoters(StringUtils.isEmpty(data.get(i)[10]) ? "" : data.get(i)[10]);
                   if(data.get(i).length > 11) {
                       mCT.setRegistrant(StringUtils.isEmpty(data.get(i)[11]) ? "" : data.get(i)[11]);
                   }
                   list.add(mCT);
               }
           }
           memberConsumptionTrackService.addMemberConsumptionTracks(list);
       }catch (Exception e){
            ResposeEntity resposeEntity = new ResposeEntity("");
            resposeEntity.setCode("-1");
            resposeEntity.setMessage(e.getMessage());
           return resposeEntity;
       }
       return new ResposeEntity("");
    }
}
