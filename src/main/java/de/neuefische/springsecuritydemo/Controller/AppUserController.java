package de.neuefische.springsecuritydemo.Controller;

import de.neuefische.springsecuritydemo.Model.AppUser;
import de.neuefische.springsecuritydemo.Service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/app-users")
    public AppUser post(@RequestBody AppUser appUser) {
        return appUserService.create(appUser);
    }

    @GetMapping("/{username}")
    public AppUser getUserDetails(@PathVariable String username) {
        return appUserService.returnUserDetails(username);
    }
}
