import { UserGuard } from './guards/user.guard';
import { AdminGuard } from './guards/admin.guard';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { SigninComponent } from './pages/signin/signin.component';
import { SignupComponent } from './pages/signup/signup.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'sginup',component:SignupComponent},
  {path:'sginin',component:SigninComponent},
  {path:'user-dashboard',component:UserDashboardComponent,canActivate:[UserGuard]},
  {path:'admin-dashboard',component:AdminDashboardComponent,canActivate:[AdminGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
