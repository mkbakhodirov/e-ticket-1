package uz.pdp.eticket1.distance;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistanceService implements BaseService<DistanceRequestDTO, DistanceResponseDTO> {

    private final DistanceRepository distanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public String add(DistanceRequestDTO stationRequestDTO) {
        check(stationRequestDTO.getStation1(), stationRequestDTO.getStation2());
        Distance station = modelMapper.map(stationRequestDTO, Distance.class);
        return distanceRepository.save(station).getId();
    }

    @Override
    public DistanceResponseDTO get(String id) {
        Optional<Distance> optional = distanceRepository.findById(id);
        if (optional.isPresent()) {
            Distance distance = optional.get();
            return modelMapper.map(distance, DistanceResponseDTO.class);
        }
        throw new NotFoundException("Distance is not found");
    }

    @Override
    public List<DistanceResponseDTO> getList() {
        List<DistanceResponseDTO> list = new ArrayList<>();
        for (Distance distance : distanceRepository.findAll()) {
            DistanceResponseDTO distanceResponseDTO = modelMapper.map(distance, DistanceResponseDTO.class);
            list.add(distanceResponseDTO);
        }
        return list;
    }

    @Override
    public List<DistanceResponseDTO> getList(String str) {
        return null;
    }

    @Override
    public DistanceResponseDTO get(String str1, String str2) {
        return null;
    }

    private void check(String station1, String station2) {
        for (Distance station : distanceRepository.findAll()) {
            String from = station.getStation1();
            String to = station.getStation2();
            if (station1.equals(from) && station2.equals(to))
                throw new UniqueException(station1 + " and " + station2 + " are already exist");
            if (station2.equals(from) && station1.equals(to))
                throw new UniqueException(station1 + " and " + station2 + " are already exist");
        }
    }
}
