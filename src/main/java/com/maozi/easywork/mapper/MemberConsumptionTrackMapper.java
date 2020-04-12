package com.maozi.easywork.mapper;

import com.maozi.easywork.entity.MemberConsumptionTrack;
import com.maozi.easywork.entity.UpdateMemberName;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberConsumptionTrackMapper {

    List<MemberConsumptionTrack> queryList(Map filterMap);

    int addMemberConsumptionTracks(List<MemberConsumptionTrack> memberConsumptionTrackList);

    int total4QueryList(Map<String, Object> filterMap);

    List<String> getMarkedMemberNameList();
    
    void deleteMarkedMember();

    int addMarkedMember(List<String> memberName);

    void updateMemberName(UpdateMemberName updateMemberName);
}
