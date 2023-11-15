import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'interfaces/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = '/api/arsii/';

  constructor(private httpClient: HttpClient) { }

  getCurrentUser(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}admin/me`);
  }
  

  getUserList(): Observable<User[]>{
    return this.httpClient.post(`${this.baseUrl}admin/filter`, {"role": "MEMBER"});
  }

  enableUser(id: number): Observable<any> {
    return this.httpClient.put(this.baseUrl + 'admin/enable/' + id, {}, {responseType: 'text'})
  }

  disableUser(id: number): Observable<any> {
    return this.httpClient.put(this.baseUrl + 'admin/disable/' + id, {}, {responseType: 'text'})
  }

  deleteUser(id: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl + 'admin/' + id, {responseType: 'text'}) //`${this.baseUrl}admin/${id}`
  }
}