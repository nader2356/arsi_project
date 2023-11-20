import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.scss']
})
export class EditEventComponent implements OnInit {

  eventId: number = 0;
  event: any = {};
  imageEvent: any;

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.getEventById(this.eventId);
  }

  private getEventById(eventId: number) {
    this.eventService.getEventById(eventId).subscribe((data) => {
      let date_only = data.date.split('T')[0];
      data.date = date_only;
      this.event = data;
      this.imageEvent = data.image;
      this.event.image = null;
    })
  }

  updateEvent(eventData: any) {
    this.eventService
    .updateEventImg(this.eventId, {
      ...eventData,
      date: new Date(eventData.date),
      maxOfParticipants: Number(eventData.maxOfParticipants),
      price: Number(eventData.price),
      image: this.selectedFile,
    })
    .subscribe((response) => {
      if (response) {
        this.router.navigate(['/private/events']);
      }
    });
  }

  goBack() {
    this.router.navigate(['/private/events']);

  }

  selectedFile: File | null = null;
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

}
