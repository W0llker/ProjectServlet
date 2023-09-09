package multi.basic.service;

import multi.api.contract.ClientApi;
import multi.api.dto.user.UserRequest;
import multi.api.dto.user.UserResponse;
import multi.basic.mapping.UserMapper;
import multi.basic.repository.ClientDao;
import multi.basic.repository.file.RepositoryClient;
import multi.domain.Client;

import java.util.List;
import java.util.Optional;


public class ClientService implements ClientApi {
    private UserMapper userMapper;
    private ClientDao repositoryClient;

    public ClientService(UserMapper userMapper, ClientDao repositoryClient) {
        this.userMapper = userMapper;
        this.repositoryClient = repositoryClient;
    }

    @Override
    public UserResponse authentication(String login, String password) {
        var clientList = repositoryClient.getAllClient();
        Optional<Client> user = clientList.stream().filter(client -> client.getLogin().equals(login) && client.getPassword().equals(password)).findFirst();
        return user.map(client -> userMapper.createResponse(client)).orElse(null);
    }

    @Override
    public void createClient(UserRequest userRequest) {
        repositoryClient.save(userMapper.createClient(userRequest));
    }
    public List<Client> test() {
        return repositoryClient.getAllClient();
    }
}
