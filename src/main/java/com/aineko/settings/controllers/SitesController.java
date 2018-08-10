package com.aineko.settings.controllers;


import com.aineko.settings.entities.Site;
import com.aineko.settings.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class SitesController {

    private SiteRepository siteRepository;

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository){
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/site", method = RequestMethod.GET)
    public List<Site> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return siteRepository.findAll();
    }

    @RequestMapping(value = "/site", method = RequestMethod.PUT)
    public Site save(@RequestParam(value = "url") String url){
        Site site = new Site();
        site.setUrl(url);

        site.setCreatedAt(new Date());
        site.setUpdatedAt(site.getCreatedAt());
        return siteRepository.save(site);
    }
}
