import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from '../auth/components/signin/signin.component';
import { SignupComponent } from '../auth/components/signup/signup.component';
import { RecoverPasswordComponent } from '../auth/components/recover-password/recover-password.component';


const routes: Routes = [
  {
    path: 'signin',
    component : SigninComponent
  },
  {
    path: 'signup',
    component : SignupComponent
  },
  {
    path: 'reset',
    component: RecoverPasswordComponent,
  },
 
  {
    path: '**',
    redirectTo: ''
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
