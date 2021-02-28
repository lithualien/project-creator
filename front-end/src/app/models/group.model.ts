import { Student } from "./student.model";

export interface Group {

    id: number;
    groupName: string;
    students: Array<Student>;

}