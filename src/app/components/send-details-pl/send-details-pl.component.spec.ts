import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendDetailsPLComponent } from './send-details-pl.component';

describe('SendDetailsPLComponent', () => {
  let component: SendDetailsPLComponent;
  let fixture: ComponentFixture<SendDetailsPLComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendDetailsPLComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendDetailsPLComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
