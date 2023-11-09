import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFormationsModalComponent } from './add-formations-modal.component';

describe('AddFormationsModalComponent', () => {
  let component: AddFormationsModalComponent;
  let fixture: ComponentFixture<AddFormationsModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddFormationsModalComponent]
    });
    fixture = TestBed.createComponent(AddFormationsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
