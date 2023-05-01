package com.csys.access.web.rest;

import com.csys.access.dto.AccessButtonDTO;
import com.csys.access.service.AccessButtonUserService;
import java.lang.String;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing AccessButtonUser.
 */
@RestController
@RequestMapping("/api")
public class AccessButtonUserResource {

    private static final String ENTITY_NAME = "accessbuttonuser";

    private final AccessButtonUserService accessButtonUserService;

    private final Logger log = LoggerFactory.getLogger(AccessButtonUserService.class);

    public AccessButtonUserResource(AccessButtonUserService accessButtonUserService) {
        this.accessButtonUserService = accessButtonUserService;
    }

  
    @GetMapping("/access-button-user")
    public Collection<AccessButtonDTO> getAllAccessButtonUsers() {
        log.debug("Request to get all  AccessButtonUsers : {}");
        return accessButtonUserService.findAll();
    }

    @GetMapping("/access-button-user/findByCodeMenuAndModule")
    public Collection<AccessButtonDTO> findAccessUserBouton(@RequestParam(required = false) String module, @RequestParam(required = false) String menu) {
        log.debug("Request to get all  AccessMenuUsers findByCodeMenuAndModule: {}");
        return accessButtonUserService.findAccessUserBouton(menu, module);
    }

}
