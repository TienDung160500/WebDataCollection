import { Router } from '@angular/router';
import { Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() backgroundLogin!: string;
  @Output() onSubmitLoginEvent = new EventEmitter();
  
  constructor(private router: Router) { }

  ngOnInit(): void { 
    const localData = localStorage.getItem('')
  }
  
  // loginObj: any = {
  //   username: '',
  //   password: '',
  // }

  username: string = "";
  password: string = "";

  Login() {
    if (this.username == "admin" && this.password == "123456") {
      this.router.navigate 
      alert("Welcome Admin");
    } else {
      alert("Invalid Username or Password");
    }
    console.log('login button clicked')
    console.log('username', this.username);
    console.log('password', this.password);
  }

  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({"username": this.username, password: this.password});
  }

  // Login() {
  //   if (this.loginObj.username === 'admin' && this.loginObj.password === '123456') {
  //     this.router.navigate(['/welcome']);
  //   } else {
  //     alert('login failed')
  //   }
  


    // console.log('username', this.loginObj.username)
    // console.log('password', this.loginObj.password)

    // can API de ktra dang nhap

    // const isUserExist = this.signupUsers.find(m => m.userName == this.loginObj.username && m.username == this.loginObj.password);
    // if (isUserExist != undefined) {
    //   alert('User login successfully')
    // } else {
    //   alert('User login failed')
    // }
  // }
}
