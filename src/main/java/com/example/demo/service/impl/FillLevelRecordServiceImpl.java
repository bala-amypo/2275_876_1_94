package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {

        Bin bin = binRepository.findById(record.getBin().getId()).orElseThrow(() ->new ResourceNotFoundException("Bin not found"));

        if (!bin.getActive()) {
            throw new BadRequestException("Inactive bin");
        }

        if (record.getFillPercentage() < 0 || record.getFillPercentage() > 100) {
            throw new BadRequestException("fillPercentage");
        }

        if (record.getRecordedAt().after(Timestamp.from(Instant.now()))) {
            throw new BadRequestException("Future recordedAt");
        }

        record.setBin(bin);
        return recordRepository.save(record);
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {

        Bin bin = binRepository.findById(binId).orElseThrow(() ->new ResourceNotFoundException("Bin not found"));

        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {

        Bin bin = binRepository.findById(binId).orElseThrow(() ->new ResourceNotFoundException("Bin not found"));

        List<FillLevelRecord> records =recordRepository.findByBinOrderByRecordedAtDesc(bin);

        return records.stream().limit(limit).toList();
    }
}






// package com.example.demo.service.impl;

// import com.example.demo.model.Bin;
// import com.example.demo.repository.BinRepository;
// import com.example.demo.model.FillLevelRecord;
// import com.example.demo.repository.FillLevelRecordRepository;
// import com.example.demo.service.FillLevelRecordService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class FillLevelRecordServiceImpl implements FillLevelRecordService {

//     private final FillLevelRecordRepository fillLevelRecordRepository;
//     private final BinRepository binRepository;

//     public FillLevelRecordServiceImpl(FillLevelRecordRepository fillLevelRecordRepository,
//                                       BinRepository binRepository) {
//         this.fillLevelRecordRepository = fillLevelRecordRepository;
//         this.binRepository = binRepository;
//     }

//     @Override
//     public List<FillLevelRecord> getRecordsForActiveBins() {
//         List<Bin> bins = binRepository.findAll();
//         // Filter only active bins
//         List<Bin> activeBins = bins.stream()
//                                    .filter(Bin::getActive) // make sure getActive() exists
//                                    .toList();

//         // Return records for active bins
//         return fillLevelRecordRepository.findByBinIn(activeBins);
//     }
// }
