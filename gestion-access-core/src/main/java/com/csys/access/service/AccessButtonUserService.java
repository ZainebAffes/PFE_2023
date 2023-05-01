package com.csys.access.service;

import com.csys.access.domain.AccessButtonUser;
import com.csys.access.domain.Param;
import com.csys.access.domain.QAccessButtonUser;
import com.csys.access.dto.AccessButtonDTO;
import com.csys.access.factory.AccessButtonUserFactory;
import com.csys.access.repository.AccessButtonUserRepository;
import com.csys.access.repository.ParamRepository;
import com.csys.access.util.WhereClauseBuilder;
import java.util.List;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing AccessButtonUser.
 */
@Service
@Transactional
public class AccessButtonUserService {

    private final Logger log = LoggerFactory.getLogger(AccessButtonUserService.class);
    private final ParamRepository ParamRepository;
    private final AccessButtonUserRepository accessButtonUserRepository;
    private final AccessButtonService accessButtonService;

    public AccessButtonUserService(ParamRepository ParamRepository, AccessButtonUserRepository accessButtonUserRepository, AccessButtonService accessButtonService) {
        this.ParamRepository = ParamRepository;
        this.accessButtonUserRepository = accessButtonUserRepository;
        this.accessButtonService = accessButtonService;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessButtonDTO> findAll() {
        log.debug("Request to get All AccessButtonUsers");
        List<AccessButtonUser> result = accessButtonUserRepository.findAll();
        return AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessButtonDTO> findByCodeMenuAndModule(String menu, String module) {
        log.debug("Request to get All AccessButtonUser findByCodeMenuAndModule");
        QAccessButtonUser qQAccessButtonUser = QAccessButtonUser.accessButtonUser;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .and(qQAccessButtonUser.button().codeMenu.eq(menu))
                .and(qQAccessButtonUser.accessButtonUserPK().codeModule.eq(module))
                .and(qQAccessButtonUser.accessButtonUserPK().user.eq(SecurityContextHolder.getContext().getAuthentication().getName()))
                .and(qQAccessButtonUser.visible.eq(Boolean.TRUE));
        List<AccessButtonUser> result = (List<AccessButtonUser>) accessButtonUserRepository.findAll(builder);
        return AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessButtonDTO> findAccessUserBouton(String menu, String module) {
        Param paramVersionSec = ParamRepository.findOne("new_version_securite");
        List<AccessButtonDTO> accessButton;
        if (paramVersionSec != null && paramVersionSec.getValeur().equalsIgnoreCase("true")) {
            accessButton = findByCodeMenuAndModule(menu, module);
        } else {
            accessButton = accessButtonService.findByCodeMenuAndModule(menu, module);
        }
        return accessButton;
    }
}
