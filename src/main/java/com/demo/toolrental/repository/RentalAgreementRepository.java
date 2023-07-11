package com.demo.toolrental.repository;

import com.demo.toolrental.domain.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to manage user rental agreement data
 * 
 * @author Andrew
 */

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {
}