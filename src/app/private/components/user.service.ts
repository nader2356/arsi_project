import { User } from './user';
import { UserUpdate } from './user-update';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Password } from './Password';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http:HttpClient) { }
  public SignupUserService(user:User):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/arsii/auth/register",user);
  }
  public SigninUserService(user:User):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/arsii/auth/authenticate",user);
  }
  public ResetPasswordService(email: string): Observable<any> {
    return this.http.post<any>('http://localhost:8090/api/arsii/admin/password', { email });
  }
  public InformationService(user:UserUpdate):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/",user);
  }
  public PasswordService(password:Password):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/arsii/admin/password",password);

  }
  
  public CvService(data:FormData):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/cv",data);

  }


}