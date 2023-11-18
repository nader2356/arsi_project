import { Component, OnInit } from '@angular/core';
import { EventService } from '../event.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {

  newEvent: any = {

  };

  constructor(private eventService: EventService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(eventData: Event) {
    this.eventService.addEvent({...eventData, date: new Date(eventData.date)}).subscribe((response) => {
      console.log('response', response);
      if(response) {
        this.router.navigate(['/private/events']);
      }
    })
  }

  goBack() {
    this.router.navigate(['/private/events']);
  }

}