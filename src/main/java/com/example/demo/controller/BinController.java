package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    // Create a new Bin
    @PostMapping
    public ResponseEntity<ApiResponse> createBin(@RequestBody Bin bin) {
        Bin createdBin = binService.createBin(bin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Bin created successfully", createdBin));
    }

    // Update an existing Bin
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        Bin updatedBin = binService.updateBin(id, bin);
        return ResponseEntity.ok(new ApiResponse(true, "Bin updated successfully", updatedBin));
    }

    // Get a Bin by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBinById(@PathVariable Long id) {
        Bin bin = binService.getBinById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Bin retrieved successfully", bin));
    }

    // Get all Bins
    @GetMapping
    public ResponseEntity<ApiResponse> getAllBins() {
        List<Bin> bins = binService.getAllBins();
        return ResponseEntity.ok(new ApiResponse(true, "All bins retrieved successfully", bins));
    }

    // Deactivate a Bin
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse> deactivateBin(@PathVariable Long id) {
        binService.deactivateBin(id);
        return ResponseEntity.ok(new ApiResponse(true, "Bin deactivated successfully", null));
    }
}
