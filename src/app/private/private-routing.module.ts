import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { FormationsComponent } from './components/formations/formations.component';
import { EditEventComponent } from './components/events/edit-event/edit-event.component';
import { EventsComponent } from './components/events/events.component';
import { OpportunityComponent } from './components/opportunity/opportunity.component';
import { ChangeInformationComponent } from './components/change-formations/change-formations.component';


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
        component: FormationsComponent,
      },
      {
        path: 'opportunity',
        component: OpportunityComponent,
      },
      {
        path: 'admin',
        component: FormationsComponent,
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
