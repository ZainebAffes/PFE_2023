package com.csys.access.service;

import com.csys.access.domain.AccessMenu;
import com.csys.access.domain.AccessMenuPK;
import com.csys.access.domain.QAccessMenu;
import com.csys.access.dto.AccessMenuDTO;
import com.csys.access.dto.UserDTO;
import com.csys.access.factory.AccessMenuFactory;
import com.csys.access.repository.AccessMenuRepository;
import com.csys.access.util.WhereClauseBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing AccessMenu.
 */
@Service
@Transactional
public class AccessMenuService {

    private final Logger log = LoggerFactory.getLogger(AccessMenuService.class);

    private final AccessMenuRepository accessMenuRepository;
    private final UserService userService;

    public AccessMenuService(AccessMenuRepository accessMenuRepository, UserService userService) {
        this.accessMenuRepository = accessMenuRepository;
        this.userService = userService;
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuDTO findOne(AccessMenuPK id) {
        log.debug("Request to get AccessMenu: {}", id);
        AccessMenu accessmenu = accessMenuRepository.findOne(id);
        AccessMenuDTO dto = AccessMenuFactory.accessmenuToAccessMenuDTO(accessmenu);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenu findAccessMenu(AccessMenuPK id) {
        log.debug("Request to get AccessMenu: {}", id);
        AccessMenu accessmenu = accessMenuRepository.findOne(id);
        return accessmenu;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<AccessMenuDTO> findAll() {
        log.debug("Request to get All AccessMenus");
        Collection<AccessMenu> result = accessMenuRepository.findAll();
        return AccessMenuFactory.accessmenuToAccessMenuDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessMenuDTO> findByCodeModule(String module) {
        log.debug("Request to get All AccessMenu findByCodeModule");
        UserDTO userDTO = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        String groupeArray[] = userDTO.getGroupe().split(",");
        QAccessMenu qQAccessMenu = QAccessMenu.accessMenu;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .and(qQAccessMenu.accessMenuPK().codeModule.eq(module))
                .and(qQAccessMenu.accessMenuPK().groupe.in(groupeArray))
                .and(qQAccessMenu.visible.eq(Boolean.TRUE));
        List<AccessMenu> result = (List<AccessMenu>) accessMenuRepository.findAll(builder);
        List<AccessMenuDTO> accessMenuDTOs = AccessMenuFactory.accessmenuToAccessMenuDTOs(result);
        List<AccessMenuDTO> resultatDTOs = new ArrayList<>();
        accessMenuDTOs.stream().collect(groupingBy(AccessMenuDTO::getCodeMenu, Collectors.reducing(new AccessMenuDTO(), (a, accessMenuDTO) -> {
            return accessMenuDTO;
        }))).forEach((k, v) -> { 
            resultatDTOs.add(v);
        }); 
        Collections.sort(resultatDTOs, (d1, d2) -> d1.getMenu().getOrderMenu().compareTo(d2.getMenu().getOrderMenu()));
        
        return resultatDTOs;
    }
}
