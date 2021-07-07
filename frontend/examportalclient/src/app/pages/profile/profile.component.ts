import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
 public user:any=null;
  constructor(private _authenticationService :AuthenticationService) { }

  ngOnInit(): void {
    this.user=this._authenticationService.getUser();
    console.log(this.user.username);
    
  }

}
