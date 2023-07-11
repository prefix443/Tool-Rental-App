package com.demo.toolrental.repository;

import com.demo.toolrental.domain.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to manage user checkout data
 * 
 * @author Andrew
 */

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}