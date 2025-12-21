package com.example.demo.service.impl;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Override
    public List<Zone> getAllZones() {
        return new ArrayList<>();
    }

    @Override
    public Zone getZoneById(Long id) {
        return new Zone();
    }

    @Override
    public Zone createZone(Zone zone) {
        return zone;
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
        return zone;
    }

    @Override
    public void deleteZone(Long id) {
        // TODO: Delete zone
    }
}
