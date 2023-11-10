import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { Contact } from '../types/contact';
import { Email } from '../types/email';
import { Password } from '../types/password';
import { UserUpdate } from '../types/user-update';



@Component({
  selector: 'app-change-information',
  templateUrl: './change-information.component.html',
  styleUrls: ['./change-information.component.scss']
})
export class ChangeInformationComponent {
  user_update = new UserUpdate();
  password_update = new Password();
  email = new Email();
  contact = new Contact();
  msg = '';

  constructor(private  service :UserService,private router:Router) { }

  ngOnInit(): void {
  }

  ChangeUser(){
    this.service.InformationService(this.user_update).subscribe(
      data=>{console.log(this.user_update);
        console.log(this.user_update);
        this.msg = 'Registration successfully';
        this.user_update = new UserUpdate();
        alert(this.msg);
        this.router.navigate(['/auth/signin']);

      },
      (error) => {
        console.log('Registration failed'), (this.msg = error.error);
        this.user_update = new UserUpdate();
      }
    );
  }

  ChangePassword(){
    this.service.PasswordService(this.password_update).subscribe(
      (data) => {
        console.log(this.password_update);
        this.msg = 'Password is changed successfully';
        this.password_update = new Password();
        alert(this.msg);
      },
      (error) => {
        console.log('Change password failed'), (this.msg = error.error);
        this.password_update = new Password();
      }
    );
  }


  EmailNotification(){
    this.service.EmailService(this.email).subscribe(
      (data) => {
        console.log(this.password_update);
        this.msg = 'Email sended';
        this.email = new Email();
        alert(this.msg);
      },
      (error) => {
        console.log('Eroor'), (this.msg = error.error);
        this.email = new Email();
      }
    );
  }

  ContactForm() {
    this.service.ContactService(this.contact).subscribe(
      (data) => {
        console.log(this.user_update);
        this.msg = 'Message send';
        this.contact = new Contact();
        alert(this.msg);
      },
      (error) => {
        this.msg = error.error;
        this.contact = new Contact();
      }
    );
  }
}
