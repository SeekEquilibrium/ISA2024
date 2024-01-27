import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { of } from 'rxjs/internal/observable/of';
import { Observable } from 'rxjs';
import {ApiService} from "./ApiService";
import {ConfigService} from "./ConfigService";
import {UserAppService} from "./UserAppService"
import {Employee,Credentials} from "../User";
import { Platform } from '@angular/cdk/platform';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  access_token: any;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private userAppService: UserAppService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId:Object
  ) {
    if(isPlatformBrowser(this.platformId)){
      var access_token = localStorage.getItem("jwt");
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
        console.log(res);
        this.access_token = res.accessToken;
        this.userAppService.getActiveUser();
        localStorage.setItem("jwt", res.accessToken)
        window.location.href = "/";

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
    localStorage.clear();
    return this.apiService.post(`${this.config.log_out_url}`, "").subscribe(result => {
      window.location.href = "/";
    });
  }

  tokenIsPresent() {
    return localStorage.getItem("jwt") !== null;
  }

  refreshToken() {
    return this.apiService.post(`${this.config.refresh_token_url}`, "");
  }

}
