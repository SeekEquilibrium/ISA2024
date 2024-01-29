import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import {ApiService} from "./ApiService";
import {ConfigService} from "./ConfigService";
import {UserAppService} from "./UserAppService"
import {Employee,Credentials} from "../User";
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  access_token: String | null;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private userAppService: UserAppService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId:Object
  ) {
    if(isPlatformBrowser(this.platformId)){
      this.access_token = localStorage.getItem("jwt");
    }
  }
  

  
  

  login(user: Credentials) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    const body = {
      'username': user.email,
      'password': user.password
    };
    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        
        this.access_token = res.accessToken;
        this.userAppService.getActiveUser();
        if(isPlatformBrowser(this.platformId)){
          localStorage.setItem("jwt", res.accessToken)
        }
        
        

      }));
  }

  register(user: Employee) {
    const registerHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.register_url, JSON.stringify(user), registerHeaders)
      .pipe(map(() => {
        console.log('Register success');
      }));
  }

  verify(email: string) {
    return this.apiService.post(`${this.config.verification_url}/${email}`, email);
  }

  logout() {
    this.userAppService.currentUser = null;
    this.access_token = null;
    if(isPlatformBrowser(this.platformId)){localStorage.clear();}
    return this.apiService.post(`${this.config.log_out_url}`, "").subscribe(result => {
      window.location.href = "/";
    });
  }

  tokenIsPresent() {
    if(isPlatformBrowser(this.platformId))
      return localStorage.getItem("jwt") !== null;
  }

  refreshToken() {
    return this.apiService.post(`${this.config.refresh_token_url}`, "");
  }

}
