import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliverBinComponent } from './deliver-bin.component';

describe('DeliverBinComponent', () => {
  let component: DeliverBinComponent;
  let fixture: ComponentFixture<DeliverBinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeliverBinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeliverBinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
