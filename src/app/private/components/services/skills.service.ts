import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {

  constructor(private http: HttpClient) {}

  getCategories(): Observable<any> {
    return this.http.get('api/arsii/admin/category');
  }
  public deleteCategory(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/category/' + Id);
  }

  public addCategory(data: any): Observable<any> {
    return this.http.post('api/arsii/admin/category', data);
  }

  public deleteSkill(Id: number): Observable<any> {
    return this.http.delete<any>('api/arsii/admin/competence/' + Id);
  }
  public addSkill(data: any): Observable<any> {
    return this.http.post('api/arsii/admin/competence', data);
  }
  public editSkill(data: any): Observable<any> {
    return this.http.put('api/arsii/admin/competence/' + data.id, data);
  }
}
