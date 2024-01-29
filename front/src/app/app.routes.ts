import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SignupComponent } from './pages/signup/signup.component';
import { VerifyModalComponent } from './components/verify-modal/verify-modal.component';
import { EmployeeProfileComponent } from './pages/profiles/employee-profile/employee-profile.component';
import { AuthGuard } from './tools/Auth-guard';
import { RoleGuard } from './tools/Role-guard';

export const routes: Routes = [
    
    {path:'', component: HomeComponent},
    {path:'signup', component: SignupComponent},
    {path:'verify/:email', component:VerifyModalComponent},
    {path:'profile', component:EmployeeProfileComponent , canActivate: [AuthGuard] },
    {path:'profile/edit', component:EmployeeProfileComponent , canActivate: [RoleGuard], data:{expectedRole:'ROLE_EMPLOYEE'}},

];
