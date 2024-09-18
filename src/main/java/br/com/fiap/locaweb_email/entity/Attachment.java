package br.com.fiap.locaweb_email.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="TB_ATTACHMENTS")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_ATTACHMENT")
    @SequenceGenerator(name="SQ_ATTACHMENT", sequenceName = "SQ_ATTACHMENT", allocationSize = 1, initialValue = 1)
    @Column(name="ID_ATTACHMENT")
    private Long id;

    @Column(name="FILE_NAME", nullable = false)
    private String fileName;

    @Column(name="FILE_TYPE", nullable = false)
    private String fileType;

    @Lob
    @Column(name="FILE_DATA", nullable = false)
    private byte[] data;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "EMAIL_ID", referencedColumnName = "ID_EMAIL",
            foreignKey = @ForeignKey(name = "FK_EMAIL_ATTACHMENT"))
    private Email email;
}
