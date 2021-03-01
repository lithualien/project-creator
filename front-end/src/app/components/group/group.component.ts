import { Component, Input, OnInit } from '@angular/core';
import { Group } from 'src/app/models/group.model';
import { Student } from 'src/app/models/student.model';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  @Input('group')
  public group!: Group;

  @Input('studentPerGroup')
  public studentPerGroup!: number;

  constructor() {  }

  ngOnInit(): void {
  }

  public getStudentArray(): Array<Student> {
    return this.group.students;
  }

  public isStudentListEmpty(): boolean {
    return this.getStudentArray().length < 1;
  }

  public isStudentListFull(): boolean {
    return this.getStudentArray().length === this.studentPerGroup;
  }

  public onDelete(student: Student) {
    console.log(student);
  }

}
