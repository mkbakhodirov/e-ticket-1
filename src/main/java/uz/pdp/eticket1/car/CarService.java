package uz.pdp.eticket1.car;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.user.AdminResponseDTO;
import uz.pdp.eticket1.user.User;
import uz.pdp.eticket1.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService implements BaseService<CarRequestDTO, CarResponseDTO> {

    private final CarRepository carRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public String add(CarRequestDTO carRequestDTO) {
        String adminId = carRequestDTO.getAdminId();
        AdminResponseDTO adminResponseDTO = userService.getAdminResponse(adminId);
        Car car = modelMapper.map(carRequestDTO, Car.class);
        System.out.println(car);
        car.setUpdateBy(adminResponseDTO);
        System.out.println(car.getUpdateBy());
        return carRepository.save(car).getId();
    }

    @Override
    public CarResponseDTO get(String id) {
        return null;
    }

    @Override
    public List<CarResponseDTO> getList() {
        return null;
    }

    @Override
    public List<CarResponseDTO> getList(String str) {
        return null;
    }

    @Override
    public CarResponseDTO get(String str1, String str2) {
        return null;
    }
}
