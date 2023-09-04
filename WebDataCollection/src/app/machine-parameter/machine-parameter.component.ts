import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { DataService, thietBi, chiTietThietBi, thietBiRequest } from './../data.service';
import { ItemData } from './../item-data';
import { Component, Input } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-machine-parameter',
  templateUrl: './machine-parameter.component.html',
  styleUrls: ['./machine-parameter.component.css']
})
export class MachineParameterComponent {
  // ----- set input
  @Input() maThietBi = '';
  @Input() loaiThietBi = '';
  @Input() dayChuyen = '';
  @Input() status = '';
  @Input() ngayTao = null;
  @Input() timeUpdate = null;
  @Input() thongSo = '';
  @Input() moTa = '';
  @Input() phanLoai = '';

  searchTerm: string = '';

  listOfData: ItemData[] = [];
  searchResults: thietBi[] = [];
  editCache: { [key: string]: { edit: boolean; data: ItemData5 } } = {};
  listOfData5: ItemData5[] = [];


  constructor(private dataService: DataService, private modalService: NzModalService, private http: HttpClient) { }

  // thông tin
  startEditInfor(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.listOfData5.findIndex(item => item.id === id);
    this.editCache[id] = {
      data: { ...this.listOfData5[index] },
      edit: false
    };
  }

  saveEdit(id: string): void {
    const index = this.listOfData5.findIndex(item => item.id === id);
    Object.assign(this.listOfData5[index], this.editCache[id].data);
    this.editCache[id].edit = false;
  }

  updateEditCache(): void {
    this.listOfData5.forEach(item => {
      this.editCache[item.id] = {
        edit: false,
        data: { ...item }
      };
    });
  }

