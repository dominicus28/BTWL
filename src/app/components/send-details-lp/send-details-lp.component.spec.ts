import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendDetailsLPComponent } from './send-details-lp.component';

describe('SendDetailsLPComponent', () => {
  let component: SendDetailsLPComponent;
  let fixture: ComponentFixture<SendDetailsLPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendDetailsLPComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendDetailsLPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
