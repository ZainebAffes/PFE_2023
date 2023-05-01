package com.csys.access.web.rest;

import com.csys.access.domain.AccessMenuPK;
import com.csys.access.dto.AccessMenuDTO;
import com.csys.access.service.AccessMenuService;
import com.csys.access.util.RestPreconditions; 
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing AccessMenu.
 */
@RestController
@RequestMapping("/api")
public class AccessMenuResource {
  private static final String ENTITY_NAME = "accessmenu";

  private final AccessMenuService accessmenuService;

  private final Logger log = LoggerFactory.getLogger(AccessMenuService.class);

  public AccessMenuResource(AccessMenuService accessmenuService) {
    this.accessmenuService=accessmenuService;
  }

  @GetMapping("/accessmenus/{id}")
  public ResponseEntity<AccessMenuDTO> getAccessMenu(@PathVariable AccessMenuPK id) {
    log.debug("Request to get AccessMenu: {}",id);
    AccessMenuDTO dto = accessmenuService.findOne(id);
    RestPreconditions.checkFound(dto, "accessmenu.NotFound");
    return ResponseEntity.ok().body(dto);
  }

 
  @GetMapping("/accessmenus")
  public Collection<AccessMenuDTO> getAllAccessMenus() {
    log.debug("Request to get all  AccessMenus : {}");
    return accessmenuService.findAll();
  }
 
}

