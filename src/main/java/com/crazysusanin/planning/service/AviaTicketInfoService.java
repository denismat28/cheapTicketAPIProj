package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.AviaTicketInfo;



import java.util.Collection;


public interface AviaTicketInfoService {
    Collection<AviaTicketInfo> aviaTicketInfos();
    AviaTicketInfo addAviaTicketInfo(AviaTicketInfo aviaTicketInfo);
    AviaTicketInfo findAviaTicketInfoById(int id);


}
