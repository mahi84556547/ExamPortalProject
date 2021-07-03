import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private _authenticationService:AuthenticationService,private _router:Router) { }

  ngOnInit(): void {
  }

  checkIsLogin(){
    return this._authenticationService.isLoggedIn();
  }

  logOut(){
   this._authenticationService.logout();
   this._router.navigateByUrl('/sginin');
  }

}
