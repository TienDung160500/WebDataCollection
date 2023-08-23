import { DataService } from './../data.service';
import { Component } from '@angular/core';
import { NzButtonSize } from 'ng-zorro-antd/button';

interface ItemData {
  STT: string;
  code_parameter: number;
  name_parameter: string;
  date: string;
  username: string;
}

@Component({
  selector: 'app-welcome-content',
  templateUrl: './welcome-content.component.html',
  styleUrls: ['./welcome-content.component.css']
})

export class WelcomeContentComponent {

  // // Create
  // addNewItem(newItem: Item): Observable<Item> { 
  //   return this.http.post<Item>(`${this.apiUrl}/items`, newItem);
  // }

  // // Read
  // // Get a list of items
  // getItem(): Observable<Item[]> { 
  //   return this.http.get<Item[]>(`${this.apiUrl}/items`);
  // }
  // // Get a single item of ID
  // getItemByID(id: number): Observable<Item> { 
  //   return this.http.get<Item>(`${this.apiUrl}/items/${id}`)
  // }

  // // Update
  // updateItem(updateItem: Item): Observable<Item> { 
  //   return this.http.put<Item>(`${this.apiUrl}/items/${updateItem.id}`, updateItem);
  // }

  // // Delete
  // deleteItem(id: number): Observable<void> { 
  //   return this.http.delete<void>(`${this.apiUrl}/items/${id}`)
  // }


  isCollapsed = false;

  searchTerm: string = '';

  listOfData: ItemData[] = [];
  searchResults: ItemData[] = [];

  constructor(private dataService: DataService) { }

  search() {
    this.searchResults = this.dataService.search(this.searchTerm);
  }


  // private data: ItemData[] {
    
  // }

  // getData: ItemData[] {
  // return this.listOfData;
  // }



  ngOnInit(): void {
    for (let i = 1; i <= 100; i++) {
      this.listOfData.push({
        STT: `${i}`,
        code_parameter: 32,
        name_parameter: `London`,
        date: Date(),
        username: `Dũng`,
      });
    }

    for (let i = 101; i <= 150; i++) {
      this.listOfData.push({
        STT: `${i}`,
        code_parameter: 322,
        name_parameter: `VN`,
        date: Date(),
        username: `Dũng`,
      });
    }
  }

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