package br.com.fiap.locaweb_email.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_EMAILS")
public class Email {

    @Id
    @Column(name="ID_EMAIL")
    private Long id;
    private String subject;
    private String body;
    private boolean isRead = false;
    private boolean isArchived = false;
    private LocalDateTime sentAt;
    private LocalDateTime receivedAt;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "SENDER", referencedColumnName = "ID_SENDER",
            foreignKey = @ForeignKey(name = "FK_SENDER_EMAIL"))
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "RECIPIENT", referencedColumnName = "ID_RECIPIENT",
            foreignKey = @ForeignKey(name = "FK_RECIPIENT_EMAIL"))
    private User recipient;


}
