package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.model.AccessTracker;
import com.vedantu.counselling.data.repository.AccessTrackerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AccessTrackerService {

    AccessTrackerRepository accessTrackerRepository;

    @Autowired
    public AccessTrackerService(AccessTrackerRepository accessTrackerRepository) {
        this.accessTrackerRepository = accessTrackerRepository;
    }

    public AccessTracker save(AccessTracker accessTracker){
        return accessTrackerRepository.save(accessTracker);
    }

    public int getAccessCount(){
        Integer accessCount = accessTrackerRepository.getAccessCount();
        return accessCount==null?0:accessCount;
    }

    public void noteNewAccess() {
        AccessTracker accessTracker = new AccessTracker();
        accessTracker.setDate(new Date());
        save(accessTracker);
    }
}
