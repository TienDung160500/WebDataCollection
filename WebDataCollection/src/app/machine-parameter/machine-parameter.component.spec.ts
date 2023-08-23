import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineParameterComponent } from './machine-parameter.component';

describe('MachineParameterComponent', () => {
  let component: MachineParameterComponent;
  let fixture: ComponentFixture<MachineParameterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MachineParameterComponent]
    });
    fixture = TestBed.createComponent(MachineParameterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
