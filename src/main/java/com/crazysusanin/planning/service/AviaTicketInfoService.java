package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.User;



import java.util.Collection;
import java.util.List;


public interface AviaTicketInfoService {
    Collection<AviaTicketInfo> aviaTicketInfos();
    AviaTicketInfo addAviaTicketInfo(AviaTicketInfo aviaTicketInfo);
    AviaTicketInfo findAviaTicketInfoById(int id);
    List<AviaTicketInfo> getAviaTicketInfoByUser(User user);

}
