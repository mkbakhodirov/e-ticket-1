package uz.pdp.eticket1.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.eticket1.base.BaseService;
import uz.pdp.eticket1.exception.MissRequiredParam;
import uz.pdp.eticket1.exception.NotFoundException;
import uz.pdp.eticket1.exception.UniqueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRequestDTO, User> {

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
    public User get(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new NotFoundException("User is not found");
    }

    @Override
    public List<User> getActiveList() {
        return null;
    }

    @Override
    public List<User> getList() {
        return  userRepository.findAll();
    }

    @Override
    public List<User> getList(String str) {
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

    public User loginAdmin(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user != null)
            return user;
        throw new NotFoundException("username or password is not correct");
    }

    public UserBase getUserBase(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            if (user.getRole() == 2)
                return modelMapper.map(user, UserBase.class);
        }
        throw new NotFoundException("ID: " + id + " is not admin");
    }

}
