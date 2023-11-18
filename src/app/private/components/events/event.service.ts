import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Event } from 'interfaces/event';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private httpClient: HttpClient) { }

  private baseUrl = '/api/arsii/';

  addEvent(eventData: Event): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}admin/event`, eventData);
  }

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
}