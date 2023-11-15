
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../components/types/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}
  public SignupUserService(user: User): Observable<any> {

    return this.http.post<any>('/api/arsii/auth/register', user);
  }
  public SigninUserService(user: User): Observable<any> {
   
    return this.http.post<any>('/api/arsii/auth/authenticate', user);
  }
  public ResetPasswordService(email: string): Observable<any> {
   
    return this.http.post<any>('/api/arsii/admin/password', { email });
  }

}