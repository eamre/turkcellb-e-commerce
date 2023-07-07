package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateUserRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateUserResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllUsersResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetUserResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateUserResponse;
import kodlama.io.ecommerce.entities.concretes.User;
import kodlama.io.ecommerce.repository.abstracts.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = repository.findAll();
        List<GetAllUsersResponse> responses = users.stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetUserResponse getById(int id) {
        User user = repository.findById(id).orElseThrow();
        GetUserResponse response = mapper.map(user,GetUserResponse.class);

        return response;
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        User user = mapper.map(request, User.class);
        user.setId(0);

        repository.save(user);

        CreateUserResponse response = mapper.map(user, CreateUserResponse.class);
        return response;
    }

    @Override
    public UpdateUserResponse update(int id, UpdateUserRequest request) {
        User user = mapper.map(request, User.class);
        user.setId(id);

        repository.save(user);

        UpdateUserResponse response = mapper.map(user, UpdateUserResponse.class);
        return response;    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
