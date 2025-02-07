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