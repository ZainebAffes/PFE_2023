package com.csys.access.web.rest;

import com.csys.access.domain.AccessMenuUserPK;
import com.csys.access.dto.AccessMenuDTO;
import com.csys.access.service.AccessMenuUserService;
import com.csys.access.util.RestPreconditions;
import java.lang.String;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing AccessMenuUser.
 */
@RestController
@RequestMapping("/api")
public class AccessMenuUserResource {

    private static final String ENTITY_NAME = "accessMenuUser";

    private final AccessMenuUserService accessMenuUserService;

    private final Logger log = LoggerFactory.getLogger(AccessMenuUserService.class);

    public AccessMenuUserResource(AccessMenuUserService accessMenuUserService) {
        this.accessMenuUserService = accessMenuUserService;
    }

    @GetMapping("/access-menu-user/{id}")
    public ResponseEntity<AccessMenuDTO> getAccessMenuUser(@PathVariable AccessMenuUserPK id) {
        log.debug("Request to get AccessMenuUser: {}", id);
        AccessMenuDTO dto = accessMenuUserService.findOne(id);
        RestPreconditions.checkFound(dto, "accessmenuuser.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/access-menu-user")
    public Collection<AccessMenuDTO> getAllAccessMenuUsers() {
        log.debug("Request to get all  AccessMenuUsers : {}");
        return accessMenuUserService.findAll();
    }

    @RequestMapping(value = "/access-menu-user/findAccessMenuUser", method = RequestMethod.GET)
    public Collection<AccessMenuDTO> findAccessMenuUser(@RequestParam(required = false) String module) {
        log.debug("Request to get all  AccessMenuUsers findByCodeModule: {}");
        return accessMenuUserService.findAccessMenuUser(module);
    }
}
