package br.com.fiap.locaweb_email.service;

import br.com.fiap.locaweb_email.dto.request.FolderRequest;
import br.com.fiap.locaweb_email.dto.response.EmailResponse;
import br.com.fiap.locaweb_email.dto.response.FolderResponse;
import br.com.fiap.locaweb_email.entity.Folder;
import br.com.fiap.locaweb_email.repository.FolderRepository;
import br.com.fiap.locaweb_email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderService implements ServiceDTO<Folder, FolderRequest, FolderResponse> {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Override
    public Folder toEntity(FolderRequest r) {
        return Folder.builder()
                .name(r.name())
                .user(userRepository.findById(r.userId()).orElse(null))
                .build();
    }

    @Override
    public FolderResponse toResponse(Folder e) {
        List<EmailResponse> emailResponses = e.getEmails().stream()
                .map(emailService::toResponse)
                .collect(Collectors.toList());

        return FolderResponse.builder()
                .id(e.getId())
                .name(e.getName())
                .user(userService.toResponse(e.getUser()))
                .emails(emailResponses)
                .build();
    }

    @Override
    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    @Override
    public Folder save(Folder e) {
        return folderRepository.save(e);
    }
}
