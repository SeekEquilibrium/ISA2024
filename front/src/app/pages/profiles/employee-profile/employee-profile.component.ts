import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Employee } from '../../../User';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../services/EmployeeService';
import {MatCardModule} from '@angular/material/card';
import { CommonModule } from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";


@Component({
  selector: 'app-employee-profile',
  standalone: true,
  imports: [
    FormsModule, 
    ReactiveFormsModule,
    MatCardModule,
    CommonModule,
    MatIconModule,
    MatDividerModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule
  ],
  templateUrl: './employee-profile.component.html',
  styleUrl: './employee-profile.component.css',
  providers:[EmployeeService]
})
export class EmployeeProfileComponent implements OnInit {
  profileForm: FormGroup = this.formBuilder.group({
    name: [],
    surname: [],
    gender: [],
    phoneNumber: [],
    email: [],
    occupation: [],
    companyInfo: [],
    street: [],
    city: [],
    country: [],
  });

  public employee: Employee ;
  public editEnabled : boolean=false;  

  constructor(
    private formBuilder: FormBuilder,
    private router:Router,
    private employeeService:EmployeeService,

    ) {}

  ngOnInit(): void {
    this.employeeService.getCurrentEmployee().subscribe((res:any) =>{
      this.employee = res;
      this.profileForm.setValue(this.employee);
    })
    if(window.location.href.indexOf("edit")> -1) {
      this.editEnabled = true;
    } else {
      this.editEnabled= false;
    }
  }
  saveEditChanges(): void{

  this.employeeService.editEmployee(this.profileForm.getRawValue()).subscribe((res:any) => {
    this.disableSafe();
    window.location.href = window.location.origin + "/profile";
  })
  }

  editClicked(): void{
    this.editEnabled = !this.editEnabled;
    if(this.editEnabled){
      window.history.pushState('edit', 'Edit', '/profile/edit');
      this.enableSafe();
    }
    else{
      window.history.pushState('profile', 'Profile', '/profile');
      this.disableSafe();
    }
  }
  goBack() {
    this.editEnabled = false;
    window.history.pushState('profile', 'Profile', '/profile');
    this.disableSafe();
  }

  enableSafe():void{
    this.profileForm.get('name')?.enable();
    this.profileForm.get('surname')?.enable();
    this.profileForm.get('occupation')?.enable();
    this.profileForm.get('companyInfo')?.enable();
    this.profileForm.get('gender')?.enable();
    this.profileForm.get('phoneNumber')?.enable();
    this.profileForm.get('street')?.enable();
    this.profileForm.get('city')?.enable();
    this.profileForm.get('country')?.enable();
  }
  disableSafe():void{
    this.profileForm.get('name')?.disable();
    this.profileForm.get('surname')?.disable();
    this.profileForm.get('occupation')?.disable();
    this.profileForm.get('companyInfo')?.disable();
    this.profileForm.get('gender')?.disable();
    this.profileForm.get('phoneNumber')?.disable();
    this.profileForm.get('street')?.disable();
    this.profileForm.get('city')?.disable();
    this.profileForm.get('country')?.disable();
  }
}
