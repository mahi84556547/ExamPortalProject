import { AuthenticationService } from './../../services/authentication.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  public loginData={
    username:'',
    password:''
  };
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private _authenticationService:AuthenticationService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  onLogin(){
    if(this.loginData.username.trim()=='' || this.loginData.password.trim() ==''){
      this.openSnackBar("username & password is required.","OK");
      return;
    }

    this._authenticationService.generateToken(this.loginData).subscribe(
      (response:any)=>{
        console.log(response.token);
        console.log('from signin'+ this._authenticationService.getToken());
        
        this._authenticationService.loginUser(response.token);
        this._authenticationService.getCurrentUser().subscribe(
          (response)=>{
            console.log(response);
            this._authenticationService.setUser(response);
          }
        );
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


 
