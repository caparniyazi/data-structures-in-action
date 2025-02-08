import {IceCream} from "./IceCream";

export interface Sundae extends IceCream {
    sauce: "chocolate" | "caramel" | "strawberry";  // Literal type.
    nuts?: boolean; // optional prop.
    whippedCream?: boolean; // optional prop.
    instructions?: boolean; // optional prop.
}

let myIceCream: Sundae = {
    flavor: "vanilla",
    scoops: 2,
    sauce: "caramel",
    nuts: true
}