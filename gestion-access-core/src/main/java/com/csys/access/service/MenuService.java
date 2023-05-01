package com.csys.access.service;

import com.csys.access.domain.Menu;
import com.csys.access.domain.MenuPK;
import com.csys.access.dto.MenuDTO;
import com.csys.access.factory.MenuFactory;
import com.csys.access.repository.MenuRepository;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class MenuService {
  private final Logger log = LoggerFactory.getLogger(MenuService.class);

  private final MenuRepository menuRepository;

  public MenuService(MenuRepository menuRepository) {
    this.menuRepository=menuRepository;
  }

  @Transactional(
      readOnly = true
  )
  public MenuDTO findOne(MenuPK id) {
    log.debug("Request to get Menu: {}",id);
    Menu menu= menuRepository.findOne(id);
    MenuDTO dto = MenuFactory.menuToMenuDTO(menu);
    return dto;
  }


  @Transactional(
      readOnly = true
  )
  public Menu findMenu(MenuPK id) {
    log.debug("Request to get Menu: {}",id);
    Menu menu= menuRepository.findOne(id);
    return menu;
  }

  @Transactional(
      readOnly = true
  )
  public Collection<MenuDTO> findAll() {
    log.debug("Request to get All Menus");
    Collection<Menu> result= menuRepository.findAll();
    return MenuFactory.menuToMenuDTOs(result);
  }

}

