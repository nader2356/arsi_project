import { Component, OnInit } from '@angular/core';
import { MemberService } from '../services/member.service';
import { Media } from '../types/media';
import { Popover } from 'bootstrap';
import { MediaService } from '../services/media.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-medias',
  templateUrl: './medias.component.html',
  styleUrls: ['./medias.component.scss']
})
export class MediasComponent  implements OnInit {

  medias: any[] = [];
  selectedMedia = {
    urlPost: '',
    description: '',
    image: '',
    title: '',
    type: '',
  };
  constructor(
    private mediaService: MediaService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    Array.from(document.querySelectorAll('a[data-toggle="popover"]')).forEach(
      (popverMode) => new Popover(popverMode)
    );
  }

  private getAllMedia() {
    this.mediaService.getOpportunities().subscribe((data: any) => {
      console.log('data', data);
      this.medias = data;
    })
  }
  goToEditPage() {}
  
  addmediaCheck(data: any) {
    if (data) {
      this.getAllMedia();
      this.messageService.add({
        severity: 'success',
        summary: 'media added Successfully',
      });
    } else {
      console.log('no');
    }
  }
  editmediaCheck(data: any) {
    if (data) {
      this.getAllMedia();
      this.messageService.add({
        severity: 'success',
        summary: 'media updated Successfully',
      });
    } else {
      console.log('no');
    }
  }
  selectMedia(data: any) {
    this.selectedMedia = data;
  }
  deleteMedia(media: any) {
    this.mediaService.deleteMedia(media.id).subscribe((res: any) => {
      console.log(res);
      this.getAllMedia();
      this.messageService.add({
        severity: 'success',
        summary: 'media deleted Successfully',
      });
    });
  }
}