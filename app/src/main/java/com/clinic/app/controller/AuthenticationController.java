package com.clinic.app.controller;

import com.clinic.app.converter.EmployeeDTOConv;
import com.clinic.app.dto.EmployeeDTO;
import com.clinic.app.dto.JWTAuthDTO;
import com.clinic.app.model.*;
import com.clinic.app.security.TokenHandler;
import com.clinic.app.service.RefreshTokenService;
import com.clinic.app.service.UserAppService;
import io.swagger.annotations.Api;
import org.apache.catalina.Authenticator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping(value = "/auth")
@Api(value = "/auth", tags = "Auth")
public class AuthenticationController {
    private final UserAppService userAppservice;
    private final EmployeeDTOConv employeeDTOConv ;
    private final AuthenticationManager authenticationManager;
    private final TokenHandler tokenHandler;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationController(UserAppService userAppservice , EmployeeDTOConv employeeDTOConv, AuthenticationManager authenticationManager, TokenHandler tokenHandler, RefreshTokenService refreshTokenService) {
        this.userAppservice = userAppservice;
        this.employeeDTOConv = employeeDTOConv;
        this.authenticationManager = authenticationManager;
        this.tokenHandler = tokenHandler;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee (@RequestBody EmployeeDTO request) {
        if (userAppservice.findbyEmail(request.getEmail()) == null) {
            Employee employee= employeeDTOConv.DTOToEmployee(request);
            employee.setVerified(false);
            employee.setRole(Role.EMPLOYEE);
            userAppservice.register(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<UserToken> createAuthToken(@RequestBody JWTAuthDTO authenticationRequest, HttpServletResponse response){
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserApp user = (UserApp) authentication.getPrincipal();
        String jwt = this.tokenHandler.generateToken(user.getUsername());
        ResponseCookie jwtCookie = tokenHandler.generateJwtCookie(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        ResponseCookie jwtRefreshCookie = tokenHandler.generateRefreshJwtCookie(refreshToken.getToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserToken(jwt, user.getUsername()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(principal.toString(), "anonymousUser")) {
            refreshTokenService.deleteByUserId(((Employee) principal).getId());
        }

        ResponseCookie jwtCookie = tokenHandler.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = tokenHandler.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .build();
    }

    @PostMapping("verify/{email}")
    public ResponseEntity<?> verify(@PathVariable String email){
        UserApp user = userAppservice.findbyEmail(email);
        if(user== null){
            return ResponseEntity.status(404).body("Account with the given email does not exist.");
        }else if(user.isVerified()) {
            return ResponseEntity.status(403).body("Account already verified.");
        } else {
            userAppservice.verifyAccount(user);
            return ResponseEntity.ok().build();
        }
    }

}
