package com.simplon.safetynetalertswebservice.service;

import com.simplon.safetynetalertswebservice.model.response.PersonInfo;

import java.util.List;

public interface UrlService {

    List<String> readCommunityEmail(String city);
    List<PersonInfo> readPersonInfo(String firstName, String lastName);
}
