package uz.pdp.eticket1.station;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.distance.Distance;
import uz.pdp.eticket1.distance.DistanceResponseDTO;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;
import uz.pdp.eticket1.user.AdminResponseDTO;
import uz.pdp.eticket1.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationService implements BaseService<StationRequestDTO, StationResponseDTO> {

    private final StationRepository stationRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public String add(StationRequestDTO stationRequestDTO) {
        try {
            AdminResponseDTO adminResponse = userService.getAdminResponse(stationRequestDTO.getAdminId());
            System.out.println(adminResponse);
            Station station = modelMapper.map(stationRequestDTO, Station.class);
            station.setUpdateBy(adminResponse);
            return stationRepository.save(station).getId();
        } catch (Exception e) {
            throw new UniqueException(stationRequestDTO.getName() + " already exists");
        }
    }

    @Override
    public StationResponseDTO get(String id) {
        Optional<Station> optional = stationRepository.findById(id);
        if (optional.isPresent()) {
            Station station = optional.get();
            return modelMapper.map(station, StationResponseDTO.class);
        }
        throw new NotFoundException("Station is not found");
    }

    @Override
    public List<StationResponseDTO> getList() {
        List<StationResponseDTO> list = new ArrayList<>();
        for (Station station : stationRepository.findAll()) {
            StationResponseDTO stationResponseDTO = modelMapper.map(station, StationResponseDTO.class);
            list.add(stationResponseDTO);
        }
        return list;
    }

    @Override
    public List<StationResponseDTO> getList(String str) {
        return null;
    }

    @Override
    public StationResponseDTO get(String str1, String str2) {
        return null;
    }
}
