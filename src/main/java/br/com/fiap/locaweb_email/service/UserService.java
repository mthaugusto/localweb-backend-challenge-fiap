package br.com.fiap.locaweb_email.service;

import br.com.fiap.locaweb_email.dto.request.UserRequest;
import br.com.fiap.locaweb_email.dto.response.FolderResponse;
import br.com.fiap.locaweb_email.dto.response.UserResponse;
import br.com.fiap.locaweb_email.entity.User;
import br.com.fiap.locaweb_email.repository.FolderRepository;
import br.com.fiap.locaweb_email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements ServiceDTO<User, UserRequest, UserResponse> {

    @Autowired
    private UserRepository repo;

    @Autowired
    private FolderRepository folderRepository;


    public User toEntity(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .active(true)
                .build();
    }

    public UserResponse toResponse(User user) {
        List<FolderResponse> folderResponses = user.getFolders().stream()
                .map(folder -> FolderResponse.builder()
                        .id(folder.getId())
                        .name(folder.getName())
                        .user(null) // Evitar referência circular entre User e Folder
                        .emails(null)
                        .build())
                .collect(Collectors.toList());

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.getActive())
                .folders(folderResponses)
                .build();
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id).orElse(null);
    };

    @Override
    public User save(User e) {
        return repo.save(e);
    }
}