  ngOnInit(): void {
    if (sessionStorage.getItem('danhSachThietBi') === null) {
      this.getDanhSachThietBi();
    } else {
      console.log('lay danh sach tu cache');
      var result = sessionStorage.getItem('danhSachThietBi');
      if (result) {
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //------------------------- danh sach thiet bi --------------------------------
  getDanhSachThietBi() {
    this.http.get<any>('http://192.168.18.145:8080/thiet-bi').subscribe(res => {
      console.log('danh sách thiet bi'); console.log(res)
      this.searchResults = res as any;
      sessionStorage.setItem('danhSachThietBi', JSON.stringify(res));

    })
  }
  //------------------------------ su kien tim kiem ------------------------------
  timKiemThietBi() {
    //xoa du lieu cu
    this.searchResults = [];
    //request den server
    var timKiem = { maThietBi: this.maThietBi, loaiThietBi: this.loaiThietBi, dayChuyen: this.dayChuyen, ngayTao: this.ngayTao, timeUpdate: this.timeUpdate, updateBy: '', status: this.status };
    if (sessionStorage.getItem('thiet bi' + JSON.stringify(timKiem)) === null) {
      this.http.post<any>('http://192.168.18.145:8080/thiet-bi/tim-kiem', timKiem).subscribe(res => {
        console.log("tim kiem", res);
        //luu du lieu tra ve de hien thi len front-end  
        this.searchResults = res as any;
        sessionStorage.setItem('thiet bi' + JSON.stringify(timKiem), res);
      })
    } else {
      var result = sessionStorage.getItem('thiet bi' + JSON.stringify(timKiem))
      if (result) {
        console.log('lay du lieu tren cache');
        this.searchResults = JSON.parse(result);
      }
    }
  }
  //------------------------------------------------ del thiet bi -----------------------------------
  delThietBi(del: string) {
    var result = confirm('Are you sure');
    if (result) {
      this.http.delete(`http://192.168.18.145:8080/thiet-bi/del-thiet-bi/${del}`).subscribe(() => {
        alert('xoa thanh cong');
        // xoa thong tin dang luu trong session
        sessionStorage.removeItem(del);
        this.getDanhSachThietBi();
      })
    }
  }
//---------------------- them moi thiet bi ----------------------
postThietBi(){
  this.searchResults = [];
  this.http.post('http://localhost:8080/thiet-bi/them-moi-thiet-bi',this.thietBi).subscribe(res =>{
    console.log("message: "+res);

    this.getDanhSachThietBi();
  })
}
//------------------------------------------ xem chi tiết thông số thiết bị ----------------------------------------

  luuThongSo(save: any) {
    this.searchResults = [];

    const cacheKey = 'thiet bi' + JSON.stringify(save);
    const cachedData = sessionStorage.getItem(cacheKey);

    if (cachedData === null) {
      this.http.get<any>('http://192.168.18.145:8080//thiet-bi/chi-tiet-thiet-bi/4', save).subscribe(res => {
        console.log("tim kiem", res);

        this.searchResults = res as any;
        sessionStorage.setItem(cacheKey, JSON.stringify(res));
      });
    } else {
      this.searchResults = JSON.parse(cachedData);
    }
  }

  hienThiDuLieuCache(save: any) {
    const cacheKey = 'thiet bi' + JSON.stringify(save);
    const cachedData = sessionStorage.getItem(cacheKey);

    if (cachedData) {
      this.searchResults = JSON.parse(cachedData);
    } else {
      this.searchResults = []
    }
  }

  putKichBan() {
    // this.http.put('url', body)
    // /thiet-bi/chi-tiet-thiet-bi/{idThietBi}
  }
  //----------------------------------------- tương tác với thêm mới 
  isVisibleTop = false;
  isVisibleMiddle = false;

  showModalTop(): void {
    this.isVisibleTop = true;
  }

  showModalMiddle(): void {
    this.thietBi = [];
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
    console.log("data: ", this.thietBi)
    this.postThietBi()
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }

  // thông tin chi tiết

  isVisibleTopInfor = false;
  isVisibleMiddleInfor = false;

  showModalTopInfor(): void {
    this.isVisibleTopInfor = true;
  }

  showModalMiddleInfor(): void {
    this.isVisibleMiddleInfor = true;
  }

  handleOkTopInfor(): void {
    this.isVisibleTopInfor = false;
  }

  handleCancelTopInfor(): void {
    this.isVisibleTopInfor = false;
  }

  handleOkMiddleInfor(): void {
    console.log('click ok');
    this.isVisibleMiddleInfor = false;
  }

  handleCancelMiddleInfor(): void {
    this.isVisibleMiddleInfor = false;
  }

  showConfirm(): void {
    this.modalService.confirm({
      nzTitle: 'Confirm',
      nzContent: 'Bla bla ...',
      nzOkText: 'OK',
      nzCancelText: 'Cancel'
    });
  }

  size: NzButtonSize = 'large';

  expandSet = new Set<number>();
  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }

  onChange(result: Date): void {
    console.log('Selected Time: ', result);
  }

  onOk(result: Date | Date[] | null): void {
    console.log('onOk', result);
  }
  // -----------------------------------------------------------------------------khai bao  thiet bi

  editIdThietBi: number | null = null;
  thietBi: thietBiRequest[] = [];

  idThietBi = this.thietBi.length;

  startEditThietBi(id: number): void {
    this.editIdThietBi = id;
  }

  stopEditThietBi(): void {
    this.editIdThietBi = null;
  }

  addRowThietBi(): void {
    this.thietBi = [
      ...this.thietBi,
      {
        maThietBi: '',
        loaiThietBi: '',
        dayChuyen: '',
        ngayTao: Date.now(),
        timeUpdate: Date.now(),
        updateBy: '',
        status: '',
        phanLoai: ''
      }
    ];
    console.log('add: ', this.thietBi)
    this.idThietBi++;
  }

  deleteRowThietBi(maThietBi: string): void {
    this.thietBi = this.thietBi.filter(d => d.maThietBi !== maThietBi);
    console.log('del ', this.thietBi)
  }
  // -------------------------------------------------------------------------- them moi thong so thiet bi
  editId: number | null = null;
  chiTietThongSoThietBi: chiTietThietBi[] = [];

  i = this.chiTietThongSoThietBi.length;

  startEditChiTietThongSoThietBi(id: number): void {
    this.editId = id;
  }

  stopEditChiTietThongSoThietBi(): void {
    this.editId = null;
  }

  addRowChiTietThongSoThietBi(): void {
    this.chiTietThongSoThietBi = [
      ...this.chiTietThongSoThietBi,
      {
        maThietBi:'',
        loaiThietBi:'',
        dayChuyen:'',
        thongSo: '',
        moTa: '',
        status: '',
        phanLoai: ''
      }
    ];
    console.log('add: ', this.chiTietThongSoThietBi)
    this.i++;
  }

  deleteRowChiTietThongSoThietBi(thongSo: string): void {
    this.chiTietThongSoThietBi = this.chiTietThongSoThietBi.filter(d => d.thongSo !== thongSo);
    console.log('del ', this.chiTietThongSoThietBi)
  }
//------------------------------------------------------ cap nhat thong so thiet bi ----------------------------------------
editId: number | null = null;
  chiTietThongSoThietBi: chiTietThietBi[] = [];

  i = this.chiTietThongSoThietBi.length;

  startEditChiTietThongSoThietBi(id: number): void {
    this.editId = id;
  }

  stopEditChiTietThongSoThietBi(): void {
    this.editId = null;
  }

  addRowChiTietThongSoThietBi(): void {
    this.chiTietThongSoThietBi = [
      ...this.chiTietThongSoThietBi,
      {
        maThietBi:'',
        loaiThietBi:'',
        dayChuyen:'',
        thongSo: '',
        moTa: '',
        status: '',
        phanLoai: ''
      }
    ];
    console.log('add: ', this.chiTietThongSoThietBi)
    this.i++;
  }

  deleteRowChiTietThongSoThietBi(thongSo: string): void {
    this.chiTietThongSoThietBi = this.chiTietThongSoThietBi.filter(d => d.thongSo !== thongSo);
    console.log('del ', this.chiTietThongSoThietBi)
  }
}
export interface ItemData1 {
  status: any;
  updateBy: any;
  timeUpdate: any;
  tenThongSo: any;
  ngayTao: any;
  idThongSo: any;
  maThongSo: any;
  STT: number,
  code_parameter: number,
  name_parameter: string,
  date: string,
  username: string,
}
export interface ItemData4 {
  id: string;
  name: string;
  age: string;
  address: string;
}

export interface ItemData5 {
  id: string;
  name: string;
  age: number;
  address: string;
}