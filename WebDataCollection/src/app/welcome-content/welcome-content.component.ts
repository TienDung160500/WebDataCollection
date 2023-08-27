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
  @Input() ngayTao = null;
  @Input() timeUpdate = null;
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
    var timKiem = {maThongSo:this.maThongSo,tenThongSo:this.tenThongSo,ngayTao:this.ngayTao,timeUpdate:this.timeUpdate,updateBy:this.updateBy,status:this.status}
      if(sessionStorage.getItem(JSON.stringify(timKiem))=== null){
      this.http.post<any>('http://localhost:8080/quan-ly-thong-so/tim-kiem',timKiem).subscribe(res => {
        console.log("tim kiem: ", res)
        this.searchResults = res as any;
        sessionStorage.setItem(JSON.stringify(timKiem),JSON.stringify(res));
      })
    }else{
      var result = sessionStorage.getItem(JSON.stringify(timKiem));
      if (result) {
        console.log('lay tu cache')
        console.log("ma thong so: ", timKiem)
        this.searchResults = JSON.parse(result);
    }
  }
}
expandSet = new Set<number>();
  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }
listOfData1 = [
  {
    id: 1,
    name: 'John Brown',
    age: 32,
    expand: false,
    address: 'New York No. 1 Lake Park',
    description: 'My name is John Brown, I am 32 years old, living in New York No. 1 Lake Park.'
  },
  {
    id: 2,
    name: 'Jim Green',
    age: 42,
    expand: false,
    address: 'London No. 1 Lake Park',
    description: 'My name is Jim Green, I am 42 years old, living in London No. 1 Lake Park.'
  },
  {
    id: 3,
    name: 'Joe Black',
    age: 32,
    expand: false,
    address: 'Sidney No. 1 Lake Park',
    description: 'My name is Joe Black, I am 32 years old, living in Sidney No. 1 Lake Park.'
  }
];

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