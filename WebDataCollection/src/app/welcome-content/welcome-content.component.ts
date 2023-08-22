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

}