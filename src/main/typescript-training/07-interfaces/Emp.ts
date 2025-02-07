/**
 * A simple TypeScript interface that defines the two properties and a method of an Emp object.
 */
export interface Emp {
    firstName: string;
    lastName: string;

    fullName(): string;
}

let emp: Emp = {
    firstName: "Niyazi",
    lastName: "Çapar",
    fullName(): string {
        return this.firstName + " " + this.lastName;
    }
}

console.log(emp.fullName());