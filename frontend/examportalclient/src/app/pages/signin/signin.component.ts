import { AuthenticationService } from './../../services/authentication.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
    private _snackBar: MatSnackBar,
    private _router:Router) { }

  ngOnInit(): void {
  }

  onLogin(){
    if(this.loginData.username.trim()=='' || this.loginData.password.trim() ==''){
      this.openSnackBar("username & password is required.","OK");
      return;
    }

    this._authenticationService.generateToken(this.loginData).subscribe(
      (response:any)=>{

        this._authenticationService.loginUser(response.token);
        this._authenticationService.getCurrentUser().subscribe(
          (response)=>{
            this._authenticationService.setUser(response);
            if(this._authenticationService.getUserRole()=='NORMAL'){
              this._authenticationService.loginSubject.next(true);
              this._router.navigateByUrl("/user-dashboard");
            }else if(this._authenticationService.getUserRole()=='ADMIN'){
              this._authenticationService.loginSubject.next(true);
              this._router.navigateByUrl("/admin-dashboard");
            }else{
              this._authenticationService.logout();
            }    
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


 
