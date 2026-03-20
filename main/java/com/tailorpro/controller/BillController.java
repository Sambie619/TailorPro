package com.tailorpro.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tailorpro.dto.ApiResponse;
import com.tailorpro.dto.BillDTO;
import com.tailorpro.service.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping
    public BillDTO generateBill(@RequestBody BillDTO dto) {
        return billService.generateBill(dto);
    }

    @PutMapping("/{id}/pay")
    public BillDTO updatePayment(@PathVariable Long id,
                                 @RequestParam Double amount) {
        return billService.updatePayment(id, amount);
    }
    
    @GetMapping("/order/{orderId}")
    public ApiResponse<BillDTO> getByOrder(@PathVariable Long orderId) {
        return new ApiResponse<>(
                "success",
                "Bill fetched",
                billService.getBillByOrder(orderId)
        );
    }
}
