import { environment } from './../../environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private _http:HttpClient) { }

  public saveUser(user:any){
    return this._http.post(`${environment.baseUrl}/users`,user);
  }
}
