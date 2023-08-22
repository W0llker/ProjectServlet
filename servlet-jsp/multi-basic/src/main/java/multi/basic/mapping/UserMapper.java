package multi.basic.mapping;


import multi.api.dto.UserRequest;
import multi.api.dto.UserResponse;
import multi.domain.Client;

public class UserMapper {
    public Client createClient(UserRequest userRequest) {
        return new Client(userRequest.getName(), userRequest.getSurName(), userRequest.getLogin(), userRequest.getPassword());
    }

    public UserResponse createResponse(Client client) {
        return new UserResponse(client.getId(),
                client.getName(),
                client.getSurName(),
                client.getName() + " " + client.getSurName(),
                client.getLogin(),
                client.getRole());
    }
}
