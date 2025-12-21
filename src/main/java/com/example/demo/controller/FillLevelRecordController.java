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

    private final FillLevelRecordService fillLevelRecordService;

    public FillLevelRecordController(FillLevelRecordService fillLevelRecordService) {
        this.fillLevelRecordService = fillLevelRecordService;
    }

    // POST /api/fill-records - Create a new fill level record
    @PostMapping
    public ResponseEntity<ApiResponse> createRecord(@RequestBody FillLevelRecord record) {
        FillLevelRecord created = fillLevelRecordService.createRecord(record);
        return ResponseEntity.ok(new ApiResponse(true, "Fill record created successfully", created));
    }

    // GET /api/fill-records/{id} - Get fill record by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRecordById(@PathVariable Long id) {
        FillLevelRecord record = fillLevelRecordService.getRecordById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Fill record fetched", record));
    }

    // GET /api/fill-records/bin/{binId} - Get all records for a bin
    @GetMapping("/bin/{binId}")
    public ResponseEntity<ApiResponse> getRecordsForBin(@PathVariable Long binId) {
        List<FillLevelRecord> records = fillLevelRecordService.getRecordsForBin(binId);
        return ResponseEntity.ok(new ApiResponse(true, "Records fetched for bin", records));
    }

    // GET /api/fill-records/bin/{binId}/recent?limit=n - Get recent n records
    @GetMapping("/bin/{binId}/recent")
    public ResponseEntity<ApiResponse> getRecentRecords(@PathVariable Long binId,
                                                        @RequestParam(defaultValue = "5") int limit) {
        List<FillLevelRecord> records = fillLevelRecordService.getRecentRecords(binId, limit);
        return ResponseEntity.ok(new ApiResponse(true, "Recent records fetched", records));
    }
}
