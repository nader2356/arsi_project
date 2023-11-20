import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Popover } from 'bootstrap';
import { EventService } from '../services/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  events: Event[] = [];

  constructor(private eventService:EventService,  private router: Router) { }

  ngOnInit(): void {

    this.getAllEvents();

    Array.from(document.querySelectorAll('a[data-toggle="popover"]'))
      .forEach(popverMode => new Popover(popverMode));
  }

 private getAllEvents() {
    this.eventService.getAllEvents().subscribe(data => {
      this.events = data;
    })
  }

  goToAddEvent() {
    this.router.navigate(['/private/addEvent']);
  }

  goToEditPage(eventId: number | undefined) {
    if (eventId != undefined) {
      this.router.navigate(['/private/events', eventId]);
    }
  }

  deleteEvent(eventId: number | undefined) {
    console.log('eventId', eventId);
    if(eventId != undefined) {
      this.eventService.deleteEvent(eventId).subscribe((data) => {
        this.getAllEvents();
      });
    }

  }

}