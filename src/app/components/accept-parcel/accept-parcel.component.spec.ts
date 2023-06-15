import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptParcelComponent } from './accept-parcel.component';

describe('AcceptParcelComponent', () => {
  let component: AcceptParcelComponent;
  let fixture: ComponentFixture<AcceptParcelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptParcelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcceptParcelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
