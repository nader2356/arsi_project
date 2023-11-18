import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'interfaces/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = '/api/arsii/';

  constructor(private httpClient: HttpClient) {}


  getUserList(filter: object): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}admin/filter`, filter);
  }

  // serveImage(imageFile: any): Observable<any>{
  //   return this.httpClient.get(this.baseUrl+'admin/img/'+ imageFile)
  // }

  addUser(userData: User): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}auth/register`, userData);
  }

  updateUser(id:number, updatedUser:User): Observable<any>{
    return this.httpClient.put(this.baseUrl + 'admin/' + id, updatedUser, {responseType: 'text'})
  }

  enableUser(id: number | undefined): Observable<any> {
    return this.httpClient.put(this.baseUrl + 'admin/enable/' + id, {}, {responseType: 'text'})
  }

  deleteUser(id: number | undefined): Observable<any> {
    return this.httpClient.delete(this.baseUrl + 'admin/' + id, {responseType: 'text'}) //`${this.baseUrl}admin/${id}`
  }
}