import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'interfaces/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8081/api/arsii/admin/me'

  constructor(private httpClient: HttpClient) { }

  getUserList(): Observable<User[]>{
    return this.httpClient.post<User[]>(`${this.baseUrl}`, {});
  }
}