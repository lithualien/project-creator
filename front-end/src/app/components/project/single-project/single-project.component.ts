import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Group } from 'src/app/models/group.model';
import { Student } from 'src/app/models/student.model';
import { ProjectService } from 'src/app/services/project/project.service';
import { StudentService } from 'src/app/services/student/student.service';
import { ProjectGroup } from '../../../models/project-group.model';

@Component({
  selector: 'app-single-project',
  templateUrl: './single-project.component.html',
  styleUrls: ['./single-project.component.css']
})
export class SingleProjectComponent implements OnInit {

  private projectId!: number;
  private projectWithGroup!: ProjectGroup;
  private studentArray: Array<Student> = new Array();
  private loadingGroups: boolean = false;
  private loadingStudents: boolean = false;
  private uploadInProgress: boolean = false;
  private uploadFinished: boolean = false;
  private form!: FormGroup;
  public groupName: string = '';
  public student: string = '';
  public errMessage: string = '';

  constructor(
    private route: ActivatedRoute, 
    private service: ProjectService,
    private studentService: StudentService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.projectId = this.route.snapshot.params['id'];
    this.initializeGroups();
    this.initializestudents();
    this.setFormGroup();
  }

  private initializeGroups() {
    this.loadingGroups = true;
    this.service.getProjectWithGroup(this.projectId)
      .subscribe((result: ProjectGroup) => {
        this.projectWithGroup = result;
        this.loadingGroups = false;
    });
  }

  private initializestudents() {
    this.loadingStudents = true;
    this.studentService.getStudentArray().subscribe((result: Array<Student>) => {
        this.studentArray = result;
        this.loadingStudents = false;
    })
  }

  public getLoadingGroups(): boolean {
    return this.loadingGroups;
  }

  public getProjectWithGroup(): ProjectGroup {
    return this.projectWithGroup;
  }

  public getGroupArray(): Array<Group> {
    return this.projectWithGroup.groups;
  }

  public getStudentArray(): Array<Student> {
    return this.studentArray;
  }

  public getLoadingStudents(): boolean {
    return this.loadingStudents;
  }

  public getStudentsPerGroup(): number {
    return this.projectWithGroup.studentsPerGroup;
  }

  public getFormGroup(): FormGroup {
    return this.form;
  }

  public getUploadInProgress() {
    return this.uploadInProgress;
  }

  public getUploadFinished() {
    return this.uploadFinished;
  }

  public getErrorMessage() {
    return this.errMessage;
  }

  public setFormGroup() {
    this.form = new FormGroup({
      'groupName': new FormControl('', Validators.required),
      'student': new FormControl('', Validators.required)
    });
  }

  public submitForm(): void {
    let groupname: Group = this.form.value['groupName'];
    let student: Student = this.form.value['student'];

    this.uploadInProgress = true;
    this.errMessage = '';

    this.studentService.postStudentGroup(student.id, groupname.id).subscribe(() => {
      this.uploadInProgress = false;
      this.uploadFinished = true;
      let index: number = this.getGroupArray().indexOf(groupname);
      this.getGroupArray()[index].students.push(student);
      this.setFormGroup();
    }, (error: HttpErrorResponse) => {
      this.uploadFinished = false;
      this.uploadInProgress = false;
      this.setFormGroup();

        if(error.error.status === 400) {
            this.errMessage = error.error.message;
        }
    });

  }

  public loadGroupManagement(content: any) {
    this.modalService.open(content, { centered: true, size: 'lg', keyboard: false });
  }

}
