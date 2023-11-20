import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, mergeMap } from 'rxjs';
import { MemberService } from './member.service';

@Injectable({
  providedIn: 'root',
})
export class MediaService {
  constructor(private http: HttpClient, private memberService: MemberService) {}

  getOpportunities(): Observable<any> {
    return this.http.get('api/arsii/admin/media');
  }

  // addMedia(data: any): Observable<any> {
  //   return this.http.post('api/arsii/admin/media', data);
  // }

  addMedia(data: any) {
    if (data.image) {
      return this.memberService.uploadImage(data.image).pipe(
        mergeMap((result: any) => {
          const reqData = {
            ...data,
            image: result.file,
          };
          return this.http.post(`api/arsii/admin/media`, reqData);
        })
      );
    } else {
      return this.http.post(`api/arsii/admin/media`, data);
    }
  }

  // public editMedia(data: any): Observable<any> {
  //   return this.http.put('api/arsii/admin/media/' + data.id, data);
  // }

  editMedia(data: any) {
    if (data.image) {
      return this.memberService.uploadImage(data.image).pipe(
        mergeMap((result: any) => {
          const reqData = {
            ...data,
            image: result.file,
          };
          return this.http.put('api/arsii/admin/media/' + reqData.id, reqData);
        })
      );
    } else {
      return this.http.put('api/arsii/admin/media/' + data.id, data);
    }
  }

  public deleteMedia(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/media/' + Id);
  }
}