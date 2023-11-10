import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    Array.from(document.querySelectorAll('a[data-toggle="popover"]'))
      .forEach(popverMode => new Popover(popverMode));
  }

  goToEditPage() {

  }

}