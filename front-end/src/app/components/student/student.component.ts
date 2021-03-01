import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student/student.service';
import { Student } from 'src/app/models/student.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  private studentArray!: Array<Student>;
  private index!: number;
  private loading: boolean = false;
  private deleteInProgress: boolean = false;
  private deleteFinished: boolean = false;
  private uploadInProgress: boolean = false;
  private uploadFinished: boolean = false;
  private form!: FormGroup;
  private errorMessage: string = '';

  constructor(
    private studentService: StudentService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.loading = true;
    this.initStudentArray();
    this.setFormGroup();
  }

  public initStudentArray(): void {
    this.studentService.getStudentArray().subscribe((student) => {
      this.loading = false;
      this.studentArray = student;
    }, (error: Error) => {
      this.loading = false;
    })
  }

  public getLoading(): boolean {
    return this.loading;
  }

  public getStudentArray(): Array<Student> {
    return this.studentArray;
  }

  public getDeleteInProgress(): boolean {
    return this.deleteInProgress;
  }

  public getDeleteFinished(): boolean {
    return this.deleteFinished;
  }

  public getUploadInProgress(): boolean {
    return this.uploadInProgress;
  }

  public getUploadFinished(): boolean {
    return this.uploadFinished;
  }

  public getFormGroup(): FormGroup {
    return this.form;
  }

  public getErrorMessage(): string {
    return this.errorMessage;
  }

  public setFormGroup(): void {
    this.form = new FormGroup({
      'id': new FormControl(0),
      'firstName': new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(250)]),
      'lastName': new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(250)])

    });
  }

  public isEmpty(): boolean {
    return this.studentArray.length < 1
  }

  public deleteElement(): void {
    let id: number = this.studentArray[this.index].id;
    this.deleteInProgress = true;
    this.studentService.deleteStudent(id).subscribe(() => {
      this.deleteInProgress = false;
      this.deleteFinished = true;
      this.studentArray.splice(this.index, 1);
    }, (error)=> {
      this.deleteInProgress = false;
      this.deleteFinished = false;
    })
  }

  public confirm(content: any, index: number) {
    this.modalService.open(content, { centered: true, size: 'lg', keyboard: false });
    this.index = index;
    this.deleteFinished = false;
    this.deleteInProgress = false;
  }

  public submitForm() {
    let student: Student = this.form.value;
    this.errorMessage = '';
    this.uploadInProgress = true;
    this.studentService.postStudent(student).subscribe((result) => {
      this.uploadInProgress = false;
      this.uploadFinished = true;
      this.setFormGroup();
      this.studentArray.push(result);
    }, (error: HttpErrorResponse) => {
      this.uploadFinished = false;
      this.uploadInProgress = false;
      if(error.error.status === 400) {
        this.errorMessage = error.error.message;
      }
    });
  }

  public getFormErrorMessage(beggining: string, field: string): string {
    let control: any = this.form.get(field);

    if(!control?.touched) {
      return '';
    }

    if(control?.hasError('required')) {
      return beggining + " field is required!"
    }

    if(control?.hasError('minlength')) {
      return beggining + " is a bit too short, it should contain at least 4 symbols!";
    }

    if(control?.hasError('maxlength')) {
      return beggining + ' is a bit too long, it should contain no more than 250 symbols!';
    }

    return '';

  }

  public newStudent(content: any) {
    this.modalService.open(content, { centered: true, size: 'lg', keyboard: false });
    this.errorMessage = '';
    this.uploadFinished = false;
    this.uploadInProgress = false;
  }

}
