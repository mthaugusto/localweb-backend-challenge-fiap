package br.com.fiap.locaweb_email.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_FOLDERS")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_FOLDER")
    @SequenceGenerator(name="SQ_FOLDER", sequenceName = "SQ_FOLDER", allocationSize = 1, initialValue = 1)
    @Column(name="ID_FOLDER")
    private Long id;

    @Column(name="FOLDER_NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_USER_FOLDER"))
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> emails;
}
