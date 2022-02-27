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
public class DistanceService implements BaseService<DistanceRequestDTO, Distance> {

    private final DistanceRepository distanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public String add(DistanceRequestDTO stationRequestDTO) {
        check(stationRequestDTO.getStation1(), stationRequestDTO.getStation2());
        Distance station = modelMapper.map(stationRequestDTO, Distance.class);
        return distanceRepository.save(station).getId();
    }

    @Override
    public Distance get(String id) {
        Optional<Distance> optional = distanceRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new NotFoundException("Distance is not found");
    }

    @Override
    public List<Distance> getActiveList() {
        return null;
    }

    @Override
    public List<Distance> getList() {
        return distanceRepository.findAll();
    }

    @Override
    public List<Distance> getList(String str) {
        return null;
    }

    private void check(String station1, String station2) {
        for (Distance station : distanceRepository.findAll()) {
            String from = station.getFromStationId();
            String to = station.getToStationId();
            if (station1.equals(from) && station2.equals(to))
                throw new UniqueException(station1 + " and " + station2 + " are already exist");
            if (station2.equals(from) && station1.equals(to))
                throw new UniqueException(station1 + " and " + station2 + " are already exist");
        }
    }
}
