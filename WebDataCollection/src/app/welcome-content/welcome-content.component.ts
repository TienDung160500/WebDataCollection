import { HttpClient } from '@angular/common/http';
import { DataService, ItemData, ItemDataRequest, thietBiRequest } from './../data.service';
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
  parameters: ItemData[] = [];
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
  //---------------------- them moi thong so ---------------------- 
  postThongSo(){
    this.searchResults = [];
    this.http.post('http://localhost:8080/quan-ly-thong-so/them-moi-thong-so',this.listOfData).subscribe(res =>{
      console.log("message: "+res);
      this.getDanhSachThongSo();
    })
  }
  //------------------------- cap nhat thong so -----------------------
  putThongSo(){
    this.searchResults = [];
    const body = {tenThongSo:this.tenThongSo, maThongSo:this.maThongSo,moTa:this.tenThongSo,timeUpdate:Date.now(),status:this.status}
    this.http.put('http://localhost:8080/quan-ly-thong-so/cap-nhat-thong-so/'+this.update, body).subscribe(res=>{
      console.log("cap nhat thong so: "+res);
      this.getDanhSachThongSo();
    })
  }

   //------------------------ su kien tim kiem ------------------------------
   timKiemThongSo(){
    this.searchResults = [];
    var timKiem = {maThongSo:this.maThongSo,tenThongSo:this.tenThongSo,moTa:"",ngayTao:this.ngayTao,timeUpdate:this.timeUpdate,updateBy:this.updateBy,status:this.status}
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
//---------------------------- xem chi tiet thong so -------------------------------------
getChiTietThongSo(value: string){
  if(sessionStorage.getItem("ma thong so: "+value)=== null){
    console.log("ma thong so: ", value);
    this.http.get<any>('http://localhost:8080/quan-ly-thong-so/chi-tiet-thong-so/'+ value).subscribe(res =>{
      console.log("message: ",res);
      this.parameters = res as ItemData[];
      sessionStorage.setItem("ma thong so:"+value,res);
    })
  }else{
    var result = sessionStorage.getItem("ma thong so: "+value);
    if (result) {
      console.log('lay tu cache');
      console.log("ma thong so: ", value);
      this.parameters = JSON.parse(result);
  }
}
}
//--------------------------------thêm mới thông số ---------------------------------
expandSet = new Set<number>();
  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }

  size: NzButtonSize = 'large';

  isVisibleTop = false;
  isVisibleMiddle = false;
  isVisibleMiddle1 = false;
  showModalTop(): void {
    this.isVisibleTop = true;
  }

  showModalMiddle(): void {
  this.listOfData = [];
    this.isVisibleMiddle = true;
  }
  showModalMiddle1(maThongSo: string): void {
    this.getChiTietThongSo(maThongSo);
    this.update = maThongSo;
    this.isVisibleMiddle1 = true;
  }

  handleOkTop(): void {
    this.isVisibleTop = false;
  }

  handleCancelTop(): void {
    this.isVisibleTop = false;
  }

  handleOkMiddle(): void {
    console.log('click ok');
    this.postThongSo()
    this.isVisibleMiddle = false;
  }

  handleCancelMiddle(): void {
    this.isVisibleMiddle = false;
  }
  handleOkMiddle1(): void {
    console.log('click ok');
    console.log('test:',this.tenThongSo,this.maThongSo,this.status)
    this.putThongSo()
    this.isVisibleMiddle1 = false;
  }

  handleCancelMiddle1(): void {
    this.isVisibleMiddle1 = false;
  }
  // tao du lieu gia 
  editId: number | null = null;
  listOfData: ItemDataRequest[] = [];

  i = this.listOfData.length;

  startEdit(id: number): void {
    this.editId = id;
  }

  stopEdit(): void {
    this.editId = null;
  }

  addRow(): void {
    this.listOfData = [
      ...this.listOfData,
      {
      tenThongSo:"",
      maThongSo:"",
      mota:"",
      ngayTao: Date.now(),
      timeUpdate: Date.now(),
      updateBy:"user",
      status:""
      }
    ];
    console.log('add: ',this.listOfData)
    this.i++;
  }

  deleteRow(tenThongSo: string): void {
    this.listOfData = this.listOfData.filter(d => d.tenThongSo !== tenThongSo);
    console.log('del ',this.listOfData)
  }

}