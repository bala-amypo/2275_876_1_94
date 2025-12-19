// package com.example.demo.service.impl;

// import com.example.demo.exception.BadRequestException;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.Bin;
// import com.example.demo.model.Zone;
// import com.example.demo.repository.BinRepository;
// import com.example.demo.repository.ZoneRepository;
// import com.example.demo.service.BinService;

// import java.sql.Timestamp;
// import java.time.Instant;
// import java.util.List;

// public class BinServiceImpl implements BinService {

//     private final BinRepository binRepository;
//     private final ZoneRepository zoneRepository;

//     public BinServiceImpl(BinRepository binRepository,
//                           ZoneRepository zoneRepository) {
//         this.binRepository = binRepository;
//         this.zoneRepository = zoneRepository;
//     }

//     @Override
//     public Bin createBin(Bin bin) {

//         if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
//             throw new BadRequestException("Invalid capacity");
//         }

//         Zone zone = zoneRepository.findById(bin.getZone().getId()).orElseThrow(() ->new ResourceNotFoundException("Zone not found"));

//         bin.setZone(zone);
//         bin.setActive(true);
//         bin.setCreatedAt(Timestamp.from(Instant.now()));
//         bin.setUpdatedAt(Timestamp.from(Instant.now()));

//         return binRepository.save(bin);
//     }

//     @Override
//     public Bin updateBin(Long id, Bin updatedBin) {

//         Bin existing = getBinById(id);

//         if (updatedBin.getCapacityLiters() != null && updatedBin.getCapacityLiters() <= 0) {
//             throw new BadRequestException("capacity");
//         }

//         existing.setIdentifier(updatedBin.getIdentifier());
//         existing.setLocationDescription(updatedBin.getLocationDescription());
//         existing.setLatitude(updatedBin.getLatitude());
//         existing.setLongitude(updatedBin.getLongitude());
//         existing.setCapacityLiters(updatedBin.getCapacityLiters());
//         existing.setUpdatedAt(Timestamp.from(Instant.now()));

//         return binRepository.save(existing);
//     }

//     @Override
//     public Bin getBinById(Long id) {
//         return binRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Bin not found"));
//     }

//     @Override
//     public List<Bin> getAllBins() {
//         return binRepository.findAll();
//     }

//     @Override
//     public void deactivateBin(Long id) {
//         Bin bin = getBinById(id);
//         bin.setActive(false);
//         binRepository.save(bin);
//     }
// }









// package com.example.demo.service.impl;

// import com.example.demo.model.Bin;
// import com.example.demo.repository.BinRepository;
// import com.example.demo.service.BinService;
// import org.springframework.stereotype.Service;

// import java.sql.Timestamp;
// import java.util.List;

// @Service   
// public class BinServiceImpl implements BinService {

//     private final BinRepository binRepository;

//     public BinServiceImpl(BinRepository binRepository) {
//         this.binRepository = binRepository;
//     }

//     @Override
//     public Bin createBin(Bin bin) {
//         Timestamp now = new Timestamp(System.currentTimeMillis());
//         bin.setCreatedAt(now);
//         bin.setUpdatedAt(now);
//         return binRepository.save(bin);
//     }

//     @Override
//     public Bin updateBin(Long id, Bin bin) {
//         Bin existing = binRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Bin not found"));

//         existing.setName(bin.getName());
//         existing.setCapacity(bin.getCapacity());
//         existing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

//         return binRepository.save(existing);
//     }

//     @Override
//     public Bin getBinById(Long id) {
//         return binRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Bin not found"));
//     }

//     @Override
//     public List<Bin> getAllBins() {
//         return binRepository.findAll();
//     }

//     @Override
//     public void deleteBin(Long id) {
//         binRepository.deleteById(id);
//     }
// }









package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Optional<Bin> getBinByIdentifier(String identifier) {
        return binRepository.findByIdentifier(identifier);
    }

    @Override
    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public void deleteBin(Long id) {
        binRepository.deleteById(id);
    }
}
