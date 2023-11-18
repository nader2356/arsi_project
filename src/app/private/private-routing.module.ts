import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import { UserListComponent } from './components/user-list/userlist.component';
import { EditEventComponent } from './components/events/edit-event/edit-event.component';
import { EventsComponent } from './components/events/events.component';
import { OpportunityComponent } from './components/opportunity/opportunity.component';
import { ChangeInformationComponent } from './components/change-formations/change-formations.component';
import { MediasComponent } from './components/medias/medias.component';
import { AddUserComponent } from './components/user-list/add-user/add-user.component';
import { EditUserComponent } from './components/user-list/edit-user/edit-user.component';


const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children : [
      {
        path: 'user-list',
        component: UserListComponent,
      },
      {
        path: 'addUser',
        component: AddUserComponent,
      },
      {
        path: 'opportunity',
        component: OpportunityComponent,
      },
      {
        path: 'media',
        component: MediasComponent
       },
      {
        path: 'editUser/:id',
        component: EditUserComponent,
      },
      {
        path: 'events',
        component: EventsComponent,
      },
      {
        path: 'events/:id',
        component: EditEventComponent,
      },
      {
        path: 'change',
        component: ChangeInformationComponent,
      },

      {
        path: '**',
        redirectTo: '',
      },
    ],
  },
];
 


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
