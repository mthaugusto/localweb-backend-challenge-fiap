package br.com.fiap.locaweb_email.entity;

import java.util.List;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private List<Email> sentEmails;
    private List<Email> receivedEmails;

}
