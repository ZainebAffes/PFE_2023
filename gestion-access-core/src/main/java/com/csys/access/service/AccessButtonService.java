package com.csys.access.service;

import com.csys.access.domain.AccessButton;
import com.csys.access.domain.AccessButtonUserPK;
import com.csys.access.domain.QAccessButton;
import com.csys.access.dto.AccessButtonDTO;
import com.csys.access.dto.UserDTO;
import com.csys.access.factory.AccessButtonFactory;
import com.csys.access.repository.AccessButtonRepository;
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
 * Service Implementation for managing AccessButton.
 */
@Service
@Transactional
public class AccessButtonService {

    private final Logger log = LoggerFactory.getLogger(AccessButtonService.class);

    private final AccessButtonRepository accessButtonRepository;
    private final UserService userService;

    public AccessButtonService(AccessButtonRepository accessButtonRepository, UserService userService) {
        this.accessButtonRepository = accessButtonRepository;
        this.userService = userService;
    }

    @Transactional(
            readOnly = true
    )
    public AccessButtonDTO findOne(AccessButtonUserPK id) {
        log.debug("Request to get AccessButton: {}", id);
        AccessButton accessbutton = accessButtonRepository.findOne(id);
        AccessButtonDTO dto = AccessButtonFactory.accessbuttonToAccessButtonDTO(accessbutton);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public AccessButton findAccessButton(AccessButtonUserPK id) {
        log.debug("Request to get AccessButton: {}", id);
        AccessButton accessbutton = accessButtonRepository.findOne(id);
        return accessbutton;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<AccessButtonDTO> findAll() {
        log.debug("Request to get All AccessButtons");
        Collection<AccessButton> result = accessButtonRepository.findAll();
        return AccessButtonFactory.accessbuttonToAccessButtonDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessButtonDTO> findByCodeMenuAndModule(String menu, String module) {
        log.debug("Request to get All AccessButtonUser findByCodeMenuAndModule");
        UserDTO userDTO = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        String groupeArray[] = userDTO.getGroupe().split(",");
        QAccessButton qQAccessButton = QAccessButton.accessButton;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .and(qQAccessButton.button().codeMenu.eq(menu))
                .and(qQAccessButton.accessButtonPK().codeModule.eq(module))
                .and(qQAccessButton.accessButtonPK().groupe.in(groupeArray))
                .and(qQAccessButton.visible.eq(Boolean.TRUE));
        List<AccessButton> result = (List<AccessButton>) accessButtonRepository.findAll(builder);
        List<AccessButtonDTO> accessButtonDTOs = AccessButtonFactory.accessbuttonToAccessButtonDTOs(result);
        List<AccessButtonDTO> resultatDTOs = new ArrayList<>();
        accessButtonDTOs.stream().collect(groupingBy(AccessButtonDTO::getCodeButton, Collectors.reducing(new AccessButtonDTO(), (a, accessMenuDTO) -> {
            return accessMenuDTO;
        }))).forEach((k, v) -> {
            resultatDTOs.add(v);
        });
        Collections.sort(resultatDTOs, (d1, d2) -> d1.getCodeButton().compareTo(d2.getCodeButton()));
        return resultatDTOs;
    }
}
