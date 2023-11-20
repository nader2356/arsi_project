import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-edit-media',
  templateUrl: './edit-media.component.html',
  styleUrls: ['./edit-media.component.scss']
})
export class EditMediaComponent implements OnInit {
  @Output() customEvent = new EventEmitter<string>();

  @Input() media: any = {
    urlPost: '',
    description: '',
    image: '',
    title: '',
    type: '',
  };
  ngOnChanges(changes: SimpleChanges): void {
    // Check for changes to the @Input property
    if (changes['inputData']) {
      const newValue = changes['inputData'].currentValue;
      const previousValue = changes['inputData'].previousValue;
      console.log(this.media);
      // Perform actions based on the changes
      console.log(`InputData changed from ${previousValue} to ${newValue}`);
    }
  }
  constructor(private mediaService: MediaService) {}

  ngOnInit(): void {
    console.log(this.media);
  }

  emitEvent(data: any) {
    this.customEvent.emit(data);
  }
  updateMedia() {
    this.mediaService
      .editMedia({ ...this.media, image: this.selectedFile })
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