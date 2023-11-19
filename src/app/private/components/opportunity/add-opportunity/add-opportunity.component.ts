import { Component, EventEmitter, Output } from '@angular/core';
import { OpportunityService } from '../../services/opportunity.service';

@Component({
  selector: 'app-add-opportunity',
  templateUrl: './add-opportunity.component.html',
  styleUrls: ['./add-opportunity.component.scss']
})
export class AddOpportunityComponent implements OnInit {
  newOpportunity = {
    company: '',
    description: '',
    image: '',
    title: '',
    type: 'Summer_internship',
  };

  @Output() customEvent = new EventEmitter<string>();


  constructor(private opportunityService: OpportunityService) {}


  ngOnInit(): void {}

  emitEvent(data: any) {
    this.customEvent.emit(data);
  }

  addOpportunity() {
    this.opportunityService
      .addOpportunity(this.newOpportunity)
      .subscribe((res: any) => {
        console.log(res);
        this.emitEvent(true);
      });
  }
}
