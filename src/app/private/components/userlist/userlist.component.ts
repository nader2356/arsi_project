import { Component } from '@angular/core';
import { User } from 'interfaces/user';
import { UserService } from 'services/userService/user.service';



@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserlistComponent {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(){
    this.userService.getUserList().subscribe(data => {
      this.users = data;
    })
  }
}
