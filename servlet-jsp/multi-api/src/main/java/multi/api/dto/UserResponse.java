package multi.api.dto;


import multi.domain.Role;

public class UserResponse {
    private long id;
    private String name;
    private String surName;
    private String fullName;
    private String login;
    private Role role;

    public UserResponse(long id, String name, String surName, String fullName, String login, Role role) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.fullName = fullName;
        this.login = login;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
