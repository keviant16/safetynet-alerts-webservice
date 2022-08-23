package com.simplon.safetynetalertswebservice.controller;

import com.simplon.safetynetalertswebservice.model.response.PersonInfo;
import com.simplon.safetynetalertswebservice.model.response.PersonsByStation;
import com.simplon.safetynetalertswebservice.service.PersonService;
import com.simplon.safetynetalertswebservice.service.UrlService;
import com.simplon.safetynetalertswebservice.service.impl.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping(value = "/communityEmail")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public ResponseEntity<List<String>> readCommunityEmail(@RequestParam("city") String city) {
        return new ResponseEntity<List<String>>(urlService.readCommunityEmail(city), HttpStatus.OK);
    }

    @GetMapping(value = "/personInfo")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public ResponseEntity<List<PersonInfo>> readPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return new ResponseEntity<List<PersonInfo>>(urlService.readPersonInfo(firstName, lastName), HttpStatus.OK);
    }
}
