package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
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

    // Create a new bin
    @PostMapping
    public ResponseEntity<ApiResponse> createBin(@RequestBody Bin bin) {
        Bin created = binService.createBin(bin);
        return ResponseEntity.ok(new ApiResponse(true, "Bin created successfully", created));
    }

    // Update an existing bin
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        Bin updated = binService.updateBin(id, bin);
        return ResponseEntity.ok(new ApiResponse(true, "Bin updated successfully", updated));
    }

    // Get bin by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBin(@PathVariable Long id) {
        Bin bin = binService.getBinById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Bin fetched successfully", bin));
    }

    // List all bins
    @GetMapping
    public ResponseEntity<ApiResponse> getAllBins() {
        List<Bin> bins = binService.getAllBins();
        return ResponseEntity.ok(new ApiResponse(true, "Bins fetched successfully", bins));
    }

    // Deactivate a bin
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse> deactivateBin(@PathVariable Long id) {
        binService.deactivateBin(id);
        return ResponseEntity.ok(new ApiResponse(true, "Bin deactivated successfully", null));
    }
}
