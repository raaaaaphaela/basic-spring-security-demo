package de.neuefische.springsecuritydemo.Service;

import de.neuefische.springsecuritydemo.Model.AppUser;
import de.neuefische.springsecuritydemo.Repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AppUser create(AppUser appUser) {
        Optional<AppUser> existingAppUser = appUserRepository.findByUsername(appUser.getUsername());

        if (existingAppUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        if (
                SecurityContextHolder.getContext().getAuthentication() == null ||
                        !SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .isAuthenticated() ||
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getAuthorities()
                                .stream()
                                .noneMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))
        ) {
            appUser.setRole("BASIC");
        }
        appUserRepository.save(appUser);

        appUser.setPassword("");

        return appUser;
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public Optional<AppUser> findByUsernameWithoutPassword(String name) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(name);
        appUser.ifPresent(user -> user.setPassword(""));
        return appUser;
    }
}
