package com.csys.access.factory;

import com.csys.access.domain.Module;
import com.csys.access.dto.ModuleDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModuleFactory {
  public static ModuleDTO moduleToModuleDTO(Module module) {
    ModuleDTO moduleDTO=new ModuleDTO();
    moduleDTO.setNumModule(module.getNumModule());
    moduleDTO.setDesModule(module.getDesModule());
    moduleDTO.setChemin(module.getChemin());
    moduleDTO.setAfficher(module.getAfficher());
    moduleDTO.setImage(module.getImage());
    moduleDTO.setNomImage(module.getNomImage());
    moduleDTO.setVersionDatabase(module.getVersionDatabase());
    moduleDTO.setVersionModule(module.getVersionModule());
//    moduleDTO.setDesignationSec(module.getDesignationSec());
    return moduleDTO;
  }

  public static Module moduleDTOToModule(ModuleDTO moduleDTO) {
    Module module=new Module();
    module.setNumModule(moduleDTO.getNumModule());
    module.setDesModule(moduleDTO.getDesModule());
    module.setChemin(moduleDTO.getChemin());
    module.setAfficher(moduleDTO.getAfficher());
    module.setImage(moduleDTO.getImage());
    module.setNomImage(moduleDTO.getNomImage());
    module.setVersionDatabase(moduleDTO.getVersionDatabase());
    module.setVersionModule(moduleDTO.getVersionModule());
    return module;
  }

  public static Collection<ModuleDTO> moduleToModuleDTOs(Collection<Module> modules) {
    List<ModuleDTO> modulesDTO=new ArrayList<>();
    modules.forEach(x -> {
      modulesDTO.add(moduleToModuleDTO(x));
    } );
    return modulesDTO;
  }
}

