package com.aineko.settings.controllers;

import com.aineko.settings.entities.Site;
import com.aineko.settings.repositories.SiteRepository;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class SitesController {

    private SiteRepository siteRepository;
    //private Logger logger = LoggerFactory.getLogger(SitesController.class);


    @Autowired
    public void setSiteRepository(SiteRepository siteRepository){
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/site", method = RequestMethod.GET)
    public List<Site> getSites(@RequestParam(value="name", defaultValue="World") String name) {
        return siteRepository.findAll();
    }

    @RequestMapping(value = "/sites", method = RequestMethod.POST)
    public List<Site> saveSites(@RequestBody List<String> urls) {
        List<Site> sites = new ArrayList<>();
        for (String url : urls) {
            Site site = new Site();
            site.setUrl(url);
            site.setCreatedAt(new Date());
            site.setUpdatedAt(site.getCreatedAt());
            sites.add(site);
        }
        return siteRepository.saveAll(sites);
    }

    @RequestMapping(value = "/site", method = RequestMethod.PUT)
    public Site save(@RequestParam(value = "url") String url){
        Site site = new Site();
        site.setUrl(url);

        site.setCreatedAt(new Date());
        site.setUpdatedAt(site.getCreatedAt());
        return siteRepository.save(site);
    }

    @RequestMapping(value = "/site", method=RequestMethod.DELETE)
    public void delete(@RequestParam(value="id") Long id){
        siteRepository.deleteById(id);
    }

    @RequestMapping(value = "/site", method = RequestMethod.PATCH)
    public Site update(@RequestParam(value = "url") String url, @RequestParam(value = "id") Long id){
        Optional<Site> optionalSite = siteRepository.findById(id);
        if (optionalSite.isPresent()){
            Site site = optionalSite.get();
            site.setUrl(url);
            site.setUpdatedAt(new Date());
            return siteRepository.save(site);
        }
        return null;

    }
}
