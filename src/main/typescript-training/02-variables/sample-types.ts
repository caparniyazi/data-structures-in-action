let found: boolean = true;
let grade: number = 88.6;
let firstName: string = "Niyazi";
let lastName: string = "Çapar";

let x: number; // Explicitly declares x as a number type
let y = 1;     // Implicitly declares y as a number type
let z;  // Declares z without initializing it.TypeScript infers the z is of type any because we did not assign
// a type or initialize it when it was declared.
// All types in TypeScript are subtypes of a single top type called the "any" type.

let randomValue: any = 10;
randomValue = "Niyazi";
randomValue = true;

// multiType is a union type.
let multiType: number | boolean;    // multiType can be a number or a boolean.
multiType = 20;
multiType = true;

console.log(found);
console.log("The grade is: " + grade);
console.log("Hi " + firstName + " " + lastName);

// Using template strings that are useful for long strings with a lot of concatenation.
console.log(`Hi ${firstName} ${lastName}`);

function add(x: number | string, y: number | string) {
    if (typeof x === 'number' && typeof y === 'number') {
        return x + y;
    }

    if (typeof x === 'string' && typeof y === 'string') {
        return x.concat(y);
    }

    throw new Error('Parameters must be numbers or strings');
}

console.log(add(34, 44));
console.log(add("one", "two"));