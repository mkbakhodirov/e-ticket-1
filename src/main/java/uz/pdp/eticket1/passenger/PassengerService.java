package uz.pdp.eticket1.passenger;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;
import uz.pdp.eticket1.user.User;
import uz.pdp.eticket1.user.UserResponseDTO;
import uz.pdp.eticket1.user.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService implements BaseService<PassengerReceiveDTO, PassengerResponseDTO> {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public String add(PassengerReceiveDTO passengerReceiveDTO) {
        String userId = passengerReceiveDTO.getUserId();
        User user = userService.getUser(userId);
        List<Passenger> passengers = user.getPassengers();
        check(passengerReceiveDTO, passengers);
        Passenger passenger = modelMapper.map(passengerReceiveDTO, Passenger.class);
        passengers.add(passenger);
        user.setUpdateDate(LocalDateTime.now());
        userService.editUser(user);
        return passengerReceiveDTO.getDocumentNumber();
    }

    @Override
    public PassengerResponseDTO get(String userId) {
        return null;
    }

    @Override
    public List<PassengerResponseDTO> getList() {
        return null;
    }

    @Override
    public List<PassengerResponseDTO> getList(String userId) {
        List<PassengerResponseDTO> list = new ArrayList<>();
        for (Passenger passenger : userService.getUser(userId).getPassengers()) {
            PassengerResponseDTO passengerResponseDTO = modelMapper.map(passenger, PassengerResponseDTO.class);
            list.add(passengerResponseDTO);
        }
        return list;
    }

    @Override
    public PassengerResponseDTO get(String userId, String documentNumber) {
        User user = userService.getUser(userId);
        for (Passenger passenger : user.getPassengers()) {
            if (passenger.getDocumentNumber().equals(documentNumber))
                return modelMapper.map(passenger, PassengerResponseDTO.class);
        }
        throw new NotFoundException("Passenger is not found");
    }

    public void check(PassengerReceiveDTO passengerReceiveDTO, List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            if (passenger.getDocumentType().equals(passengerReceiveDTO.getDocumentType()) &&
                    passenger.getDocumentNumber().equals(passengerReceiveDTO.getDocumentNumber()))
                throw new UniqueException("Passenger with " + passenger.getDocumentNumber() + " is already exists");
        }
    }
}
