package multi.basic.controler;


import multi.basic.service.ClientService;

public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
}
