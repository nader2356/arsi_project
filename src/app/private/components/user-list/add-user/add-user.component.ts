import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../userService/user.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent {
  constructor(private router: Router, private userService: UserService) { }
  userForm!: FormGroup;


  newUser: any = {};

  ngOnInit(): void {

  }

  onSubmit(userData: any) {
    this.userService.addUser(userData).subscribe((response) => {
      console.log('response', response);
      if(response) {
        this.router.navigate(['/private/user-list']);
      }
    })
  }

  goBack() {
    this.router.navigate(['/private/user-list']);
  }


}
