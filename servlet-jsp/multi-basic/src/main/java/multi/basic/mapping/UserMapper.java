package multi.basic.mapping;


import multi.api.dto.UserRequest;
import multi.api.dto.UserResponse;
import multi.domain.Client;

public class UserMapper {
    public static Client createClient(UserRequest userRequest) {
        return new Client(userRequest.getName(), userRequest.getSurName(), userRequest.getLogin(), userRequest.getPassword());
    }

    public static UserResponse createResponse(Client client) {
        return new UserResponse(client.getId(),
                client.getName(),
                client.getSurName(),
                client.getName() + " " + client.getSurName(),
                client.getLogin(),
                client.getRole());
    }
}
