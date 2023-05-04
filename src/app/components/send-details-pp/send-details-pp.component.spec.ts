import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendDetailsPPComponent } from './send-details-pp.component';

describe('SendDetailsPPComponent', () => {
  let component: SendDetailsPPComponent;
  let fixture: ComponentFixture<SendDetailsPPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendDetailsPPComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendDetailsPPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
