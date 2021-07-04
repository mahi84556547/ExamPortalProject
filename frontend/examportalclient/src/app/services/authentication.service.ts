import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public loginSubject=new Subject<boolean>();
  constructor(private _http:HttpClient) { }

  public generateToken(logindata:any){
    return this._http.post(`${environment.baseUrl}/authentication/generate-token`,logindata);
  }

  public loginUser(token:string){
    localStorage.setItem('token',token);
    return true;
  }

  public isLoggedIn(){
    let token=localStorage.getItem('token');
    if(token==undefined || token == '' || token == null){
      return false;
    }else{
      return true;
    }
  }

  public getCurrentUser(){
    return this._http.get(`${environment.baseUrl}/authentication/current-user`);
  }

  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  public setUser(user:any){
    localStorage.setItem('user',JSON.stringify(user));
  }
  
  public getUser(){
    let user = localStorage.getItem('user');
    if(user != null){
      return JSON.parse(user);
    }else{
      this.logout();
      return null;
    }
  }

  public getUserRole(){
    let user=this.getUser();
    return user.authorities[0].authority;
  }
}
