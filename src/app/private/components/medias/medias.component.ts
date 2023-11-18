import { Component, OnInit } from '@angular/core';
import { MemberService } from '../services/member.service';
import { Media } from '../types/media';
import { Popover } from 'bootstrap';

@Component({
  selector: 'app-medias',
  templateUrl: './medias.component.html',
  styleUrls: ['./medias.component.scss']
})
export class MediasComponent  implements OnInit {

  medias: Media[] = [];
  constructor(private memberService: MemberService) { }

  ngOnInit(): void {
    Array.from(document.querySelectorAll('a[data-toggle="popover"]'))
    .forEach(popverMode => new Popover(popverMode));
    this.getAllMedia();
  }

  private getAllMedia(){
    this.memberService.getAllMedia().subscribe((data:any) => {
      console.log('data', data);
      this.medias = data;
    })
  }
  goToEditPage() {

  }
}