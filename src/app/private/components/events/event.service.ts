import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Event } from 'interfaces/event';
import { Observable, mergeMap } from 'rxjs';
import { MemberService } from '../services/member.service';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private httpClient: HttpClient,private membreService: MemberService) { }

  private baseUrl = '/api/arsii/';
  
 // addEvent(eventData: Event): Observable<any> {
  //   return this.httpClient.post(`${this.baseUrl}admin/event`, eventData);
  // }

  getAllEvents(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}admin/event?type=Formation`);
  }

  getEventById(eventId: Number): Observable<any>{
    return this.httpClient.get(this.baseUrl + 'admin/event/' + eventId)
  }

  updateEvent(eventId:number, updatedEvent:Event): Observable<any>{
    return this.httpClient.put(this.baseUrl + 'admin/event/' + eventId, updatedEvent, {responseType: 'text'})
  }

  deleteEvent(id: number | undefined): Observable<any> {
    return this.httpClient.delete(this.baseUrl + 'admin/event/' + id, {responseType: 'text'}) //`${this.baseUrl}admin/${id}`
  }
  

  addEvent(data: any) {
    return this.membreService.uploadImage(data.image).pipe(
      mergeMap((result: any) => {
        const reqData = {
          ...data,
          image: result.file,
        };
        return this.httpClient.post(`${this.baseUrl}admin/event`, reqData);
      })
    );
  }
  
}