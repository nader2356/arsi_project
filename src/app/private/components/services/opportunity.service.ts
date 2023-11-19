import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OpportunityService {
  constructor(private http: HttpClient) {}

  getOpportunities(): Observable<any> {
    return this.http.get('api/arsii/admin/opportunity');
  }

  addOpportunity(data: any): Observable<any> {
    return this.http.post('api/arsii/admin/opportunity', data);
  }

  public editOpportunity(data: any): Observable<any> {
    return this.http.put('api/arsii/admin/opportunity/' + data.id, data);
  }
  public deleteOpportunity(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/opportunity/' + Id);
  }
}