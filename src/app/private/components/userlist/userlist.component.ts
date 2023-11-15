import { Component } from '@angular/core';
import { User } from 'interfaces/user';
import { UserService } from 'services/userService/user.service';



@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserlistComponent {
  onDelete:boolean
  users: User[] = [];

  constructor(private userService: UserService) {
    this.onDelete = false
  }
  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(){
    this.userService.getUserList().subscribe(data => {
   
      this.users = data;
    })
  }
  onEnableUser(userId: number) {
    this.userService.enableUser(userId).subscribe(
      (response) => {
        if(response == "This Account enabled with success !!!!!")
        window.location.reload();
      }
    )
  }

  onDisableUser(userId: number) {
    this.userService.disableUser(userId).subscribe(
      (response) => {
        if(response == "This Account is disabled !!!!!")
        window.location.reload();
      }
    )
  }

  onDeleteAccount(userId: number) {
    this.userService.deleteUser(userId).subscribe(
      (response) => {
        if(response == "this Account is deleted"){
          window.location.reload();
        }
      }
    )
  }
  
}
