package com.csys.access.web.rest;

import com.csys.access.dto.ModuleDTO;
import com.csys.access.service.ModuleService;
import com.csys.access.util.RestPreconditions;
import java.lang.String;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Module.
 */
@RestController
@RequestMapping("/api")
public class ModuleResource {
  private static final String ENTITY_NAME = "module";

  private final ModuleService moduleService;

  private final Logger log = LoggerFactory.getLogger(ModuleService.class);

  public ModuleResource(ModuleService moduleService) {
    this.moduleService=moduleService;
  }

  @GetMapping("/modules/{id}")
  public ResponseEntity<ModuleDTO> getModule(@PathVariable String id) {
    log.debug("Request to get Module: {}",id);
    ModuleDTO dto = moduleService.findOne(id);
    RestPreconditions.checkFound(dto, "module.NotFound");
    return ResponseEntity.ok().body(dto);
  }


  @GetMapping("/modules")
  public Collection<ModuleDTO> getAllModules() {
    log.debug("Request to get all  Modules : {}");
    return moduleService.findAll();
  }

}

