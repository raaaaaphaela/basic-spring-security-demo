package de.neuefische.springsecuritydemo.Controller;

import de.neuefische.springsecuritydemo.Model.AppUser;
import de.neuefische.springsecuritydemo.Service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/app-users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping()
    public AppUser post(@RequestBody AppUser appUser) {
        return appUserService.create(appUser);
    }

   @PostMapping("/login")
    public Optional<AppUser> login() {
        return me();
   }

   @GetMapping("/me")
    public Optional<AppUser> me () {
        return appUserService.findByUsernameWithoutPassword(SecurityContextHolder.getContext().getAuthentication().getName());
   }

    @GetMapping("/logout")
    public void logout (HttpSession httpSession) {
        httpSession.invalidate();
    }
}
