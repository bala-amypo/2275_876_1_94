package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    private final FillLevelRecordService recordService;

    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ApiResponse createRecord(@RequestBody FillLevelRecord record) {
        return new ApiResponse(
                true,
                "Record created",
                recordService.createRecord(record)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getRecord(@PathVariable Long id) {
        return new ApiResponse(
                true,
                "Record fetched",
                recordService.getRecordById(id)
        );
    }

    @GetMapping("/bin/{binId}")
    public ApiResponse getRecordsForBin(@PathVariable Long binId) {
        return new ApiResponse(
                true,
                "Records fetched",
                recordService.getRecordsForBin(binId)
        );
    }

    @GetMapping("/bin/{binId}/recent")
    public ApiResponse getRecentRecords(@PathVariable Long binId,@RequestParam int limit) {
        return new ApiResponse(
                true,
                "Recent records fetched",
                recordService.getRecentRecords(binId, limit)
        );
    }
}