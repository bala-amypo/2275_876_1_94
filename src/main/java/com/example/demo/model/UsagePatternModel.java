package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    private final FillLevelRecordService recordService;

    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createRecord(@RequestBody FillLevelRecord record) {
        FillLevelRecord created = recordService.createRecord(record);
        return ResponseEntity.ok(new ApiResponse(true, "Fill record created", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRecord(@PathVariable Long id) {
        FillLevelRecord record = recordService.getRecordById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Fill record fetched", record));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<ApiResponse> getRecordsForBin(@PathVariable Long binId) {
        List<FillLevelRecord> records = recordService.getRecordsForBin(binId);
        return ResponseEntity.ok(new ApiResponse(true, "Records fetched", records));
    }

    @GetMapping("/bin/{binId}/recent")
    public ResponseEntity<ApiResponse> getRecentRecords(@PathVariable Long binId, @RequestParam int limit) {
        List<FillLevelRecord> records = recordService.getRecentRecords(binId, limit);
        return ResponseEntity.ok(new ApiResponse(true, "Recent records fetched", records));
    }
}
