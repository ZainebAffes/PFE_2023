package com.csys.access.service;

import com.csys.access.domain.AccessMenuUser;
import com.csys.access.domain.AccessMenuUserPK;
import com.csys.access.domain.Param;
import com.csys.access.domain.QAccessMenuUser;
import com.csys.access.dto.AccessMenuDTO;
import com.csys.access.factory.AccessMenuUserFactory;
import com.csys.access.repository.AccessMenuUserRepository;
import com.csys.access.repository.ParamRepository;
import com.csys.access.util.WhereClauseBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing AccessMenuUser.
 */
@Service
@Transactional
public class AccessMenuUserService {

    private final Logger log = LoggerFactory.getLogger(AccessMenuUserService.class);

    private final AccessMenuUserRepository accessMenuUserRepository;
    private final ParamRepository ParamRepository;
    private final AccessMenuService accessMenuService;

    public AccessMenuUserService(AccessMenuUserRepository accessMenuUserRepository, ParamRepository ParamRepository, AccessMenuService accessMenuService) {
        this.accessMenuUserRepository = accessMenuUserRepository;
        this.ParamRepository = ParamRepository;
        this.accessMenuService = accessMenuService;
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuDTO findOne(AccessMenuUserPK id) {
        log.debug("Request to get AccessMenuUser: {}", id);
        AccessMenuUser accessmenuuser = accessMenuUserRepository.findOne(id);
        AccessMenuDTO dto = AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTO(accessmenuuser);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuUser findAccessMenuUser(AccessMenuUserPK id) {
        log.debug("Request to get AccessMenuUser: {}", id);
        AccessMenuUser accessmenuuser = accessMenuUserRepository.findOne(id);
        return accessmenuuser;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<AccessMenuDTO> findAll() {
        log.debug("Request to get All AccessMenuUsers");
        Collection<AccessMenuUser> result = accessMenuUserRepository.findAll();
        return AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<AccessMenuDTO> findByCodeModule(String module) {
        log.debug("Request to get All AccessMenuUsers findByCodeModule");
        QAccessMenuUser qQAccessMenuUser = QAccessMenuUser.accessMenuUser;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .and(qQAccessMenuUser.accessMenuUserPK().codeModule.eq(module))
                .and(qQAccessMenuUser.accessMenuUserPK().user.eq(SecurityContextHolder.getContext().getAuthentication().getName()))
                .and(qQAccessMenuUser.visible.eq(Boolean.TRUE));
        List<AccessMenuUser> result = (List<AccessMenuUser>) accessMenuUserRepository.findAll(builder);
        List<AccessMenuDTO> menusDTO = (List<AccessMenuDTO>)AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTOs(result);
        Collections.sort(menusDTO, (d1, d2) -> d1.getMenu().getOrderMenu().compareTo(d2.getMenu().getOrderMenu()));
        return menusDTO;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<AccessMenuDTO> findAccessMenuUser(String module) {
        Param paramVersionSec = ParamRepository.findOne("new_version_securite");
        log.debug("test version sec {}",paramVersionSec.getValeur());
        Collection<AccessMenuDTO> accessMenu;
        if (paramVersionSec != null && paramVersionSec.getValeur().equalsIgnoreCase("true")) {
            accessMenu = findByCodeModule(module);
        } else {
            accessMenu = accessMenuService.findByCodeModule(module);
        }
        return accessMenu;
    }
}
