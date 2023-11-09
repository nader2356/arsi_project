import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrivateRoutingModule } from './private-routing.module';
import { BaseComponent } from './components/base/base.component';

import { SidenavComponent } from './components/sidenav/sidenav.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { FormationsComponent } from './components/formations/formations.component';
import { AddFormationsModalComponent } from './components/formations/add-formations-modal/add-formations-modal.component';


@NgModule({
  declarations: [
    BaseComponent,
    SidenavComponent,
    UserlistComponent,
    FormationsComponent,
    AddFormationsModalComponent,
    FormationsComponent,
    AddFormationsModalComponent
  ],
  imports: [
    CommonModule,
    PrivateRoutingModule
  ]
})
export class PrivateModule { }
