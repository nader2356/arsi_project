import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PartenaireService } from '../../services/partenaire.service';

@Component({
  selector: 'app-add-partenaire',
  templateUrl: './add-partenaire.component.html',
  styleUrls: ['./add-partenaire.component.scss']
})
export class AddPartenaireComponent  implements OnInit {
  newPartenaire = {
    address: '',
    contact: '',
    description: '',
    image: '',
    name: '',
    type: '',
  };
  @Output() customEvent = new EventEmitter<string>();

  constructor(private partenaireService: PartenaireService) {}

  ngOnInit(): void {}

  emitEvent(data: any) {
    this.customEvent.emit(data);
  }

  addPartenaire() {
    console.log(this.selectedFile);

    this.partenaireService
      .addPartenaire({ ...this.newPartenaire, image: this.selectedFile })
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
