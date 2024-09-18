package br.com.fiap.locaweb_email;

import java.util.Arrays;
import java.util.List;

public class SpamChecker {

    // Lista de domínios autorizados
    private static final List<String> AUTHORIZED_DOMAINS = Arrays.asList("hotmail.com", "gmail.com", "yahoo.com");

    public static void main(String[] args) {
        // Testar com alguns emails
        String[] emails = {
                "user1@hotmail.com",
                "user2@mydomain.com",
                "user3@spammail.com"
        };

        for (String email : emails) {
            boolean isSpam = isSpam(email);
            System.out.println(email + " is spam: " + isSpam);
        }
    }

    public static boolean isSpam(String email) {
        // Verifica se o email contém um '@'
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        // Divide o email em local e domínio
        String[] parts = email.split("@");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid email address format");
        }

        // Obtém o domínio do email
        String domain = parts[1];

        // Verifica se o domínio está na lista de domínios autorizados
        return !AUTHORIZED_DOMAINS.contains(domain);
    }
}
