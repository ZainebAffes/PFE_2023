package com.csys.access.web.rest;

import com.csys.access.dto.GroupeUserDTO;
import com.csys.access.service.GroupeUserService;
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
 * REST controller for managing GroupeUser.
 */
@RestController
@RequestMapping("/api")
public class GroupeUserResource {
  private static final String ENTITY_NAME = "groupeuser";

  private final GroupeUserService groupeuserService;

  private final Logger log = LoggerFactory.getLogger(GroupeUserService.class);

  public GroupeUserResource(GroupeUserService groupeuserService) {
    this.groupeuserService=groupeuserService;
  }

  @GetMapping("/groupeusers/{id}")
  public ResponseEntity<GroupeUserDTO> getGroupeUser(@PathVariable String id) {
    log.debug("Request to get GroupeUser: {}",id);
    GroupeUserDTO dto = groupeuserService.findOne(id);
    RestPreconditions.checkFound(dto, "groupeuser.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/groupeusers")
  public Collection<GroupeUserDTO> getAllGroupeUsers() {
    log.debug("Request to get all  GroupeUsers : {}");
    return groupeuserService.findAll();
  }

}

