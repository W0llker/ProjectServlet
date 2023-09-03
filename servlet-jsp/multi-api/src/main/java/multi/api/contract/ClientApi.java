package multi.api.contract;

import multi.api.dto.UserRequest;
import multi.api.dto.UserResponse;

public interface ClientApi {
     UserResponse authentication(String login, String password);
     void createClient(UserRequest userRequest);
}
