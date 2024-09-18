package br.com.fiap.locaweb_email.entity;

public class Attachment {

    private Long id;
    private String fileName;
    private String filetype;
    private String data; // poderia ser byte[]
    private Email email;
}
