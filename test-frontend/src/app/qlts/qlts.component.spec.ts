import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QltsComponent } from './qlts.component';

describe('QltsComponent', () => {
  let component: QltsComponent;
  let fixture: ComponentFixture<QltsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QltsComponent]
    });
    fixture = TestBed.createComponent(QltsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
