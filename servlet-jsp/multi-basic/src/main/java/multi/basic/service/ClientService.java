package multi.basic.service;

import multi.api.contract.ClientApi;
import multi.api.dto.UserRequest;
import multi.api.dto.UserResponse;
import multi.basic.mapping.UserMapper;
import multi.basic.repository.RepositoryClient;
import multi.domain.Client;

import java.util.List;
import java.util.Optional;


public class ClientService implements ClientApi {
    private UserMapper userMapper;
    private RepositoryClient repositoryClient;

    public ClientService(UserMapper userMapper, RepositoryClient repositoryClient) {
        this.userMapper = userMapper;
        this.repositoryClient = repositoryClient;
    }

    @Override
    public UserResponse authentication(String login, String password) {
        var clientList = repositoryClient.getList();
        Optional<Client> user = clientList.stream().filter(client -> client.getLogin().equals(login) && client.getPassword().equals(password)).findFirst();
        return user.map(client -> userMapper.createResponse(client)).orElse(null);
    }

    @Override
    public void createClient(UserRequest userRequest) {
        repositoryClient.save(userMapper.createClient(userRequest));
    }
    public List<Client> test() {
        return repositoryClient.getList();
    }
}
