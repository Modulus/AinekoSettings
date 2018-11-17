package com.aineko.settings.controllers;


import com.aineko.settings.entities.Site;
import com.aineko.settings.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class SitesController {

    private SiteRepository siteRepository;

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository){
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/site", method = RequestMethod.GET)
    public List<Site> getSites(@RequestParam(value="name", defaultValue="World") String name) {
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
