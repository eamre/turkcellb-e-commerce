package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.UserService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateUserRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateUserRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateUserResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllUsersResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetUserResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {
    private UserService userService;

    @GetMapping
    public List<GetAllUsersResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@RequestBody CreateUserRequest request) {
        return userService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse update(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
}
