import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../types/user';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  user = new User();
  msg = '';
  ListOffice=[
    {  value:'OFFICE_SFAX',title:'SFAX'},
    {  value:'OFFICE_SOUSSE',title:'SOUSSE'},
    {  value:'OFFICE_TUNIS',title:'TUNIS'}
    ]
  constructor(
    private router: Router,
    private service: UserService,
    private messageService: MessageService
  ) {}



  ngOnInit(): void {
  }
  SignupUser(){
    this.service.SignupUserService(this.user).subscribe(
      (data) => {
        console.log(this.user);
        this.msg = 'Registration successfully';
        this.user = new User();
        this.messageService.add({
          severity: 'success',
          summary: 'Registration successfully',
          detail: 'goal',
        });
        this.router.navigate(['/auth/signin']);

      },
      (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Registration failed',
          detail:error.error,
        });
        console.log('Registration failed'), (this.msg = error.error);
        //  this.user = new User();
      }
    );
  }
}
