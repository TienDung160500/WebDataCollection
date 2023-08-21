import { Injectable } from '@angular/core';

interface ItemData {
  STT: string;
  code_parameter: number;
  name_parameter: string;
  date: string;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private data: ItemData[] = [
    
  ];

  getData(): ItemData[] {
    return this.data;
  }

  search(term: string): ItemData[] {
    term = term.toLowerCase();
    return this.data.filter(item =>
      item.STT.includes(term) ||
      item.name_parameter.toLowerCase().includes(term) ||
      item.date.includes(term) ||
      item.username.toLowerCase().includes(term)
    );
  }
  constructor() { }
}
