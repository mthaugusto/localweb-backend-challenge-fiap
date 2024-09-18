package br.com.fiap.locaweb_email.service;

import br.com.fiap.locaweb_email.dto.request.AttachmentRequest;
import br.com.fiap.locaweb_email.dto.response.AttachmentResponse;
import br.com.fiap.locaweb_email.entity.Attachment;
import br.com.fiap.locaweb_email.repository.AttachmentRepository;
import br.com.fiap.locaweb_email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService implements ServiceDTO<Attachment, AttachmentRequest, AttachmentResponse> {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Attachment toEntity(AttachmentRequest r) {
        return Attachment.builder()
                .fileName(r.fileName())
                .fileType(r.fileType())
                .data(r.data())
                .email(emailRepository.findById(r.emailId()).orElse(null))
                .build();
    }

    @Override
    public AttachmentResponse toResponse(Attachment e) {
        return AttachmentResponse.builder()
                .id(e.getId())
                .fileName(e.getFileName())
                .fileType(e.getFileType())
                .build();
    }

    @Override
    public List<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment findById(Long id) {
        return attachmentRepository.findById(id).orElse(null);
    }

    @Override
    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
}
