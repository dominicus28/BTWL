import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelDetailsCourierComponent } from './parcel-details-courier.component';

describe('ParcelDetailsCourierComponent', () => {
  let component: ParcelDetailsCourierComponent;
  let fixture: ComponentFixture<ParcelDetailsCourierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParcelDetailsCourierComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParcelDetailsCourierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
