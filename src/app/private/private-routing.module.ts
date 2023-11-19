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
import { SkillsComponent } from './components/skills/skills.component';
import { AddEventComponent } from './components/events/add-event/add-event.component';


const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children: [
     {
      path: 'user-list',
      component: UserListComponent,
     },
     {
      path: 'media',
      component: MediasComponent
     },
     {
      path: 'events',
      component: EventsComponent
     },
     {
      path: 'events/:id',
      component: EditEventComponent
     },

     {
      path: '**',
      redirectTo: '',
     },
      {
        path: 'skills',
        component: SkillsComponent,
      },
      {
        path: 'opportunity',
        component: OpportunityComponent,
      },
      {
        path: 'change',
        component: ChangeInformationComponent,
      },
      {
        path: 'addUser',
        component: AddUserComponent,
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
        path: 'addEvent',
        component: AddEventComponent,
      },
      // {
      //   path: 'editEvent/:id',
      //   component: EditEventComponent,
      // },

      {
        path: '**',
        redirectTo: '',
      },
    ]}
    ]
 


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
