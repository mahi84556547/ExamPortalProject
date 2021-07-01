import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

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

  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private _userService:UserService,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  public onRegister(){
    if(this.user.firstName==''|| this.user.firstName==null){
      this.openSnackBar("First name is required.","OK");
      return;
    }

    this._userService.saveUser(this.user).subscribe(
      (response)=>{
        console.log(response);
        
        console.log("success");
      }
      ,
      (error)=>{
        this.openSnackBar(error.error.message,"OK");
      }
    );
  }

  openSnackBar(message:string, action:string) {
    this._snackBar.open(message, action, {
      duration:5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

}
