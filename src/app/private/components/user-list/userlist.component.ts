import { Component, OnInit } from '@angular/core';
import { User } from 'interfaces/user';

import { Router } from '@angular/router';
import { UserService } from './userService/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  
  confirmDeleteId:number
  users: User[] = [];


  constructor(private userService: UserService, private router: Router) {
    this.confirmDeleteId = 0;
  }

  ngOnInit(): void {
    this.getUsers();
  }

  redirectToNewUsertPage(){
    this.router.navigate(['/private/addUser']);
  }

  redirectToEditPage(userId: number | undefined) {
    console.log("userId", userId);
    if(userId !== undefined) {
      this.router.navigate(['/private/editUser', userId]);
    }
  }


  private getUsers(){
    let filter = {
      "role": "MEMBER"
    }
    this.userService.getUserList(filter).subscribe(data => {
      console.log('data', data.content);
      this.users = data.content;
    })
  }

 
  onEnableUser(userId: number | undefined) {
    if(userId !== undefined) {
      this.userService.enableUser(userId).subscribe(
        (response) => {
          if(response == "This Account enabled with success !!!!!")
              this.getUsers();
        }
      )
    }
  }

  onDeleteAccount(userId: number | undefined) {
    if(userId !== undefined) {
      this.userService.deleteUser(userId).subscribe(
        (response) => {
          if(response == "this Account is deleted"){
            this.getUsers();
          }
        } )
      }
    }
  
  }