package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createZone(@RequestBody Zone zone) {
        Zone created = zoneService.createZone(zone);
        return ResponseEntity.ok(new ApiResponse(true, "Zone created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        Zone updated = zoneService.updateZone(id, zone);
        return ResponseEntity.ok(new ApiResponse(true, "Zone updated successfully", updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getZoneById(@PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Zone fetched successfully", zone));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllZones() {
        List<Zone> zones = zoneService.getAllZones();
        return ResponseEntity.ok(new ApiResponse(true, "All zones fetched successfully", zones));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse> deactivateZone(@PathVariable Long id) {
        zoneService.deactivateZone(id);
        return ResponseEntity.ok(new ApiResponse(true, "Zone deactivated successfully", null));
    }
}
