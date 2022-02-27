package uz.pdp.eticket1.station;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.distance.Distance;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;
import uz.pdp.eticket1.user.UserBase;
import uz.pdp.eticket1.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationService implements BaseService<StationRequestDTO, Station> {

    private final StationRepository stationRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public String add(StationRequestDTO stationRequestDTO) {
        try {
            String adminId = stationRequestDTO.getAdminId();
            UserBase userBase = userService.getUserBase(adminId);
            Station station = modelMapper.map(stationRequestDTO, Station.class);
            station.setUpdateBy(userBase);
            return stationRepository.save(station).getId();
        } catch (Exception e) {
            throw new UniqueException(stationRequestDTO.getName() + " already exists");
        }
    }

    @Override
    public Station get(String id) {
        Optional<Station> optional = stationRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Station is not found");
    }

    @Override
    public List<Station> getActiveList() {
        return null;
    }

    @Override
    public List<Station> getList() {
        return stationRepository.findAll();
    }

    @Override
    public List<Station> getList(String str) {
        return null;
    }

}
