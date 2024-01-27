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
    
}
