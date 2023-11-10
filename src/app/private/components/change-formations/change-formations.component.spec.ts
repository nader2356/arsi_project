import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeFormationsComponent } from './change-formations.component';

describe('ChangeFormationsComponent', () => {
  let component: ChangeFormationsComponent;
  let fixture: ComponentFixture<ChangeFormationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChangeFormationsComponent]
    });
    fixture = TestBed.createComponent(ChangeFormationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
