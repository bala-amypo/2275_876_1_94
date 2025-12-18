// package com.example.demo.service;

// import com.example.demo.model.Bin;

// import java.util.List;

// public interface BinService {

//     Bin createBin(Bin bin);

//     Bin updateBin(Long id, Bin bin);

//     Bin getBinById(Long id);

//     List<Bin> getAllBins();

//     void deactivateBin(Long id);
// }









// package com.example.demo.service;

// import com.example.demo.model.Bin;
// import java.util.List;

// public interface BinService {

//     Bin createBin(Bin bin);

//     Bin updateBin(Long id, Bin bin);

//     Bin getBinById(Long id);

//     List<Bin> getAllBins();

//     void deleteBin(Long id);
// }





// package com.example.demo.service;

// import com.example.demo.model.Bin;
// import java.util.List;

// public interface BinService {
//     List<Bin> getAllBins();
//     Bin getBinById(Long id);
//     Bin createBin(Bin bin);
//     Bin updateBin(Long id, Bin bin);
//     void deleteBin(Long id);
// }





package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;
import java.util.Optional;

public interface BinService {

    List<Bin> getAllBins();

    Optional<Bin> getBinByIdentifier(String identifier);

    Bin createBin(Bin bin);

    Bin updateBin(Bin bin);

    void deleteBin(Long id);
}
