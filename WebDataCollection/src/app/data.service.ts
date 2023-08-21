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
  idThongSo: Int16Array
  maThongSo: string
  tenThongSo: string
  ngayTao: Date
  timeUpdate: Date
  updateBy: string
  status: string
}