import { Router } from '@angular/router';
import { Component, EventEmitter, Input, Output} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() backgroundLogin!: string;
  @Output() onSubmitLoginEvent = new EventEmitter();
  
  constructor(private router: Router,private http: HttpClient) { }
  message?: string;

  ngOnInit(): void { 
    const localData = localStorage.getItem('')
  }
  
  loginObj: any = {
    username: '',
    password: '',
  }

  //-------------- xác thực thông tin đăng nhập ------------------
  Login() {
    const body = {userName:this.loginObj.username,password:this.loginObj.password,lastLogin: Date.now()}
    this.http.post<any>('http://localhost:8080/login',body).subscribe((data:loginAuth) => {
      this.message = data.message;
      console.log(this.loginObj);
    if (this.message === 'Đăng nhập thành công') { 
    console.log(Date.now())
      this.router.navigate(['/home']);
      alert('Đăng nhập thành công')
    } else if(this.message === 'Tài khoản hoặc mật khẩu bị sai') {
      alert('Tài khoản hoặc mật khẩu bị sai')
    }
  })
  }

  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({"username": this.loginObj.username, password: this.loginObj.password});
  }
}
// khởi tạo kiểu dữ liệu trả về
export interface loginAuth{
  message: string,
  lastLogin: Date
}
