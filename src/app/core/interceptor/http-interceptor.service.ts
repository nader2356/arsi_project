import {
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpResponse,
  } from '@angular/common/http';
  import { Injectable } from '@angular/core';
  import { Router } from '@angular/router';
  import { Observable, throwError } from 'rxjs';
  import { catchError, finalize, map } from 'rxjs/operators';
  import { AuthService } from 'src/app/auth/services/auth.service';
  
  @Injectable()
  export class AppHttpInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService, private router: Router) {}
  
    intercept(
      req: HttpRequest<any>,
      next: HttpHandler
    ): Observable<HttpEvent<any>> {
      console.log("interceptor 1");
      if (this.authService.isLoggedIn) {
        const authToken = this.authService.getToken();
        console.log("interceptor --is connceted");
  
        const cloned = req.clone({
          headers: req.headers.set('Authorization', 'Bearer ' + authToken),
        });
  
        return next.handle(cloned).pipe(
          map((event: HttpEvent<any>) => {
            return event;
          }),
          catchError((error: HttpErrorResponse) => {
            if (error.status == 401) {
              this.authService.logout();
              this.router.navigate(['/auth/login']);
            }
            return throwError(error);
          })
        );
      } else {
        console.log("interceptor --is deconected");
        return next.handle(req).pipe(finalize(() => {}));
      }
    }
  }