import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPartenaireComponent } from './edit-partenaire.component';

describe('EditPartenaireComponent', () => {
  let component: EditPartenaireComponent;
  let fixture: ComponentFixture<EditPartenaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditPartenaireComponent]
    });
    fixture = TestBed.createComponent(EditPartenaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
