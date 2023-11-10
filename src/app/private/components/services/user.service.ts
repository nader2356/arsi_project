import { Observable } from "rxjs";
import { User } from "../types/user";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserUpdate } from "../types/user-update";
import { Password } from "../types/password";
import { Contact } from "../types/contact";
import { Email } from "../types/email";




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
  public EmailService(email:Email):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/",email);

  }
  public ContactService(contact:Contact):Observable<any>{
    return this.http.post<any>("http://localhost:8090/api/",contact);

  }


}