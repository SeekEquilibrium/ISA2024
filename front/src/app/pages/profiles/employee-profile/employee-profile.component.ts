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

@Component({
  selector: 'app-employee-profile',
  standalone: true,
  imports: [
    FormsModule, 
    ReactiveFormsModule,
    MatCardModule,
    CommonModule
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
    City: [],
    Country: [],
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
  }
}
