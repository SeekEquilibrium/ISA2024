import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SignupComponent } from './pages/signup/signup.component';
import { VerifyModalComponent } from './components/verify-modal/verify-modal.component';

export const routes: Routes = [
    {path:'', component: HomeComponent},
    {path:'signup', component: SignupComponent},
    {path:'verify/:email', component:VerifyModalComponent},

];
