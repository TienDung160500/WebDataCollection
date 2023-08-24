import { HttpClient } from '@angular/common/http';
import { DataService, ItemData, ItemDataRequest } from './../data.service';
import { Component, Input } from '@angular/core';
import { NzButtonSize } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-welcome-content',
  templateUrl: './welcome-content.component.html',
  styleUrls: ['./welcome-content.component.css']
})

export class WelcomeContentComponent {
  isCollapsed = false;
  //---------------------khởi tạo biến lưu giá trị đầu vào tìm kiếm--------------------
  @Input() maThongSo = '';
  @Input() tenThongSo = '';
  @Input() ngayTao = '';
  @Input() timeUpdate = '';
  @Input() updateBy = '';
  @Input() status = '';
  @Input() update = '';
  //---------------------khởi tạo nơi lưu trữ dữ liệu trả về ------------------
  searchResults: ItemData[] = [];
  body: ItemDataRequest[] = [{
    maThongSo: "test1",
    tenThongSo:"res1",
    mota:"res1",
    ngayTao: Date.now(),
    timeUpdate: Date.now(),
    updateBy:"user",
    status:""
  },{
  maThongSo: "test2",
    tenThongSo:"res2",
    mota:"res2",
    ngayTao: Date.now(),
    timeUpdate: Date.now(),
    updateBy:"user",
    status:""

  },{
  maThongSo: "test3",
    tenThongSo:"res3",
    mota:"res3",
    ngayTao: Date.now(),
    timeUpdate: Date.now(),
    updateBy:"user",
    status:""

  },{
  maThongSo: "test4",
    tenThongSo:"res4",
    mota:"res4",
    ngayTao: Date.now(),
    timeUpdate: Date.now(),
    updateBy:"user",
    status:""

  },
];
  body1: ItemDataRequest ={
    maThongSo: "",
    tenThongSo:"",
    mota:"",
    ngayTao: 2,
    timeUpdate: 2,
    updateBy:"",
    status:""
  }
  //------------------------- Dữ liệu giả -------------------------
  constructor(private dataService: DataService, private http: HttpClient) { }
  ngOnInit(): void {
    sessionStorage.removeItem('danhSachTatCaThongSo');
    if (sessionStorage.getItem('danhSachTatCaThongSo') === null) {
      this.getDanhSachThongSo();
    } else {
      var result = sessionStorage.getItem('danhSachTatCaThongSo');
      if (result) {
        console.log('lấy danh sách từ cache ')
        this.searchResults = JSON.parse(result);
      }
    }
  }
   //-------------- request lấy danh sách tất cả thông số------------------(ok)
  getDanhSachThongSo(){
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so').subscribe(res => {
      console.log('lấy danh sách từ DB');
      this.searchResults = res as ItemData[];
      console.log(this.searchResults);
      sessionStorage.setItem('danhSachTatCaThongSo', JSON.stringify(res));
    })
  }
  //--------------- request lấy danh sách theo mã thông số --------------
  getByMaThongSo() {
    //---------- xoá dữ liệu cũ --------------------
    this.searchResults = [];
    // kiểm tra xem cache còn lưu giá trị không
    if (sessionStorage.getItem(this.maThongSo) === null) {
      this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ma-thong-so/' + this.maThongSo).subscribe(res => {
        console.log("tương tác với DB:")
        this.searchResults = res as any;
        // ------------ lưu vào cache------------ (lưu vào session storage) -aws
        sessionStorage.setItem(this.maThongSo, JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem(this.maThongSo);
      if (result) {
        console.log("lấy từ cache:")
        this.searchResults = JSON.parse(result);
      }
    }

  }
  //--------------- request lấy danh sách theo tên thông số --------------
  getByTenThongSo() {
    this.searchResults = [];
    if (sessionStorage.getItem(this.tenThongSo) === null) {
      this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ten-thong-so/' + this.tenThongSo).subscribe(res => {
        console.log("getByTenThongSo: ", res)
        //---------------- gán dữ liệu mới  ----------------
        this.searchResults = res as any;
        sessionStorage.setItem(this.tenThongSo, JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem(this.tenThongSo);
      if (result) {
        console.log("lay tu cache");
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //--------------- request lấy danh sách theo ngày tạo --------------
  getByNgayTao() {
    this.searchResults = [];
    if (sessionStorage.getItem(this.ngayTao) === null) {
      this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ngay-tao/' + this.ngayTao).subscribe(res => {
        console.log("getByNgayTao: ", res)
        this.searchResults = res as any;
        sessionStorage.setItem(this.ngayTao, JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem(this.ngayTao);
      if (result) {
        console.log('lay tu cache')
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //--------------- request lấy danh sách theo ngày cập nhật --------------
  getByTimeUpdate() {
    this.searchResults = [];
    if (sessionStorage.getItem(this.timeUpdate) === null) {
      this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ngay-tao/' + this.timeUpdate).subscribe(res => {
        console.log("getByTimeUpdate: ", res)
        this.searchResults = res as any;
        sessionStorage.setItem(this.timeUpdate, JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem(this.timeUpdate);
      if (result) {
        console.log('lay tu cache');
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //--------------- request lấy danh sách theo tài khoản --------------
  getByUpdateBy() {
    this.searchResults = [];
    if (sessionStorage.getItem(this.updateBy) === null) {
      this.http.get<any>('http://localhost:8080/quan-ly-thong-so/update-by/' + this.updateBy).subscribe(res => {
        console.log("getByUpdateBy: ", res)
        this.searchResults = res as any;
        sessionStorage.setItem(this.updateBy, JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem(this.updateBy);
      if (result) {
        console.log('lay tu cache');
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //--------------- request lấy danh sách theo trạng thái (status) --------------
  getByStatus() {
    this.searchResults = [];
    if (sessionStorage.getItem(this.status) === null) {
      this.http.get<any>('http://localhost:8080/quan-ly-thong-so/status/' + this.status).subscribe(res => {
        console.log("getBystatus: ", res)
        this.searchResults = res as any;
        sessionStorage.setItem(this.status, JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem(this.status);
      if (result) {
        console.log('lay tu cache')
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //------------------request del thông số ------------------------
  delThongSo(del: string) {
    //-------------- thêm thông báo xác nhận yêu cầu xoá thông số  --------------
    var result = confirm("Xác nhận xoá thông số !");
    if (result) {
      this.http.delete('http://localhost:8080/quan-ly-thong-so/del-by-ma-thong-so/' + del).subscribe(() => {
        alert('Xoá thành công');
        //------------ xoá thông tin thông số khỏi cache -----------------
        sessionStorage.removeItem(this.maThongSo);
        //-------------- cập nhật danh sách thông số trong cache -----------
        this.getDanhSachThongSo();
      })
    }
  }
  //---------------------- them moi thong so ---------------------- (chưa test trên nút tìm kiếm)
  postThongSo(){
    this.searchResults = [];
    this.http.post('http://localhost:8080/quan-ly-thong-so/them-moi-thong-so',this.body).subscribe(res =>{
      console.log("message: "+res);
      this.getDanhSachThongSo();
    })
  }
  //------------------------- cap nhat thong so -----------------------(chưa test hiển thị)
  putThongSo(){
    this.searchResults = [];
    this.http.put('http://localhost:8080/quan-ly-thong-so/them-moi-thong-so'+this.update, this.body).subscribe(res=>{
      console.log("cap nhat thong so: "+res);
      this.getDanhSachThongSo();
    })
  }

  //------------------------ su kien tim kiem ------------------------------

  timKiemThongSo(){
    this.searchResults = [];
    if (sessionStorage.getItem('tim kiem') === null) {
      this.http.post<any>('http://localhost:8080/quan-ly-thong-so/tim-kiem',this.body1).subscribe(res => {
        console.log("tim kiem: ", res)
        this.searchResults = res as any;
        sessionStorage.setItem('tim kiem', JSON.stringify(res));
      })
    } else {
      var result = sessionStorage.getItem('tim kiem');
      if (result) {
        console.log('lay tu cache')
        this.searchResults = JSON.parse(result);
      }
    }
  }
  size: NzButtonSize = 'large';

  isVisibleTop = false;
  isVisibleMiddle = false;
  showModalTop(): void {
    this.isVisibleTop = true;
  }

  showModalMiddle(): void {
    this.isVisibleMiddle = true;
  }

  handleOkTop(): void {
    this.isVisibleTop = false;
  }

  handleCancelTop(): void {
    this.isVisibleTop = false;
  }

  handleOkMiddle(): void {
    console.log('click ok');
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }
}