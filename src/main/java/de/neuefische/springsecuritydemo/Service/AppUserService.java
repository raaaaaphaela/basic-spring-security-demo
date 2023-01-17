package de.neuefische.springsecuritydemo.Service;

import de.neuefische.springsecuritydemo.Model.AppUser;
import de.neuefische.springsecuritydemo.Repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        appUserRepository.save(appUser);
        appUser.setPassword("");
        return appUser;
    }

    public Optional<AppUser> findByUsername (String username) {
        return appUserRepository.findByUsername(username);
    }

    public AppUser returnUserDetails(String username) {

        Optional<AppUser> existingUser = appUserRepository.findByUsername(username);

        if (username.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return existingUser.get();
    }
}
