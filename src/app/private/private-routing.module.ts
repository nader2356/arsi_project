import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import { UserlistComponent } from './components/userlist/userlist.component';


const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children : [
      {
        path: 'user-list',
        component: UserlistComponent
      }
    ]
  },
  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
