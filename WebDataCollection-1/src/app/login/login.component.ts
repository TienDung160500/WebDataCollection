import { Router } from '@angular/router';
import { Component, Input} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @Input() backgroundLogin!: string;
  //khởi tạo biến lưu trữ thông tin trả về
  message?: string;
  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void { 
    const localData = localStorage.getItem('');
  }
  // lưu trữ giá trị nhập từ input
  loginObj: any = {
    username: '',
    password: '',
  }
  
  // function kiểm tra thông tin đăng nhập
  onLogin() {
    const body = {userName:this.loginObj.username,password:this.loginObj.password,lastLogin: Date.now()}
    this.http.post<any>('http://localhost:8080/login',body).subscribe((data:loginAuth) => {
      this.message = data.message;
      console.log(this.loginObj);
    if (this.message === 'Đăng nhập thành công') { 
    console.log(Date.now())
      this.router.navigate(['/welcome']);
      alert('Đăng nhập thành công')
    } else if(this.message === 'Tài khoản hoặc mật khẩu bị sai') {
      alert('Tài khoản hoặc mật khẩu bị sai')
    }
  })
  }
}
// khởi tạo kiểu dữ liệu trả về
export interface loginAuth{
message: string,
lastLogin: Date
}
