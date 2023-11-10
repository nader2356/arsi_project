import { Component } from '@angular/core';
import { User } from '../user';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent {
  user = new User();
  msg = '';
  constructor(
    private router: Router,
    private service: UserService,
    private messageService: MessageService
  ) {}


  ngOnInit(): void {
  }
  SigninUser(){
    this.service.SigninUserService(this.user).subscribe(
      (res) => {
        console.log(res);
        localStorage.setItem('access_token', res.token);
        this.router.navigate(['/private/formations']);
      },
      (error) => {
        console.log('exception occured'),
          this.messageService.add({
            severity: 'error',
            summary: 'login failed ! please check your email or password',
            detail: 'Please check your information !!!',
          });

  })


  }

  }

