package multi.api.contract;

import multi.api.dto.user.UserRequest;
import multi.api.dto.user.UserResponse;

public interface ClientApi {
     UserResponse authentication(String login, String password);
     void createClient(UserRequest userRequest);
}
