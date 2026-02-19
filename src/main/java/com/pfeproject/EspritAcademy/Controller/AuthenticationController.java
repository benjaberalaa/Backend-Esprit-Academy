package com.pfeproject.EspritAcademy.Controller;
import com.google.gson.Gson;
import com.pfeproject.EspritAcademy.Entity.Role;
import com.pfeproject.EspritAcademy.Entity.User;
import com.pfeproject.EspritAcademy.Services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pfeproject.EspritAcademy.dto.UpdateProfileRequest;
import java.security.Principal;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @GetMapping("/GetAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<User> GetAccountsAll()  {
        System.out.println("tay");
        return service.getAccounts();
    }


//    @ResponseStatus(HttpStatus.OK)
//    public void register(@ModelAttribute RegisterRequest request) throws Exception {
//        service.register(request);
//    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestParam("file") MultipartFile file,
                         @RequestParam("request") String requestJson
                         ) throws Exception {
        RegisterRequest request = new Gson().fromJson(requestJson, RegisterRequest.class);
        service.register(file,request);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request);
        return service.authenticate(request);
    }

    @GetMapping("/nbelev")
    public long getNombreElevesByRole() {
        Role role = Role.USER;
        return service.getnbByRole(role);
    }

    @GetMapping("/nbEnsei")
    public long getNombreEnseignantByRole() {
        Role role = Role.ENSEI;
        return service.getnbByRole(role);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestParam(value = "file", required = false) MultipartFile file,
                              @RequestParam("request") String requestJson,
                              Principal connectedUser) throws Exception {
        System.out.println(requestJson);
        UpdateProfileRequest request = new Gson().fromJson(requestJson, UpdateProfileRequest.class);
        System.out.println(request);
        service.updateUserProfile(file, request, connectedUser);
    }
    @DeleteMapping("/DeleteProfile/{idprofile}")
    public void DeleteProfile(@PathVariable Integer idprofile) {
        service.deleteProfile( idprofile);
    }
}