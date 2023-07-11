package com.demo.toolrental.controller;

import java.util.ArrayList;
import java.util.List;

import com.demo.toolrental.domain.*;
import com.demo.toolrental.repository.*;
import com.demo.toolrental.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for tool rental
 * 
 * @author Andrew
 */

@RestController
@RequestMapping("/api/toolrental")
public class ToolRentalController {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    @GetMapping("/tools")
    public ResponseEntity<List<Tool>> getAllTools(@RequestParam(required = false) String brand) {
        try {
            List<Tool> tools = new ArrayList<Tool>();

            if (brand == null){
                tools.addAll(toolRepository.findAll());
            }else{
                tools.addAll(toolRepository.findByBrand(brand));
            }

            if (tools.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tools, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<RentalAgreement> checkoutTool(@RequestBody Checkout checkout) {
        try {
            RentalAgreement agreement = checkoutService.checkoutTool(checkout);
            return new ResponseEntity<>(agreement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/agreements")
    public ResponseEntity<List<RentalAgreement>> getAllRentalAgreements() {
        try {
            List<RentalAgreement> rentalAgreements = rentalAgreementRepository.findAll();

            if (rentalAgreements.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(rentalAgreements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
