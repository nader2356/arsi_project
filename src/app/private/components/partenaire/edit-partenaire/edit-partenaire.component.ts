import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { PartenaireService } from '../../services/partenaire.service';

@Component({
  selector: 'app-edit-partenaire',
  templateUrl: './edit-partenaire.component.html',
  styleUrls: ['./edit-partenaire.component.scss']
})
export class EditPartenaireComponent implements OnInit {
  @Output() customEvent = new EventEmitter<string>();

  @Input() partenaire: any = {
    address: '',
    contact: '',
    description: '',
    image: '',
    name: '',
    type: '',
  };
  ngOnChanges(changes: SimpleChanges): void {
    // Check for changes to the @Input property
    if (changes['inputData']) {
      const newValue = changes['inputData'].currentValue;
      const previousValue = changes['inputData'].previousValue;
      console.log(this.partenaire);
      // Perform actions based on the changes
      console.log(`InputData changed from ${previousValue} to ${newValue}`);
    }
  }
  constructor(private partenaireService: PartenaireService) {}

  ngOnInit(): void {
    console.log(this.partenaire);
  }

  emitEvent(data: any) {
    this.customEvent.emit(data);
  }

  updatePartenaire() {
    this.partenaireService
      .editPartenaire({ ...this.partenaire, image: this.selectedFile })
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
