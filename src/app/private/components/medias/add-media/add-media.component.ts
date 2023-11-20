import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-add-media',
  templateUrl: './add-media.component.html',
  styleUrls: ['./add-media.component.scss']
})
export class AddMediaComponent implements OnInit {
  newMedia = {
    urlPost: '',
    description: '',
    image: '',
    title: '',
    type: 'Summer_internship',
  };
  @Output() customEvent = new EventEmitter<string>();

  constructor(private mediaService: MediaService) {}

  ngOnInit(): void {}

  emitEvent(data: any) {
    this.customEvent.emit(data);
  }
   addMedia() {
    console.log(this.selectedFile);

    this.mediaService
      .addMedia({ ...this.newMedia, image: this.selectedFile })
      .subscribe((res: any) => {
        console.log(res);
        this.emitEvent(true);
      });
  }
  selectedFile: File | null = null;
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
}