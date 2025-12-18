package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ApiResponse createZone(@RequestBody Zone zone) {
        return new ApiResponse(
                true,
                "Zone created",
                zoneService.createZone(zone)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateZone(
            @PathVariable Long id,
            @RequestBody Zone zone) {
        return new ApiResponse(
                true,
                "Zone updated",
                zoneService.updateZone(id, zone)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getZone(@PathVariable Long id) {
        return new ApiResponse(
                true,
                "Zone fetched",
                zoneService.getZoneById(id)
        );
    }

    @GetMapping
    public ApiResponse getAllZones() {
        return new ApiResponse(
                true,
                "Zones fetched",
                zoneService.getAllZones()
        );
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivateZone(
            @PathVariable Long id) {
        zoneService.deactivateZone(id);
        return new ApiResponse(true, "Zone deactivated");
    }
}
