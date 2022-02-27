package uz.pdp.eticket1.direction;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.distance.Distance;
import uz.pdp.eticket1.distance.DistanceRepository;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.station.DirectionStation;
import uz.pdp.eticket1.station.StationRequestDTO;
import uz.pdp.eticket1.user.UserBase;
import uz.pdp.eticket1.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectionService implements BaseService<DirectionRequestDTO, Direction> {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final DirectionRepository directionRepository;
    private final DistanceRepository distanceRepository;

    @Override
    public String add(DirectionRequestDTO directionRequestDTO) {
        List<DirectionStation> list = new ArrayList<>();
        double sum = 0;
        List<StationRequestDTO> stations = directionRequestDTO.getStations();
        List<Distance> distances = distanceRepository.findAll();
        for (int i = 0; i < stations.size() - 1; i++) {
            for (int j = 0; j < distances.size(); j++) {
                String station1 = distances.get(j).getFromStationId();
                String station2 = distances.get(j).getToStationId();
                if ((stations.get(i).getName().equals(station1) && stations.get(i + 1).getName().equals(station2)) ||
                        (stations.get(i).getName().equals(station2) && stations.get(i + 1).getName().equals(station1))) {
                    sum += distances.get(j).getKm();
                    continue;
                }
                if (j == distances.size() - 1)
                    throw new NotFoundException(stations.get(i).getName() + " and " + stations.get(i + 1).getName() +
                            "are not in distance collection");
            }
        }
        String adminId = directionRequestDTO.getAdminId();
        UserBase userBase = userService.getUserBase(adminId);
        Direction direction = modelMapper.map(directionRequestDTO, Direction.class);
        direction.setUpdateBy(userBase);
        direction.setStations(list);
        return directionRepository.save(direction).getId();
    }

    @Override
    public Direction get(String id) {
        Optional<Direction> optional = directionRepository.findById(id);
        if (optional.isPresent())
           return optional.get();
        throw new NotFoundException("Direction is not found");
    }

    @Override
    public List<Direction> getActiveList() {
        return null;
    }

    @Override
    public List<Direction> getList() {
        return directionRepository.findAll();
    }

    @Override
    public List<Direction> getList(String str) {
        return null;
    }

}
