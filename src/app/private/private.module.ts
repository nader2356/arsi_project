import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrivateRoutingModule } from './private-routing.module';
import { BaseComponent } from './components/base/base.component';

import { SidenavComponent } from './components/sidenav/sidenav.component';
import { UserlistComponent } from './components/userlist/userlist.component';


@NgModule({
  declarations: [
    BaseComponent,
    SidenavComponent,
    UserlistComponent
  ],
  imports: [
    CommonModule,
    PrivateRoutingModule
  ]
})
export class PrivateModule { }
