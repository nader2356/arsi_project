import { Component, OnInit } from '@angular/core';
import { MemberService } from '../services/member.service';
import { Opportunity } from '../types/opportunity';
import { Popover } from 'bootstrap';
import { OpportunityService } from '../services/opportunity.service';
import { MessageService } from 'primeng/api';
@Component({
  selector: 'app-opportunity',
  templateUrl: './opportunity.component.html',
  styleUrls: ['./opportunity.component.scss']
})
export class OpportunityComponent implements OnInit {

  opportunities: any[] = [];
  selectedOpportunity = {
    company: '',
    description: '',
    image: '',
    title: '',
    type: 'Summer_internship',
  };
  constructor(
    private opportunityService: OpportunityService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    Array.from(document.querySelectorAll('a[data-toggle="popover"]')).forEach(
      (popverMode) => new Popover(popverMode)
    );
    this.getAllOpportunity();
  }

  private getAllOpportunity() {
    this.opportunityService.getOpportunities().subscribe((data: any) => {
      console.log('data', data);
      this.opportunities = data;
    })
  }
  goToEditPage() {}

  addopportunityCheck(data: any) {
    if (data) {
      this.getAllOpportunity();
      this.messageService.add({
        severity: 'success',
        summary: 'opporunity added Successfully',
      });
    } else {
      console.log('no');
    }
  }
  editopportunityCheck(data: any) {
    if (data) {
      this.getAllOpportunity();
      this.messageService.add({
        severity: 'success',
        summary: 'opporunity updated Successfully',
      });
    } else {
      console.log('no');
    }
  }
  selectOpportunity(data: any) {
    this.selectedOpportunity = data;

  }
  deleteOpportunity(opporunity: any) {
    this.opportunityService
      .deleteOpportunity(opporunity.id)
      .subscribe((res: any) => {
        console.log(res);
        this.getAllOpportunity();
        this.messageService.add({
          severity: 'success',
          summary: 'opporunity deleted Successfully',
        });
      });
  }
}
