import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BinsMapComponent } from './bins-map.component';

describe('BinsMapComponent', () => {
  let component: BinsMapComponent;
  let fixture: ComponentFixture<BinsMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BinsMapComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BinsMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
