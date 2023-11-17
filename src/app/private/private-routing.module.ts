import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { FormationsComponent } from './components/formations/formations.component';

import { EditEventComponent } from './components/events/edit-event/edit-event.component';
import { EventsComponent } from './components/events/events.component';
import { OpportunityComponent } from './components/opportunity/opportunity.component';


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
    path: 'opportunity',
    component: OpportunityComponent
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
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrivateRoutingModule { }
