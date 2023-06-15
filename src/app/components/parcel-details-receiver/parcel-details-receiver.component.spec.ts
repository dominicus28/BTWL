import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelDetailsReceiverComponent } from './parcel-details-receiver.component';

describe('ParcelDetailsReceiverComponent', () => {
  let component: ParcelDetailsReceiverComponent;
  let fixture: ComponentFixture<ParcelDetailsReceiverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParcelDetailsReceiverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParcelDetailsReceiverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
