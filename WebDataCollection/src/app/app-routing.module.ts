import { ScriptManagerComponent } from './script-manager/script-manager.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MachineParameterComponent } from './machine-parameter/machine-parameter.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeContentComponent } from './welcome-content/welcome-content.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch:'full'
  },
  {
    path: "home",
    component: HomePageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'thong-so-may',
    component: WelcomeContentComponent
  },
  {
    path: 'thong-so-ky-thuat',
    component: MachineParameterComponent
  },
  {
    path: "quan-ly-kich-ban",
    component: ScriptManagerComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
