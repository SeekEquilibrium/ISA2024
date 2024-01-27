import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/AuthService';
import {ActivatedRoute} from "@angular/router";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-verify-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './verify-modal.component.html',
  styleUrl: './verify-modal.component.css',
  providers:[AuthService]
})
export class VerifyModalComponent implements OnInit {
 
  error: string="";
  verifiedSuccessfully: boolean | null = null;
  constructor(
    private authService:AuthService,
    private route:ActivatedRoute,
  ){}
  
  ngOnInit(): void {
    this.authService.verify(this.route.snapshot.paramMap.get('email') as string).subscribe(
      (data) => {
        console.log(data);
          this.verifiedSuccessfully = true;
      },
      (error) => {
      this.verifiedSuccessfully = false;
      console.log(error)
        if(error.status == 404) {
          this.error = "Account does not exist.";
        } else if(error.status == 403) {
          this.error = "Account already verified.";
        }
      });
  }

}
