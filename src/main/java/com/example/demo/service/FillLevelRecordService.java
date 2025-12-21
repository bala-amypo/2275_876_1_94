import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository repository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int n) {
        Bin bin = new Bin();
        bin.setId(binId); // assuming you only need the ID
        return repository.findTopNByBinOrderByGeneratedAtDesc(bin, PageRequest.of(0, n));
    }
}
