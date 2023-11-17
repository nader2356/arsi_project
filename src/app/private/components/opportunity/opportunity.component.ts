import { Component, OnInit } from '@angular/core';
import { MemberService } from '../services/member.service';
import { Opportunity } from '../types/opportunity';
import { Popover } from 'bootstrap';
@Component({
  selector: 'app-opportunity',
  templateUrl: './opportunity.component.html',
  styleUrls: ['./opportunity.component.scss']
})
export class OpportunityComponent implements OnInit {

  opportunities: Opportunity[] = [];

  constructor(private memberService: MemberService) { }

  ngOnInit(): void {
    Array.from(document.querySelectorAll('a[data-toggle="popover"]'))
    .forEach(popverMode => new Popover(popverMode));
    this.getAllOpportunity();
  }

  private getAllOpportunity(){
    this.memberService.getAllOpportunity().subscribe((data:any) => {
      console.log('data', data);
      this.opportunities = data;
    })
  }
  goToEditPage() {

  }
}
