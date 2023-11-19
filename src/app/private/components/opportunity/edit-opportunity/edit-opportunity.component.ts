import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { OpportunityService } from '../services/opportunity.service';

@Component({
  selector: 'app-edit-opportunity',
  templateUrl: './edit-opportunity.component.html',
  styleUrls: ['./edit-opportunity.component.scss']
})
export class EditOpportunityComponent implements OnInit, OnChanges {
  @Output() customEvent = new EventEmitter<string>();


  @Input() opportunity: any = {
    company: '',
    description: '',
    image: '',
    title: '',
    type: 'Summer_internship',
  };

  ngOnChanges(changes: SimpleChanges): void {
    // Check for changes to the @Input property
    if (changes['inputData']) {
      const newValue = changes['inputData'].currentValue;
      const previousValue = changes['inputData'].previousValue;

      // Perform actions based on the changes
      console.log(`InputData changed from ${previousValue} to ${newValue}`);
    }
  }
  constructor(private opportunityService: OpportunityService) {}

  ngOnInit(): void {}

  editOpportunity() {}
  emitEvent(data: any) {
    this.customEvent.emit(data);
  }
  updateOpportunity() {
    this.opportunityService
      .editOpportunity(this.opportunity)
      .subscribe((res: any) => {
        console.log(res);
        this.emitEvent(true);
      });
  }
}
