import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _isLoggedIn = false;
  IsLoggedInStore = new BehaviorSubject<boolean>(false);
  constructor(private httpClient: HttpClient, private router: Router) {}

  identify(payload: any): Observable<any> {
    return this.httpClient.post(``, payload);
  }

  signup(payload: any): Observable<any> {
    return this.httpClient.post(``, payload);
  }

  set isLoggedIn(value: boolean) {
    this._isLoggedIn = value;
    this.IsLoggedInStore.next(value);
  }

  get isLoggedIn() {
    this.IsLoggedInStore.next(!!this.getToken());
    return !!this.getToken();
  }

  getIsLoggedInStore() {
    return this.IsLoggedInStore.getValue();
  }

  getToken() {
    return localStorage.getItem('access_token') || '';
  }

  login(value: any, token: any) {
    localStorage.setItem('access_token', token);
    this._isLoggedIn = value;
    this.IsLoggedInStore.next(value);
  }

  logout() {
    localStorage.clear();
    this.IsLoggedInStore.next(false);
    this.router.navigate(['/auth/login']);
  }

  getCurentUser(): Observable<any> {
    return this.httpClient.get(``);
  }
}