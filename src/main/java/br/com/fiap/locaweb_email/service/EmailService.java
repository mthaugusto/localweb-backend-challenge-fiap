package br.com.fiap.locaweb_email.service;

import br.com.fiap.locaweb_email.dto.request.EmailRequest;
import br.com.fiap.locaweb_email.dto.response.AttachmentResponse;
import br.com.fiap.locaweb_email.dto.response.EmailResponse;
import br.com.fiap.locaweb_email.dto.response.FolderResponse;  // Importar o FolderResponse
import br.com.fiap.locaweb_email.entity.Email;
import br.com.fiap.locaweb_email.entity.Folder;  // Importar o Folder
import br.com.fiap.locaweb_email.repository.EmailRepository;
import br.com.fiap.locaweb_email.repository.FolderRepository;  // Importar o FolderRepository
import br.com.fiap.locaweb_email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService implements ServiceDTO<Email, EmailRequest, EmailResponse>{

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FolderRepository folderRepository;  // Adicionar o FolderRepository

    @Autowired
    private UserService userService;


    @Override
    public Email toEntity(EmailRequest r) {
        Folder folder = (r.folderId() != null) ? folderRepository.findById(r.folderId()).orElse(null) : null;

        return Email.builder()
                .subject(r.subject())
                .body(r.body())
                .sentAt(r.sentAt())
                .receivedAt(r.receivedAt())
                .sender(userRepository.findById(r.senderId()).orElse(null))
                .recipient(userRepository.findById(r.recipientId()).orElse(null))
                .folder(folder)  // Associar o Folder ao Email
                .isRead(false)
                .isArchived(false)
                .build();
    }

    @Override
    public EmailResponse toResponse(Email e) {
        // Mapear os attachments
        List<AttachmentResponse> attachmentResponses = e.getAttachments().stream()
                .map(attachment -> AttachmentResponse.builder()
                        .id(attachment.getId())
                        .fileName(attachment.getFileName())
                        .fileType(attachment.getFileType())
                        .build())
                .collect(Collectors.toList());


        FolderResponse folderResponse = (e.getFolder() != null) ? FolderResponse.builder()
                .id(e.getFolder().getId())
                .name(e.getFolder().getName())
                .user(userService.toResponse(e.getFolder().getUser()))
                .emails(e.getFolder().getEmails().stream()
                        .map(folderEmail -> EmailResponse.builder()
                                .id(folderEmail.getId())
                                .subject(folderEmail.getSubject())
                                .body(folderEmail.getBody())
                                .isRead(folderEmail.isRead())
                                .isArchived(folderEmail.isArchived())
                                .sentAt(folderEmail.getSentAt())
                                .receivedAt(folderEmail.getReceivedAt())
                                .sender(userService.toResponse(folderEmail.getSender()))
                                .recipient(userService.toResponse(folderEmail.getRecipient()))
                                .attachments(folderEmail.getAttachments().stream()
                                        .map(attachment -> AttachmentResponse.builder()
                                                .id(attachment.getId())
                                                .fileName(attachment.getFileName())
                                                .fileType(attachment.getFileType())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build() : null;

        return EmailResponse.builder()
                .id(e.getId())
                .subject(e.getSubject())
                .body(e.getBody())
                .isRead(e.isRead())
                .isArchived(e.isArchived())
                .sentAt(e.getSentAt())
                .receivedAt(e.getReceivedAt())
                .sender(userService.toResponse(e.getSender()))
                .recipient(userService.toResponse(e.getRecipient()))
                .folder(folderResponse)
                .attachments(attachmentResponses)
                .build();
    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public Email findById(Long id) {
        return emailRepository.findById(id).orElse(null);
    }

    @Override
    public Email save(Email e) {
        return emailRepository.save(e);
    }
}
