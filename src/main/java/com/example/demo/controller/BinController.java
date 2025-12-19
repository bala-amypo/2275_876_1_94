// package com.example.demo.controller;

// import com.example.demo.dto.ApiResponse;
// import com.example.demo.model.Bin;
// import com.example.demo.service.BinService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/bins")
// public class BinController {

//     private final BinService binService;

//     public BinController(BinService binService) {
//         this.binService = binService;
//     }

//     @PostMapping
//     public ApiResponse createBin(@RequestBody Bin bin) {
//         return new ApiResponse(
//                 true,
//                 "Bin created",
//                 binService.createBin(bin)
//         );
//     }

//     @PutMapping("/{id}")
//     public ApiResponse updateBin(@PathVariable Long id,@RequestBody Bin bin) {
//         return new ApiResponse(
//                 true,
//                 "Bin updated",
//                 binService.updateBin(id, bin)
//         );
//     }

//     @GetMapping("/{id}")
//     public ApiResponse getBin(@PathVariable Long id) {
//         return new ApiResponse(
//                 true,
//                 "Bin fetched",
//                 binService.getBinById(id)
//         );
//     }

//     @GetMapping
//     public ApiResponse getAllBins() {
//         return new ApiResponse(
//                 true,
//                 "Bins fetched",
//                 binService.getAllBins()
//         );
//     }

//     @PutMapping("/{id}/deactivate")
//     public ApiResponse deactivateBin(@PathVariable Long id) {
//         binService.deactivateBin(id);
//         return new ApiResponse(true, "Bin deactivated");
//     }
// }










// package com.example.demo.controller;

// import com.example.demo.model.Bin;
// import com.example.demo.service.BinService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/bins")
// public class BinController {

//     private final BinService binService;

//     public BinController(BinService binService) {
//         this.binService = binService;
//     }

//     @PostMapping
//     public Bin create(@RequestBody Bin bin) {
//         return binService.createBin(bin);
//     }

//     @GetMapping
//     public List<Bin> getAll() {
//         return binService.getAllBins();
//     }
// }







package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    @GetMapping
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @GetMapping("/{identifier}")
    public Optional<Bin> getBinByIdentifier(@PathVariable String identifier) {
        return binService.getBinByIdentifier(identifier);
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @PutMapping
    public Bin updateBin(@RequestBody Bin bin) {
        return binService.updateBin(bin);
    }

    @DeleteMapping("/{id}")
    public void deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
    }
}
