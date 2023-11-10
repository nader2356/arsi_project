import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { PrivateRoutingModule } from './private-routing.module';
import { BaseComponent } from './components/base/base.component';
import { FormsModule } from '@angular/forms';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { FormationsComponent } from './components/formations/formations.component';
import { AddFormationsModalComponent } from './components/formations/add-formations-modal/add-formations-modal.component';
import { ChangeInformationComponent} from './components/change-formations/change-formations.component';


import { EventComponent } from './components/event/event.component';
import { UserService } from './components/services/user.service';
import { EventsComponent } from './components/events/events.component';
import { EditEventComponent } from './components/events/edit-event/edit-event.component';
import { AddEventComponent } from './components/events/add-event/add-event.component';




@NgModule({
  declarations: [
    BaseComponent,
    SidenavComponent,
    UserlistComponent,
    FormationsComponent,
    AddFormationsModalComponent,
    ChangeInformationComponent,
    EventComponent,
    EditEventComponent,
    AddEventComponent,
    EventsComponent,
  ],
  imports: [CommonModule, PrivateRoutingModule, FormsModule, HttpClientModule],
  providers: [UserService],
})
export class PrivateModule { }
