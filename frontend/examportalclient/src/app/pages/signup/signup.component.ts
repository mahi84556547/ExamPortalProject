import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public user={
    firstName:'',
    lastName:'',
    username:'',
    email:'',
    password:'',
    phone:''
  }
  constructor(private _userService:UserService) { }

  ngOnInit(): void {
  }

  public onRegister(){
    if(this.user.firstName==''|| this.user.firstName==null){
      alert('first name is required');
      return;
    }

    this._userService.saveUser(this.user).subscribe(
      (response)=>{
        console.log(response);
        
        console.log("success");
      }
      ,
      (error)=>{
        console.log(error);
        
        console.log("Faild");
      }
    );

  }

}
