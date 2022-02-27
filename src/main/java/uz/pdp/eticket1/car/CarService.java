package uz.pdp.eticket1.car;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.user.UserBase;
import uz.pdp.eticket1.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements BaseService<CarRequestDTO, Car> {

    private final CarRepository carRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Override
    public String add(CarRequestDTO carRequestDTO) {
        String adminId = carRequestDTO.getAdminId();
        UserBase userBase = userService.getUserBase(adminId);
        Car car = modelMapper.map(carRequestDTO, Car.class);
        car.setUpdateBy(userBase);
        return carRepository.save(car).getId();
    }

    @Override
    public Car get(String id) {
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Car is not found");
    }

    @Override
    public List<Car> getActiveList() {
        return carRepository.findAllByActive(true);
    }

    @Override
    public List<Car> getList() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getList(String str) {
        return null;
    }

}
