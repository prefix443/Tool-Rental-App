package com.demo.toolrental.repository;

import com.demo.toolrental.domain.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {
}