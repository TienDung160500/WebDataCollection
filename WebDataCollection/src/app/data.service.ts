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
//------------------------------------- quan ly thong so 
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
//--------------------------------- thiet bi
export interface thietBi{
  maThietBi:string,
  loaiThietBi:string,
  dayChuyen:string,
  ngayTao:Date,
  timeUpdate:Date,
  updateBy:string,
  status:string
  listThongSo: chiTietThietBi
}
export interface chiTietThietBi{
  maThietBi:string,
  loaiThietBi:string,
  dayChuyen:string,
  thongSo:string,
  moTa:string,
  status:string,
  phanLoai:string
}
export interface thietBiRequest{
  maThietBi:string,
  loaiThietBi:string,
  dayChuyen:string,
  ngayTao:number,
  timeUpdate:number,
  updateBy:string,
  status:string,
  phanLoai:string
}
//------------------------------ kich ban
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
  status:string,
  listThongSo:chiTietKichBan
}
export interface chiTietKichBan{
  thongSo:string,
  minValue: number,
  maxValue:number,
  trungBinh:number,
  donvi:string,
  phanLoai:string
}