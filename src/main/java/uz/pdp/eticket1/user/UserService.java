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
public class UserService implements BaseService<UserRequestDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public String add(UserRequestDTO userRequestDTO) {
        check(userRequestDTO);
        User user = modelMapper.map(userRequestDTO, User.class);
        User save = userRepository.save(user);
        return save.getId();
    }

    @Override
    public UserResponseDTO get(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            if (user.getRole() == 1) {
                UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
                List<Ticket> userTickets = user.getTickets();
                if (!userTickets.isEmpty()) {
                    userResponseDTO.setNumberOfTickets(userTickets.size());
                    userResponseDTO.setLastBuyTicketDate(userTickets.get(userTickets.size() - 1).getCreationDate());
                }
                return userResponseDTO;
            }
        }
        throw new NotFoundException("User is not found");
    }

    @Override
    public List<UserResponseDTO> getList() {
        List<UserResponseDTO> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getRole() == 1) {
                UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
                List<Ticket> userTickets = user.getTickets();
                if (!userTickets.isEmpty()) {
                    userResponseDTO.setNumberOfTickets(userTickets.size());
                    userResponseDTO.setLastBuyTicketDate(userTickets.get(userTickets.size() - 1).getCreationDate());
                }
                users.add(userResponseDTO);
            }
        }
        return users;
    }

    @Override
    public List<UserResponseDTO> getList(String str) {
        return null;
    }

    public void editUser(User user) {
        userRepository.save(user);
    }

    private void check(UserRequestDTO userRequestDTO) {
        String phoneNumber = userRequestDTO.getPhoneNumber();
        String username = userRequestDTO.getUsername();
        int role = userRequestDTO.getRole();
        User user;
        if (role == 1 && phoneNumber != null) {
            user = userRepository.findUserByPhoneNumber(phoneNumber);
            System.out.println(user);
            if (user != null)
                throw new UniqueException(userRequestDTO.getPhoneNumber() + " is already exists");
        } else if (role == 2 && username != null) {
            user = userRepository.findUserByUsername(username);
            System.out.println(user);
            if (user != null)
                throw new UniqueException(userRequestDTO.getUsername() + " is already exists");
        } else
            throw new MissRequiredParam("Phone number was not been entered");
    }

    @Override
    public UserResponseDTO get(String str1, String str2) {
        return null;
    }

    public User getUser(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new NotFoundException("User is not found");
    }

    public AdminResponseDTO getAdminResponse(String adminId) {
        Optional<User> optional = userRepository.findById(adminId);
        if (optional.isPresent()) {
            User user = optional.get();
            if (user.getRole() == 2)
                return modelMapper.map(user, AdminResponseDTO.class);
        }
        throw new NotFoundException(adminId + " is not admin");
    }

}
