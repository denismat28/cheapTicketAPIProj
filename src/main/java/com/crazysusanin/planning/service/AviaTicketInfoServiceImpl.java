package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.User;
import com.crazysusanin.planning.repository.AviaTicketInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AviaTicketInfoServiceImpl implements AviaTicketInfoService{

    @Autowired
    AviaTicketInfoRepository aviaTicketInfoRepository;

    @Override
    public Collection<AviaTicketInfo> aviaTicketInfos() {
        return aviaTicketInfoRepository.findAll();
    }

    @Override
    public AviaTicketInfo addAviaTicketInfo(AviaTicketInfo aviaTicketInfo) {
        return aviaTicketInfoRepository.save(aviaTicketInfo);
    }

    @Override
    public AviaTicketInfo findAviaTicketInfoById(int id) {
        return aviaTicketInfoRepository.getOne(id);
    }

    @Override
    public List<AviaTicketInfo> getAviaTicketInfoByUser(User user) {
        return aviaTicketInfoRepository.getAviaTicketInfoByUser(user);
    }
}

