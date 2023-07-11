package com.demo.toolrental.repository;

import com.demo.toolrental.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository to manage tool data
 * 
 * @author Andrew
 */

public interface ToolRepository extends JpaRepository<Tool, Long> {

    Tool findByToolCode(String toolCode);

    List<Tool> findByBrand(String brand);

}