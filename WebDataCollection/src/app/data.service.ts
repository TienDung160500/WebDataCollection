import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
//khai báo trường dữ liệu
export class DataService {
  private data: ItemData[] = [
    
  ];

  getData(): ItemData[] {
    return this.data;
  }

  search(term: string): ItemData[] {
    term = term.toLowerCase();
    return this.data.filter(item =>
      item.maThongSo.toLowerCase().includes(term) ||
      item.tenThongSo.toLowerCase().includes(term) ||
      //test hiển thị
      item.ngayTao ||
      item.timeUpdate ||
      item.updateBy.toLowerCase().includes(term)||
      item.status.toLowerCase().includes(term)
    );
    
  }
  constructor() { }
}
export interface ItemData {
  idThongSo: number
  maThongSo: string
  tenThongSo: string
  moTa:string
  ngayTao: Date
  timeUpdate: Date
  updateBy: string
  status: string
}
export interface ItemDataRequest{
  maThongSo: string
  tenThongSo: string
  mota:string
  ngayTao: number
  timeUpdate: number
  updateBy: string
  status: string
}
export interface thietBi{
  maThietBi:string,
  loaiThietBi:string,
  dayChuyen:string,
  ngayTao:Date,
  timeUpdate:Date,
  updateBy:string,
  status:string
}
export interface kichBan{
  maKichBan:string,
  maThietBi:string,
  loaiThietBi:string,
  dayChuyen:string,
  maSanPham:string
  versionSanPham:string,
  ngayTao:Date,
  timeUpdate:Date,
  updateBy:string,
  status:string
}