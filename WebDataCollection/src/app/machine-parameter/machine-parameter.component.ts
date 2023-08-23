import { NzButtonSize } from 'ng-zorro-antd/button';
import { DataService } from './../data.service';
import { ItemData } from './../item-data';
import { Component } from '@angular/core';

@Component({
  selector: 'app-machine-parameter',
  templateUrl: './machine-parameter.component.html',
  styleUrls: ['./machine-parameter.component.css']
})
export class MachineParameterComponent {

  searchTerm: string = '';

  listOfData: ItemData[] = [];
  searchResults: ItemData[] = [];

  constructor(private dataService: DataService) { }

  search() {
    this.searchResults = this.dataService.search(this.searchTerm);
  }

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
