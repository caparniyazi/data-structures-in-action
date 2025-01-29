class Client {
    // Constructor using parameter properties.
    constructor(private _firstName: string, private _lastName: string) {
    }

    // Getters & Setters
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
}

// Let's create an instance.
let myClient = new Client("Niyazi", "Çapar");
console.log(myClient);
myClient.firstName = "Hasan";
myClient.lastName = "Çapar";
console.log("The first name is: " + myClient.firstName);
console.log("The last name is: " + myClient.lastName);
