package multi.basic.service;

import multi.api.contract.ClientApi;
import multi.api.dto.user.UserRequest;
import multi.api.dto.user.UserResponse;
import multi.api.exception.client.ClientDbException;
import multi.api.exception.client.ClientValidatorException;
import multi.api.exception.client.UserNullException;
import multi.basic.mapping.UserMapper;
import multi.basic.repository.ClientDao;
import multi.domain.Client;

import java.util.List;


public class ClientService implements ClientApi {
    private UserMapper userMapper;
    private ClientDao repositoryClient;

    public ClientService(UserMapper userMapper, ClientDao repositoryClient) {
        this.userMapper = userMapper;
        this.repositoryClient = repositoryClient;
    }

    @Override
    public UserResponse authentication(String login, String password) throws UserNullException, ClientDbException {
        Client client = repositoryClient.findByLogin(login);
        if (client == null) {
            throw new UserNullException("Данного пользователя нету с таким логином");
        } else if (!client.getPassword().equals(password)) {
            throw new UserNullException("Пароль не правильный");
        }
        return userMapper.createResponse(client);
    }

    @Override
    public void createClient(UserRequest userRequest) throws ClientValidatorException, ClientDbException {
        if (userRequest.getName().equals("") | userRequest.getLogin().equals("") | userRequest.getSurName().equals("") | userRequest.getPassword().equals("")) {
            throw new ClientValidatorException("Введите все данные");
        } else if (repositoryClient.findByLogin(userRequest.getLogin()) != null) {
            throw new ClientValidatorException("Данный логин уже занят");
        }
        repositoryClient.save(userMapper.createClient(userRequest));
    }

    public List<Client> test() {
        return repositoryClient.getAllClient();
    }
}
