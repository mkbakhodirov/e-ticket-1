package uz.pdp.eticket1.train;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.station.Station;
import uz.pdp.eticket1.user.UserBase;
import uz.pdp.eticket1.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainService implements BaseService<TrainRequestDTO, Train> {

    private final TrainRepository trainRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public String add(TrainRequestDTO trainRequestDTO) {
        UserBase userBase = userService.getUserBase(trainRequestDTO.getAdminId());
        Train train = modelMapper.map(trainRequestDTO, Train.class);
        train.setUpdateBy(userBase);
        return trainRepository.save(train).getId();
    }

    @Override
    public Train get(String id) {
        return null;
    }

    @Override
    public List<Train> getActiveList() {
        return null;
    }

    @Override
    public List<Train> getList() {
        return null;
    }

    @Override
    public List<Train> getList(String str) {
        return null;
    }

}
