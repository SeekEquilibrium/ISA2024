import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import { CommonModule } from '@angular/common';
import {AbstractControl,FormBuilder,FormControl,FormGroup,Validators,ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/AuthService';
import { UserAppService } from '../../services/UserAppService';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatTabsModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatIconModule,
    MatSelectModule,
    ReactiveFormsModule,

  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
  providers: [AuthService,UserAppService]
})
export class SignupComponent implements OnInit {

  ngOnInit(): void {
    
  }

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private userAppService: UserAppService,

    ) {
  }

  password = new FormControl(null, [
    (c: AbstractControl) => Validators.required(c),
    Validators.pattern(
      /(?=.*[a-z])(?=.*[A-Z]).{6,}/
    ),
  ]);
  passwordCheck = new FormControl(null, [
    (c: AbstractControl) => Validators.required(c)
  ]);
  form_register: FormGroup = this.formBuilder.group({
    name: ['', Validators.compose([Validators.required])],
    surname: ['', Validators.compose([Validators.required])],
    email: ['', Validators.compose([Validators.required, Validators.email],)],
    phoneNumber: ['', Validators.compose([Validators.required, Validators.pattern("[0-9]\+")])],
    gender: ['', Validators.compose([Validators.required])],
    occupation: [''],
    companyInfo: [''],
    street: ['', Validators.compose([Validators.required])],
    city: ['', Validators.compose([Validators.required])],
    country: ['', Validators.compose([Validators.required])],
    password: this.password,
    passwordCheck: this.passwordCheck,
  }, {
    validators: this.ConfirmedValidator('password', 'passwordCheck')
  });


  form_login:FormGroup= this.formBuilder.group({
    email: ['', Validators.compose([Validators.required, Validators.email])],
    password: ['', Validators.compose([Validators.required])]
  })

  ConfirmedValidator(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if (
        matchingControl.errors &&
        !matchingControl.errors['confirmedValidator']
      ) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({confirmedValidator: true});
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  onSubmit_register(): void {
    this.router.navigate(["/"])
    this.authService.register(this.form_register.value).subscribe(data => {
        
      },
      error => {
        console.log(error);
      });
  }

  onSubmit_login(): void {
    
    this.authService.login(this.form_login.value).subscribe((data)=>{
      this.router.navigate(["/"]);
      this.userAppService.getActiveUser().subscribe(user => {
        this.userAppService.currentUser = user;
        console.log(data);
      })
    },
    (error)=>{
      console.log(error);
    })
  }
}
