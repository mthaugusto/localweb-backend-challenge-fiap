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
@Table(name="TB_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_USER")
    @SequenceGenerator(name="SQ_USER", sequenceName = "SQ_USER", allocationSize = 1, initialValue = 1)
    @Column(name="ID_USER")
    private Long id;

    @Column(name="USER_NAME", nullable = false)
    private String name;

    @Column(name="USER_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name="USER_PASSWORD", nullable = false)
    private String password;

    @Column(name="IS_ACTIVE", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Folder> folders;

}
