package com.example.demo.controller;

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

    @GetMapping
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBin(@PathVariable Long id) {
        Bin bin = binService.getBinById(id);
        if (bin != null) return ResponseEntity.ok(bin);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        Bin updated = binService.updateBin(id, bin);
        if (updated != null) return ResponseEntity.ok(updated);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateBin(@PathVariable Long id) {
        boolean success = binService.deactivateBin(id);
        if (success) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }
}
