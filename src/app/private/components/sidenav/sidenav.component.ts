import { Component } from '@angular/core';
import { MemberService } from '../services/member.service';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent {
  userData: any = null;
  constructor(
    private membreService: MemberService,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.displayCurrentUser();
  }
  displayCurrentUser() {
    this.membreService.getUserById().subscribe((data) => {
      console.log(data);

      this.userData = data;
    });
  }

  logout() {
    this.authService.logout();
  }
}
