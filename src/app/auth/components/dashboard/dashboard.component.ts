import { Component } from '@angular/core';
import { Email } from '../email';
import { Contact } from '../contact';
import { UserUpdate } from '../user-update';
import { Password } from '../password';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  user_update=new UserUpdate();
  password_update:Password|undefined;
  email=new Email();
  contact=new Contact();
  msg="";

  constructor(private  service :UserService,private router:Router) { }

  ngOnInit(): void {
  }

  ChangeUser(){
    this.service.InformationService(this.user_update).subscribe(
      data=>{console.log(this.user_update);
        this.msg="Registration successfully";
        this.user_update=new UserUpdate();
        alert(this.msg)
        this.router.navigate(['/auth/signin']);

      },error=>{
        console.log("Registration failed"),
        this.msg=error.error;
        this.user_update=new UserUpdate();
      });
  }




  EmailNotification(){
    this.service.EmailService(this.email).subscribe(
      data=>{console.log(this.password_update);
        this.msg="Email sended";
        this.email=new Email();
        alert(this.msg)

      },error=>{
        console.log("Eroor"),
        this.msg=error.error;
        this.email=new Email();
      });
    }

      ContactForm(){
        this.service.ContactService(this.contact).subscribe(
          data=>{console.log(this.user_update);
            this.msg="Message send";
            this.contact=new Contact();
            alert(this.msg)

          },error=>{
            this.msg=error.error;
            this.contact=new Contact();
          });

      }
  }


