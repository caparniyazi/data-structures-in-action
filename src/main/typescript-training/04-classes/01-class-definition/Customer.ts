/**
 * We can give any name we want to the class file.
 * By convention, we give the class's name.
 */
class Customer {
    // Properties are public by default.
    private _firstName: string;
    private _lastName: string;

    // Constructors
    constructor(firstName: string, lastName: string) {
        this._firstName = firstName;
        this._lastName = lastName;
    }

    // Getters & Setters
    // If no access modifier given, "public" by default.
    get firstName(): string {
        return this._firstName;
    }

    set firstName(value: string) {
        this._firstName = value;
    }

    get lastName(): string {
        return this._lastName;
    }

    set lastName(value: string) {
        this._lastName = value;
    }

    // Methods
}

// Now, let's use it.
let myCustomer = new Customer("Niyazi", "Çapar");
console.log(myCustomer);
myCustomer.firstName = "Hasan";
myCustomer.lastName = "Çapar";
console.log("The first name is: " + myCustomer.firstName);
console.log("The last name is: " + myCustomer.lastName);