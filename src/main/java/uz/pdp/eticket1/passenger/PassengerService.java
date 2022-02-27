package uz.pdp.eticket1.passenger;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;
import uz.pdp.eticket1.user.User;
import uz.pdp.eticket1.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService implements BaseService<PassengerRequestDTO, Passenger> {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public String add(PassengerRequestDTO passengerRequestDTO) {
        String userId = passengerRequestDTO.getUserId();
        User user = userService.get(userId);
        List<Passenger> passengers = user.getPassengers();
        check(passengerRequestDTO, passengers);
        Passenger passenger = modelMapper.map(passengerRequestDTO, Passenger.class);
        passengers.add(passenger);
        userService.editUser(user);
        return passengerRequestDTO.getDocumentNumber();
    }

    @Override
    public Passenger get(String userId) {
        return null;
    }

    @Override
    public List<Passenger> getList() {
        return null;
    }

    @Override
    public List<Passenger> getList(String userId) {
        return null;
    }

    public Passenger get(String userId, String documentNumber) {
        User user = userService.get(userId);
        for (Passenger passenger : user.getPassengers()) {
            if (passenger.getDocumentNumber().equals(documentNumber))
                return passenger;
        }
        throw new NotFoundException("Passenger is not found");
    }

    @Override
    public List<Passenger> getActiveList() {
        return null;
    }

    public void check(PassengerRequestDTO passengerRequestDTO, List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            if (passenger.getDocumentType().equals(passengerRequestDTO.getDocumentType()) &&
                    passenger.getDocumentNumber().equals(passengerRequestDTO.getDocumentNumber()))
                throw new UniqueException("Passenger with " + passenger.getDocumentNumber() + " is already exists");
        }
    }
}
