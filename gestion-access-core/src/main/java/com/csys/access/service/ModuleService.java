package com.csys.access.service;

import com.csys.access.domain.Module;
import com.csys.access.dto.ModuleDTO;
import com.csys.access.factory.ModuleFactory;
import com.csys.access.repository.ModuleRepository;
import com.google.common.base.Preconditions;
import java.lang.String;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Module.
 */
@Service
@Transactional
public class ModuleService {
  private final Logger log = LoggerFactory.getLogger(ModuleService.class);

  private final ModuleRepository moduleRepository;

  public ModuleService(ModuleRepository moduleRepository) {
    this.moduleRepository=moduleRepository;
  }

  @Transactional(
      readOnly = true
  )
  public ModuleDTO findOne(String id) {
    log.debug("Request to get Module: {}",id);
    Module module= moduleRepository.findOne(id);
    ModuleDTO dto = ModuleFactory.moduleToModuleDTO(module);
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public Module findModule(String id) {
    log.debug("Request to get Module: {}",id);
    Module module= moduleRepository.findOne(id);
    return module;
  }

 
  @Transactional(
      readOnly = true
  )
  public Collection<ModuleDTO> findAll() {
    log.debug("Request to get All Modules");
    Collection<Module> result= moduleRepository.findAll();
    return ModuleFactory.moduleToModuleDTOs(result);
  }

}

