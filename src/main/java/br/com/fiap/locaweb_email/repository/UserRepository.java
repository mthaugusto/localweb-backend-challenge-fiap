package br.com.fiap.locaweb_email.repository;

import br.com.fiap.locaweb_email.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
