import { Component, OnInit } from '@angular/core';
import { AppService, qlts } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Observable, map, of } from 'rxjs';

@Component({
  selector: 'app-qlts',
  templateUrl: './qlts.component.html',
  styleUrls: ['./qlts.component.css']
})
export class QltsComponent {
  //-------------------Template Quản lý thông số : Hiển thị danh sách thông số ---------------------------
  constructor(private appService: AppService,private http: HttpClient) { }
  idThongSo?: Int16Array
  maThongSo?: [qlts]
  tenThongSo?: [qlts]
  ngayTao?: [qlts]
  timeUpdate?: [qlts]
  updateBy?: [qlts]
  status?: [qlts]
  thongSo?: [qlts]
  ngOnInit(): void {
    console.log("Quan-ly-thong-so")
    // ----------hien thi danh sach thong so ---------- (ok)
    this.appService.danhSachThongSo().subscribe((res:qlts) => {
      console.log("res: ", res);
      this.thongSo = res as any;
      console.log(this.thongSo)
    })

  }
  //----------- Tim kiem theo ma thong so -----------(ok)
  getByMaThongSo() {
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ma-thong-so/3').subscribe((data: qlts) => {
      console.log("get by ma thong so: ", data);
      this.maThongSo = data as any;
      console.log("thong so1: ", this.maThongSo);
      //--------lưu response vào cache---------

    })
  }
  //--------------- Tim kiem theo ten thong so --------------(ok)
  getByTenThongSo(){
  this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ten-thong-so/234').subscribe((data:qlts) =>{
    console.log("GetByTenThongSo: ",data);
    this.tenThongSo = data as any;
  })
}
//------------------Tim kiem theo ngay tao -------------------(ok)
  getByNgayTao(){
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so/ngay-tao/2222').subscribe((data: qlts)=>{
    console.log("GetByNgayTao: ",data);
    this.ngayTao = data as any;
    })
  }
      //------------- Tìm kiếm theo ngày cập nhật -----------------(ok)
  getByTimeUpdate(){
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so/time-update/2222').subscribe((data:qlts)=>{
      console.log("GetByTimeUpdate: ",data);
      this.timeUpdate = data as any;
    })
  }
      //------------- Tìm kiếm theo tài khoản khởi tạo thông số -----------------(ok)
  getByUpdateBy(){
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so/update-by/user').subscribe((data:qlts)=>{
      console.log("GetByUpdateBy: ",data);
      this.updateBy = data as any;
    })
  }
      //------------- Tìm kiếm theo trạng thái (status) -----------------(ok)
  getByStatus(){
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so/status/active').subscribe((data:qlts)=>{
      console.log("GetByStatus: ",data);
      this.status = data as any;
    })
  }
}
