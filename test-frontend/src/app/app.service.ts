import { HttpClient } from '@angular/common/http';
import { publishFacade } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  loginAuth(){
    const url = "http://localhost:8080" + "/login"
    const body = {userName:"hieu",password:"999999"}
    return this.http.post<any>(url,body);
  }
  getUser(){
    const url = "http://localhost:8080" + "/users"
    return this.http.get<any>(url);
  }
  //---------------------------Quan ly thong so
  //---------------------------Hien thi danh sach thong so
  danhSachThongSo(){
    const url = "http://localhost:8080" + "/quan-ly-thong-so"
    return this.http.get<qlts>(url);
  }
  // --------- luu tru trong cache----------------
    public saveData(key: string, value: string){
      localStorage.setItem(key, value);
    }
  //------------ get du lieu tu cache-----------
    public getData(key: string){
      return localStorage.getItem(key)
    }
}
//xây dựng form cho dữ liệu trả về
export interface loginAuth{
  message: string
}
export interface qlts{
  idThongSo: Int16Array
  maThongSo: string
  tenThongSo: string
  ngayTao: Date
  timeUpdate: Date
  updateBy: string
  status: string
}