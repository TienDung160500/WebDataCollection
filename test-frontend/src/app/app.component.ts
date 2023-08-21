import { Component, OnInit } from '@angular/core';
import { observable } from 'rxjs';
import { AppService, loginAuth, qlts } from 'src/app/app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'test-frontend';
  res1?: any
  message?: string
  constructor(private appService: AppService) { }

  ngOnInit(): void {
    console.log("Hello");
    //-------------------Template login : Xác thực tài khoản ---------------------------
    // this.appService.loginAuth().subscribe((res: loginAuth) => {
    //   console.log("login auth", res)
    //   this.message = res.message
    // })
    // // put
    // this.userService.putUser().subscribe((res:userRes)=>{
    //   console.log("put user: ", res);
    // })
    // this.userService.postUser().subscribe(data =>{
    //     this.newUser = data.userName;
    //   });
    //     // delete
    // this.userService.delUser().subscribe(()=>{
    //   this.status='Delete successfull'
    //   console.log(this.status)})
  }

  loginObj: any = {
    username: '',
    password: '',
  }
  
  onLogin(){
    this.appService.loginAuth().subscribe((res: loginAuth) => {
      console.log("login auth", res)
      this.message = res.message
    })
  }
}
