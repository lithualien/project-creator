import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectModalCreateComponent } from './project-modal-create.component';

describe('ProjectModalCreateComponent', () => {
  let component: ProjectModalCreateComponent;
  let fixture: ComponentFixture<ProjectModalCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectModalCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectModalCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
