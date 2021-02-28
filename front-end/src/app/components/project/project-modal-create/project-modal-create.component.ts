import { Component, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EventEmitter } from '@angular/core';
import { Project } from 'src/app/models/project.model';

@Component({
  selector: 'app-project-modal-create',
  templateUrl: './project-modal-create.component.html',
  styleUrls: ['./project-modal-create.component.css']
})
export class ProjectModalCreateComponent implements OnInit {

  @Input('creatingInProgress') 
  public creatingInProgress!: boolean;

  @Input('created') 
  public created!: boolean;

  @Input('errorMessage') 
  public errorMessage: string = '';

  @Input('project') 
  public project!: Project;

  @Input('newProject') 
  public newProject!: Project;

  @Input("save") 
  public save!: boolean;
  
  @Output('submittedProject') 
  public submittedProject: EventEmitter<Project> = new EventEmitter();

  private form!: FormGroup;
  

  constructor(
    public activeModal: NgbActiveModal,
  ) { }

  ngOnInit(): void {
    this.setForm();
  }

  public setForm(): void {
    this.form = new FormGroup({
        'id': new FormControl(this.project.id),
        'projectName': new FormControl(this.project.projectName, [ Validators.required, Validators.maxLength(26), Validators.minLength(4) ]),
        'groupAmount': new FormControl(this.project.groupAmount, [ Validators.required, Validators.min(1), Validators.max(20) ]),
        'studentsPerGroup': new FormControl(this.project.studentsPerGroup, [Validators.required, Validators.min(2), Validators.max(10) ])
    });
  }

  public getForm(): FormGroup {
    return this.form;
  }

  public submitForm() {
    let project: Project = this.getForm().value;
    this.submittedProject.emit(project);
  }

  public close() {
    this.activeModal.close();
  }

  public getProjectNameMessage(): string {
    let control: any = this.getForm().get('projectName');

    if(!control?.touched) {
      return '';
    }

    if(control?.hasError('required')) {
      return 'Project name field is required!';
    }

    if(control?.hasError('minlength')) {
      return 'Project name is a bit too short, it should cointain at least 4 symbols!'
    }

    if(control?.hasError('maxlength')) {
      return 'Project name is a bit too long, it should contain no more than 26 symbols!'
    }

    return '';
  }

  public getMessageForGroupAmount(): string {
    let control: any = this.getForm().get('groupAmount');

    if(!control?.touched) {
      return '';
    }

    if(control?.hasError('required')) {
      return "Group amount field is required!"
    }

    if(control?.hasError('max')) {
      return "Group amount can not be higher than 20!";
    }

    if(control?.hasError('min')) {
      return 'Group amount can not be lower than 1!';
    }

    return '';

  }

  public getMessageForStudentAmount(): string {
    let control: any = this.getForm().get('studentsPerGroup');

    if(!control?.touched) {
      return '';
    }

    if(control?.hasError('required')) {
      return "Student per group field is required!"
    }

    if(control?.hasError('max')) {
      return "Student per group amount can not be higher than 10!";
    }

    if(control?.hasError('min')) {
      return 'Student per group amount can not be lower than 2!';
    }

    return '';

  }

}
