package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.exception.AuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthService {

    private final String password;

    private static Instant lastFailedTime;

    private static final AtomicInteger failedAttempts = new AtomicInteger(1);

    public AuthService(@Value("${uploadService.password}") String key) {
        this.password = key;
    }

    public void authenticate(String password) throws AuthenticationException {
        if(failedAttempts.get() >= 3){
            if(lastFailedTime.plusSeconds(60).isAfter(Instant.now())){
                throw new AuthenticationException("Too many failed attempts. Try after some time!!");
            } else {
                failedAttempts.set(0);
            }
        }

        if(!StringUtils.equals(this.password, password)){
            failedAttempts.incrementAndGet();
            lastFailedTime = Instant.now();
            throw new AuthenticationException("Invalid key!!");
        }
    }
}
