import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { FormationsComponent } from './components/formations/formations.component';
import { EventComponent } from './components/event/event.component';
import { EditEventComponent } from './components/events/edit-event/edit-event.component';


const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children : [
      {
        path: 'user-list',
        component: UserlistComponent,
     },
     {
      path: 'formations',
      component: FormationsComponent
    },
    {
    path: 'admin',
    component: FormationsComponent
  },
  {
   path: 'events',
   component: EventComponent
  },
  {
   path: 'events/:id',
   component: EditEventComponent
  },
   {
    path: '**',
    redirectTo: '',
  },
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
