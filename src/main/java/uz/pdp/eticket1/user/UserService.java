package uz.pdp.eticket1.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.MissRequiredParam;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;
import uz.pdp.eticket1.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserReceiveDTO, UserResponseDTO> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public String add(UserReceiveDTO userReceiveDTO) {
        check(userReceiveDTO);
        User user = modelMapper.map(userReceiveDTO, User.class);
        User save = userRepository.save(user);
        return save.getId();
    }

    @Override
    public UserResponseDTO get(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
            List<Ticket> userTickets = user.getTickets();
            userResponseDTO.setNumberOfTickets(userTickets.size());
            userResponseDTO.setLastBuyTicketDate(userTickets.get(userTickets.size() - 1).getCreationDate());
            return userResponseDTO;
        }
        throw new NotFoundException("User is not found");
    }

    @Override
    public List<UserResponseDTO> getList() {
        List<UserResponseDTO> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
            List<Ticket> userTickets = user.getTickets();
            if (!userTickets.isEmpty()) {
                userResponseDTO.setNumberOfTickets(userTickets.size());
                userResponseDTO.setLastBuyTicketDate(userTickets.get(userTickets.size() - 1).getCreationDate());
            }
            users.add(userResponseDTO);
        }
        return users;
    }

    private void check(UserReceiveDTO userReceiveDTO) {
        String email = userReceiveDTO.getEmail();
        String phoneNumber = userReceiveDTO.getPhoneNumber();
        User user;
        if (email != null) {
            user = userRepository.findUserByEmail(email);
            System.out.println(user);
            if (user != null)
                throw new UniqueException(userReceiveDTO.getEmail() + " is already exists");
        } else if (phoneNumber != null) {
            user = userRepository.findUserByPhoneNumber(phoneNumber);
            System.out.println(user);
            if (user != null)
                throw new UniqueException(userReceiveDTO.getPhoneNumber() + " is already exists");
        } else
            throw new MissRequiredParam("Email / phone number was not been entered");
    }

}
