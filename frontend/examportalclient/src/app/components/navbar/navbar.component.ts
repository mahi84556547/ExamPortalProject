import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn:boolean=false;
  username:string='';
  constructor(private _authenticationService:AuthenticationService,private _router:Router) { }

  ngOnInit(): void {
    this._authenticationService.loginSubject.asObservable().subscribe(
      (data)=>{
        this.getCurrentUserName();
      }
    )
  }

  checkIsLogin(){
    return this._authenticationService.isLoggedIn();
  }

  logOut(){
   this._authenticationService.logout();
   this._authenticationService.loginSubject.next(false);
   this._router.navigateByUrl('/sginin');
  }

  getCurrentUserName(){
    this.isLoggedIn=this._authenticationService.isLoggedIn();
    if(this.isLoggedIn){
      this.username=this._authenticationService.getUser()?.username;
    }
  }

}
