import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./components/navbar/navbar.component";
import { HomeComponent } from './pages/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignupComponent } from './pages/signup/signup.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthService } from './services/AuthService';
import { TokenInterceptor } from './tools/TokenInterceptor';


@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [CommonModule, RouterOutlet, NavbarComponent , HomeComponent ,FooterComponent ,SignupComponent],
    providers:[
      [AuthService]
    ]
})
export class AppComponent {
  title = 'Equipped';

  constructor(
    private authService:AuthService,

  ){}

  isLoggedIn(): boolean {
    return this.authService.tokenIsPresent() ?? false;
  }

  logOut() {
    this.authService.logout();
  }
}
