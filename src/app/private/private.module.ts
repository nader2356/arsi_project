import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrivateRoutingModule } from './private-routing.module';
import { BaseComponent } from './components/base/base.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { ChangeInformationComponent} from './components/change-formations/change-formations.component';
import { EditEventComponent } from './components/events/edit-event/edit-event.component';
import { AddEventComponent } from './components/events/add-event/add-event.component';
import { MemberService } from './components/services/member.service';
import { AddOpportunityComponent } from './components/opportunity/add-opportunity/add-opportunity.component';
import { EditOpportunityComponent } from './components/opportunity/edit-opportunity/edit-opportunity.component';
import { OpportunityComponent } from './components/opportunity/opportunity.component';
import { SkillsComponent } from './components/skills/skills.component';
import { AddUserComponent } from './components/user-list/add-user/add-user.component';
import { EditUserComponent } from './components/user-list/edit-user/edit-user.component';
import { UserListComponent } from './components/user-list/userlist.component';




@NgModule({
  declarations: [
    BaseComponent,
    SidenavComponent,
    UserListComponent,
    ChangeInformationComponent,
    SkillsComponent,
    EditEventComponent,
    AddEventComponent,
    OpportunityComponent,
    AddOpportunityComponent,
    EditOpportunityComponent,
    SkillsComponent,
    AddUserComponent,
    EditUserComponent
  ],
  imports: [CommonModule, PrivateRoutingModule, FormsModule,ReactiveFormsModule],
  providers: [MemberService],
})
export class PrivateModule { }
