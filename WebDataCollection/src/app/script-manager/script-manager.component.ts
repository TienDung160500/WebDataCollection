import { DataService, kichBan } from './../data.service';
import { ItemData } from './../item-data';
import { NzButtonSize } from 'ng-zorro-antd/button';
import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-script-manager',
  templateUrl: './script-manager.component.html',
  styleUrls: ['./script-manager.component.css']
})
export class ScriptManagerComponent {
searchTerm: string = '';
@Input() maKichBan = '';
@Input() maThietBi = '';
@Input() loaiThietBi = '';
@Input() dayChuyen = '';
@Input() maSanPham = '';
@Input() versionSanPham = '';
@Input() ngayTao = null;
@Input() timeUpdate = null;
@Input() updateBy = '';
@Input() status = '';
  listOfData: ItemData[] = [];
  searchResults: kichBan[] = [];

  constructor(private dataService: DataService, private http:HttpClient) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('danhSachKichBan')===null){
      this.getDanhSachKichBan();
    }else{
      var result = sessionStorage.getItem('danhSachKichBan');
      if (result) {
        console.log('lấy danh sách từ cache ')
        this.searchResults = JSON.parse(result);
      }
    }
  }
  getDanhSachKichBan(){
    this.http.get<any>('http://localhost:8080/kich-ban').subscribe(res =>{
      console.log('lấy danh sách từ DB');
        this.searchResults = res as kichBan[];
        console.log(this.searchResults);
        sessionStorage.setItem('danhSachKichBan', JSON.stringify(res));
    })
  }
  //--------------------------------- Tim kiem --------------------------------
  timKiemKichBan(){
    this.searchResults = [];
      var timKiem = {maKichBan:this.maKichBan,maThietBi:"",loaiThietBi:"",dayChuyen:"",maSanPham:this.maSanPham,versionSanPham:"",ngayTao:this.ngayTao,timeUpdate:this.timeUpdate,updateBy:this.updateBy,status:this.status}
        if(sessionStorage.getItem('kich ban: '+JSON.stringify(timKiem))=== null){
        this.http.post<any>('http://localhost:8080/kich-ban/tim-kiem',timKiem).subscribe(res => {
          console.log("tim kiem kich ban: ", res)
          this.searchResults = res as any;
          sessionStorage.setItem(JSON.stringify(timKiem),JSON.stringify(res));
        })
      }else{
        var result = sessionStorage.getItem('kich ban: '+JSON.stringify(timKiem));
        if (result) {
          console.log('lay tu cache')
          console.log("kich ban: ", timKiem)
          this.searchResults = JSON.parse(result);
      }
    }
  }
  //-------------------------------- del kich ban --------------------------------------
  delKichBan(del:string){
    var result = confirm("Xác nhận xoá thiet bi !");
    console.log("ma kich ban: ",del)
    if (result) {
      this.http.delete('http://localhost:8080/kich-ban/del-kich-ban/' + del).subscribe(() => {
        alert('Xoá thành công');
        //------------ xoá thông tin thiet bi khỏi cache -----------------
        //-------------- cập nhật danh sách thông số trong cache -----------
        this.getDanhSachKichBan();
      })
    }
  }
  //---------------------------- chi tiet kich ban -------------------------
  chiTietKichBan(){
    this.searchResults = [];
  this.http.get<any>('http://localhost:8080/kich-ban/chi-tiet-kich-ban/999').subscribe((res:kichBan)=>{
  this.searchResults = res as any;
  console.log("results: ", this.searchResults)
  })
  }
  isVisibleTop = false;
  isVisibleMiddle = false;

  showModalTop(): void {
    this.isVisibleTop = true;
  }

  showModalMiddle(): void {
    this.chiTietKichBan()
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

  // search() {
  //   console.log('searching...', this.searchTerm);
  // }

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
