import {Injectable} from '@angular/core';
import {ApiService} from "./ApiService";
import {ConfigService} from "./ConfigService";
import {User} from "../User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserAppService {

  currentUser: User | null = null;

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) {

  }

  getActiveUser(): Observable<User> {
    return this.apiService.get(this.config.current_url);
    
  }

  setActiveUser() {
    this.getActiveUser().subscribe(data => this.currentUser = data);
    
  }

  parseJwt(token: string) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  }

  getRoleFromToken(token: string) {
    return this.parseJwt(token)['role'];
  }
    
}
