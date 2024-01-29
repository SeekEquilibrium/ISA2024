import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiService } from './ApiService';
import { ConfigService } from './ConfigService';
import { Observable } from 'rxjs';
import { EditEmployee, Employee } from '../User';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private apiService: ApiService, private config: ConfigService) {}

  getCurrentEmployee(): Observable<Employee> {
    return this.apiService.get(this.config.employee_url +'/current');
  }
  editEmployee(EmployeeDTO:EditEmployee): Observable<any>{
    return this.apiService.put(this.config.employee_url + '/edit',EmployeeDTO);
  }
}
