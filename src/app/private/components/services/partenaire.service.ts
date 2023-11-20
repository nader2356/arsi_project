import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MemberService } from './member.service';
import { Observable, mergeMap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PartenaireService {
  constructor(private http: HttpClient, private memberService: MemberService) {}

  getOpportunities(): Observable<any> {
    return this.http.get('api/arsii/admin/partner');
  }

  // addPartenaire(data: any): Observable<any> {
  //   return this.http.post('api/arsii/admin/partner', data);
  // }

  addPartenaire(data: any) {
    if (data.image) {
      return this.memberService.uploadImage(data.image).pipe(
        mergeMap((result: any) => {
          const reqData = {
            ...data,
            image: result.file,
          };
          return this.http.post(`api/arsii/admin/partner`, reqData);
        })
      );
    } else {
      return this.http.post(`api/arsii/admin/partner`, data);
    }
  }

  // public editPartenaire(data: any): Observable<any> {
  //   return this.http.put('api/arsii/admin/partner/' + data.id, data);
  // }

  editPartenaire(data: any) {
    if (data.image) {
      return this.memberService.uploadImage(data.image).pipe(
        mergeMap((result: any) => {
          const reqData = {
            ...data,
            image: result.file,
          };
          return this.http.put(
            'api/arsii/admin/partner/' + reqData.id,
            reqData
          );
        })
      );
    } else {
      return this.http.put('api/arsii/admin/partner/' + data.id, data);
    }
  }

  public deletePartenaire(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/partner/' + Id);
  }
}