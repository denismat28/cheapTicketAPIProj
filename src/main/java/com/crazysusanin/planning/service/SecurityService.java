package com.crazysusanin.planning.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
