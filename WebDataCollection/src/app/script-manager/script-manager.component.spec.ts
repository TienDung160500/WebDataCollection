import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScriptManagerComponent } from './script-manager.component';

describe('ScriptManagerComponent', () => {
  let component: ScriptManagerComponent;
  let fixture: ComponentFixture<ScriptManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ScriptManagerComponent]
    });
    fixture = TestBed.createComponent(ScriptManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
