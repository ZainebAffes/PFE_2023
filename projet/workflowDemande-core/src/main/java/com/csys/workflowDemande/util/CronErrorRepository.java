/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.workflowDemande.util;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bassatine
 */
public interface CronErrorRepository extends JpaRepository<CronError, Integer> {
        
}
