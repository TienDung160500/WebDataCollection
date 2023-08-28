import { NzButtonSize } from 'ng-zorro-antd/button';
import { DataService, thietBi } from './../data.service';
import { ItemData } from './../item-data';
import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-machine-parameter',
  templateUrl: './machine-parameter.component.html',
  styleUrls: ['./machine-parameter.component.css']
})
export class MachineParameterComponent {
@Input() maThietBi ='';
@Input() loaiThietBi ='';
@Input() dayChuyen ='';
@Input() status ='';
@Input() ngayTao =null;
@Input() timeUpdate =null;

  searchTerm: string = '';

  searchResults: thietBi[] = [];

  constructor(private dataService: DataService,private http:HttpClient) { }


  ngOnInit(): void {
    if(sessionStorage.getItem('danhSachThietBi')===null){
      this.getDanhSachThietBi();
    }else{
      var result = sessionStorage.getItem('danhSachThietBi');
      if (result) {
        console.log('lấy danh sách từ cache ')
        this.searchResults = JSON.parse(result);
      }
    }
    }
//--------------------lay danh sach thiet bi -------------------
getDanhSachThietBi(){
  this.http.get<any>('http://localhost:8080/thiet-bi').subscribe(res =>{
    console.log('lấy danh sách từ DB');
      this.searchResults = res as thietBi[];
      console.log(this.searchResults);
      sessionStorage.setItem('danhSachThietBi', JSON.stringify(res));
  })
}
//---------------------------- Tim kiem ---------------------------------(ok)
timKiemThietBi(){
  this.searchResults = [];
    var timKiem = {maThietBi:this.maThietBi,loaiThietBi:this.loaiThietBi,dayChuyen:this.dayChuyen,ngayTao:this.ngayTao,timeUpdate:this.timeUpdate,updateBy:"",status:this.status}
      if(sessionStorage.getItem('thiet bi: '+JSON.stringify(timKiem))=== null){
      this.http.post<any>('http://localhost:8080/thiet-bi/tim-kiem',timKiem).subscribe(res => {
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
//---------------------------- del thiet bi ---------------------------
delThietBi(del:string){
  var result = confirm("Xác nhận xoá thiet bi !");
  console.log("ma thiet bi: ",del)
  if (result) {
    this.http.delete('http://localhost:8080/thiet-bi/del-thiet-bi/' + del).subscribe(() => {
      alert('Xoá thành công');
      //------------ xoá thông tin thiet bi khỏi cache -----------------
      sessionStorage.removeItem(this.maThietBi);
      //-------------- cập nhật danh sách thông số trong cache -----------
      this.getDanhSachThietBi();
    })
  }
}
//-------------------------------------- Them moi -----------------
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

  size: NzButtonSize = 'large';

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

}

export interface ItemData1 {
  STT: number,
  code_parameter: number,
  name_parameter: string,
  date: string,
  username: string,
}
