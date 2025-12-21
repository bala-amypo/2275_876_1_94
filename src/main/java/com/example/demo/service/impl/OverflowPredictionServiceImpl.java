@Override
public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {

    Zone zone = zoneRepository.findById(zoneId)
            .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

    return predictionRepository.findLatestPredictionsForZone(zone);
}
