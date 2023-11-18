import { Component } from '@angular/core';
import { UserService } from '../userService/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'interfaces/user';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent {
  userId: number
  user: any = {}

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
   }


  ngOnInit(): void {
    this.getUserById();
  }

  // use this when skander adds filter by id in the getAllUserByFilter request
  // private getUserById(){
  //   const userId = this.route.snapshot.paramMap.get('id');

  //   let filter = {"id": userId}
  //   this.userService.getUserList(filter).subscribe(data => {
  //     console.log('data', data);
  //     this.users = data;
  //   })
  // }

  private getUserById(){
    const filter = {"role": "MEMBER"}

    this.userService.getUserList(filter).subscribe(data => {
      let content = data.content
      const filteredUser = content.filter((user: User) => {
        return user.id == this.userId
      })
      this.user = filteredUser[0];
    })
  }


  updateUser(userData: any) {
    this.userService.updateUser(this.userId, userData).subscribe((data) => {
      this.router.navigate(['/private/user-list']);
    })
  }

  goBack() {
    this.router.navigate(['/private/user-list']);
  }


}
