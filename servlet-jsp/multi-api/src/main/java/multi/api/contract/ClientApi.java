package multi.api.contract;

import multi.api.dto.user.UserRequest;
import multi.api.dto.user.UserResponse;
import multi.api.exception.client.ClientDbException;
import multi.api.exception.client.ClientValidatorException;
import multi.api.exception.client.UserNullException;

public interface ClientApi {
    UserResponse authentication(String login, String password) throws UserNullException;

    void createClient(UserRequest userRequest) throws ClientValidatorException;
}
