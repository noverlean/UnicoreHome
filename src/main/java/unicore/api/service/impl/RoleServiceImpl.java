package unicore.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicore.api.entities.Role;
import unicore.api.repository.RoleRepository;
import unicore.api.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
