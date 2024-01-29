import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/AuthService';
import { UserAppService } from '../services/UserAppService';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
    storage:String | null;

  constructor(
    private readonly auth: AuthService,
    private readonly router: Router,
    private readonly userAppService: UserAppService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    if(isPlatformBrowser(this.platformId)){
        this.storage = localStorage.getItem("jwt");
      }
  }
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data['expectedRole'];

    const token = this.storage;
    if (!token) {
      this.router.navigate(['/signup']);
      return false;
    }

    const role = this.userAppService.getRoleFromToken(
      this.storage as string
    );

    if (role !== expectedRole) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
