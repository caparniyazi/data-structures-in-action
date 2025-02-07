import {Employee} from "./Employee";
import {Manager} from "./Manager";

// Define a new intersection type that combines the properties in both interfaces.
type ManagementEmployee = Employee | Manager;
let newManager: ManagementEmployee = {
    employeeId: 44,
    age: 54,
    stockPlan: true
};

console.log(newManager);

// Literal type example
type testResult = "pass" | "fail" | "incomplete";
let myResult: testResult;

myResult = "incomplete"; // Valid
myResult = "pass";
//myResult = "failure";   // Invalid

// numeric literal type example
type dice = 1 | 2 | 3 | 4 | 5 | 6;
let diceRoll: dice;
diceRoll = 1;
