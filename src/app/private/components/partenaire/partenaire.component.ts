import { Component, OnInit } from '@angular/core';
import { PartenaireService } from '../services/partenaire.service';
import { Popover } from 'bootstrap';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-partenaire',
  templateUrl: './partenaire.component.html',
  styleUrls: ['./partenaire.component.scss']
})
export class PartenaireComponent implements OnInit {
  partenaire: any[] = [];
  selectedPartenaire = {
    urlPost: '',
    description: '',
    image: '',
    title: '',
    type: '',
  };
  constructor(
    private mediaService: PartenaireService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    Array.from(document.querySelectorAll('a[data-toggle="popover"]')).forEach(
      (popverMode) => new Popover(popverMode)
    );
    this.getAllPartenaire();
  }

  private getAllPartenaire() {
    this.mediaService.getOpportunities().subscribe((data: any) => {
      console.log('data', data);
      this.partenaire = data;
    });
  }
  goToEditPage() {}

  addmediaCheck(data: any) {
    if (data) {
      this.getAllPartenaire();
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
      this.getAllPartenaire();
      this.messageService.add({
        severity: 'success',
        summary: 'media updated Successfully',
      });
    } else {
      console.log('no');
    }
  }
  selectPartenaire(data: any) {
    this.selectedPartenaire = data;
  }

  deletePartenaire(media: any) {
    this.mediaService.deletePartenaire(media.id).subscribe((res: any) => {
      console.log(res);
      this.getAllPartenaire();
      this.messageService.add({
        severity: 'success',
        summary: 'media deleted Successfully',
      });
    });
  }
}