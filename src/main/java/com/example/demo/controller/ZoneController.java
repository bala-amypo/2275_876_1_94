package com.example.demo.controller;

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

    // Get all zones
    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    // Get zone by ID
    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZone(@PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        if (zone != null) return ResponseEntity.ok(zone);
        else return ResponseEntity.notFound().build();
    }

    // Create zone
    @PostMapping
    public Zone createZone(@RequestBody Zone zone) {
        return zoneService.createZone(zone);
    }

    // Update zone
    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        Zone updated = zoneService.updateZone(id, zone);
        if (updated != null) return ResponseEntity.ok(updated);
        else return ResponseEntity.notFound().build();
    }

    // Delete zone
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable Long id) {
        boolean deleted = zoneService.deleteZone(id);
        if (deleted) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }
}
