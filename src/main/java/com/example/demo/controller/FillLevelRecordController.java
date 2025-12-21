package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-level-records")
public class FillLevelRecordController {

    @Autowired
    private FillLevelRecordService recordService;

    @GetMapping("/{binId}")
    public List<FillLevelRecord> getRecentRecords(@PathVariable Long binId,
                                                  @RequestParam(defaultValue = "5") int limit) {
        return recordService.getRecentRecords(binId, limit);
    }

    @PostMapping
    public FillLevelRecord createRecord(@RequestBody FillLevelRecord record) {
        return recordService.createRecord(record);
    }
}
