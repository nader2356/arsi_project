import { Contact } from '../types/contact';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, mergeMap } from 'rxjs';
import { UserUpdate } from '../types/user-update';
import { Password } from '../types/password';
import { Skill } from '../types/skills';

@Injectable({
  providedIn: 'root',
})
export class MemberService {
  constructor(private http: HttpClient) {}

  
  // public uploadImage(image: ImageData, id: number) {
  //   return this.http.post<any>('/api/arsii/admin/uploadImage' + image, id);
  // }
  

  public updateMember(user: UserUpdate): Observable<any> {
    return this.http.put<any>('api/arsii/member', user);
  }
  public PasswordService(password: Password): Observable<any> {
    return this.http.put<any>('/api/arsii/admin/password', password);
  }
  public ContactService(contact: Contact): Observable<any> {
    return this.http.post<any>('/api/arsii/admin/contact', contact);
  }
  public getUserById(): Observable<any> {
    return this.http.get<any>('api/arsii/member/me');
  }
  public getUserByAdmin(): Observable<any> {
    return this.http.get<any>('api/arsii/admin/me');
  }
  public getMe(): Observable<any> {
    return this.http.get<any>('api/arsii/admin/me');
  }
  public uploadCV(cv: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', cv);
    return this.http.post<any>(`/api/arsii/file/uploadPDF`, formData);
  }
  updateUserCV(data: any) {
    return this.uploadCV(data.cv).pipe(
      mergeMap((result: any) => {
        const reqData = {
          ...data,
          cv: result.file,
        };
        console.log(result);

        return this.http.put(`api/arsii/member`, reqData);
      })
    );
  }

  public uploadImage(image: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', image);
    return this.http.post<any>(`/api/arsii/file/uploadImage`, formData);
  }

  updateImageUser(data: any) {
    return this.uploadImage(data.image).pipe(
      mergeMap((result: any) => {
        const reqData = {
          ...data,
          image: result.file,
        };
        return this.http.put(`api/arsii/member`, reqData);
      })
    );
  }


  public getCategories(): Observable<any> {
    return this.http.get('api/arsii/admin/category');
  }
  public deleteCategory(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/category/' + Id);
  }
  public getSkills(): Observable<any> {
    return this.http.get('api/arsii/admin/competence');
  }
  public deleteSkill(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/competence/' + Id);
  }
  public addSkills(comp: Skill): Observable<any> {
    return this.http.post('api/arsii/member/usercompetences', comp);
  }

  public getAllOpportunity(): Observable<any> {
    return this.http.get<any>('api/arsii/admin/opportunity/');
  }
}