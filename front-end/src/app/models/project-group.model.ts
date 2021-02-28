import { Group } from "./group.model";
import { Project } from "./project.model";

export interface ProjectGroup extends Project {

    groups: Array<Group>;

}