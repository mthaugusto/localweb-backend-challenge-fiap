package br.com.fiap.locaweb_email.repository;

import br.com.fiap.locaweb_email.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
