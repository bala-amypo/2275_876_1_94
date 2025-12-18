package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    @PostMapping
    public ApiResponse createBin(@RequestBody Bin bin) {
        return new ApiResponse(
                true,
                "Bin created",
                binService.createBin(bin)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateBin(@PathVariable Long id,@RequestBody Bin bin) {
        return new ApiResponse(
                true,
                "Bin updated",
                binService.updateBin(id, bin)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getBin(@PathVariable Long id) {
        return new ApiResponse(
                true,
                "Bin fetched",
                binService.getBinById(id)
        );
    }

    @GetMapping
    public ApiResponse getAllBins() {
        return new ApiResponse(
                true,
                "Bins fetched",
                binService.getAllBins()
        );
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivateBin(@PathVariable Long id) {
        binService.deactivateBin(id);
        return new ApiResponse(true, "Bin deactivated");
    }
}