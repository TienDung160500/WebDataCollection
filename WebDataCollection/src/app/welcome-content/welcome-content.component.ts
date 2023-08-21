import { HttpClient } from '@angular/common/http';
import { DataService, ItemData } from './../data.service';
import { Component, Input } from '@angular/core';
import { NzButtonSize } from 'ng-zorro-antd/button';

@Component({
  selector: 'app-welcome-content',
  templateUrl: './welcome-content.component.html',
  styleUrls: ['./welcome-content.component.css']
})

export class WelcomeContentComponent {
  isCollapsed = false;
  //---------------------khởi tạo nơi lưu trữ dữ liệu trả về
  @Input() maThongSo = '';
  @Input() tenThongSo = '';
  @Input() ngayTao = '';
  @Input() timeUpdate = '';
  @Input() updateBy = '';
  @Input() status = '';
  listOfData: ItemData[] = [];
  searchResults: ItemData[] = [];
  searchByMaThongSo: ItemData[] = [];

  constructor(private dataService: DataService, private http: HttpClient) { }

  // search() {
  //   this.searchResults = this.dataService.search(this.searchTerm);
  // }
  ngOnInit(): void {
    //-------------- request lấy danh sách tất cả thông số------------------(ok)
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so').subscribe((res:ItemData)=>{
      this.listOfData = res as any;
      console.log("list of data: ", this.listOfData)
    })
  }
  //--------------- request lấy danh sách theo mã thông số --------------
  getByMaThongSo() {
    this.http.get<ItemData>('http://localhost:8080/quan-ly-thong-so/ma-thong-so/'+ this.maThongSo).subscribe((res:ItemData)=>{
    console.log("getByMaThongSo: ",res)  
    this.searchResults = res as any
    })
  }
  //--------------- request lấy danh sách theo tên thông số --------------
  getByTenThongSo() {
    //-------------xoá dữ liệu cũ----------
    this.searchResults =[];
    //--------------- gửi request----------
    this.http.get<ItemData>('http://localhost:8080/quan-ly-thong-so/ten-thong-so/'+ this.tenThongSo).subscribe((res:ItemData)=>{
    console.log("getByTenThongSo: ",res)
    //---------------- gán dữ liệu mới  ----------------
    this.searchResults = res as any
    })
  }
  //--------------- request lấy danh sách theo ngày tạo --------------
  getByNgayTao() {
    //-------------xoá dữ liệu cũ----------
    this.searchResults =[];
    //--------------- gửi request----------
    this.http.get<ItemData>('http://localhost:8080/quan-ly-thong-so/ngay-tao/'+ this.ngayTao).subscribe((res:ItemData)=>{
    console.log("getByNgayTao: ",res)
    //---------------- gán dữ liệu mới  ----------------
    this.searchResults = res as any
    })
  }
  //--------------- request lấy danh sách theo ngày cập nhật --------------
  getByTimeUpdate() {
    //-------------xoá dữ liệu cũ----------
    this.searchResults =[];
    //--------------- gửi request----------
    this.http.get<ItemData>('http://localhost:8080/quan-ly-thong-so/ngay-tao/'+ this.timeUpdate).subscribe((res:ItemData)=>{
    console.log("getByTimeUpdate: ",res)
    //---------------- gán dữ liệu mới  ----------------
    this.searchResults = res as any
    })
  }
  //--------------- request lấy danh sách theo tài khoản --------------
  getByUpdateBy() {
    //-------------xoá dữ liệu cũ----------
    this.searchResults =[];
    //--------------- gửi request----------
    this.http.get<ItemData>('http://localhost:8080/quan-ly-thong-so/update-by/'+ this.updateBy).subscribe((res:ItemData)=>{
    console.log("getByUpdateBy: ",res)
    //---------------- gán dữ liệu mới  ----------------
    this.searchResults = res as any
    })
  }
  //--------------- request lấy danh sách theo trạng thái (status) --------------
  getByStatus() {
    //-------------xoá dữ liệu cũ----------
    this.searchResults =[];
    //--------------- gửi request----------
    this.http.get<ItemData>('http://localhost:8080/quan-ly-thong-so/status/'+ this.status).subscribe((res:ItemData)=>{
    console.log("getBystatus: ",res)
    //---------------- gán dữ liệu mới  ----------------
    this.searchResults = res as any
    })
  }
  size: NzButtonSize = 'large';


}