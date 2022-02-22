package uz.pdp.eticket1.station;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.UniqueException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService implements BaseService<StationRequestDTO, StationResponseDTO> {

    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;

    @Override
    public String add(StationRequestDTO stationRequestDTO) {
        check(stationRequestDTO.getStation1(), stationRequestDTO.getStation2());
        Station station = modelMapper.map(stationRequestDTO, Station.class);
        return stationRepository.save(station).getId();
    }

    @Override
    public StationResponseDTO get(String id) {
        return null;
    }

    @Override
    public List<StationResponseDTO> getList() {
        return null;
    }

    @Override
    public List<StationResponseDTO> getList(String str) {
        return null;
    }

    @Override
    public StationResponseDTO get(String str1, String str2) {
        return null;
    }

    private void check(String station1, String station2) {
        for (Station station : stationRepository.findAll()) {
            String from = station.getStation1();
            String to = station.getStation2();
            if (station1.equals(from) && station2.equals(to))
                throw new UniqueException(station1 + " and " + station2 " are already exist");
            if (station2.equals(from) && station1.equals(to))
                throw new UniqueException(station1 + " and " + station2 " are already exist");
        }
    }
}
