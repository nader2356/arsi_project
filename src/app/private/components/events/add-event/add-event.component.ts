import { Component, OnInit } from '@angular/core';
import { EventService } from '../event.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {



  constructor(private eventService: EventService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(eventData: Event) {
    this.eventService
    .updateEvent(this.eventId, {
      ...eventData,
      date: new Date(eventData.date),
      maxOfParticipants: Number(eventData.maxOfParticipants),
      price: Number(eventData.price),
    })
    .subscribe((data) => {
      console.log(data);
      this.router.navigate(['/private/events']);
    });
  }

  goBack() {
    this.router.navigate(['/private/events']);
  }

}