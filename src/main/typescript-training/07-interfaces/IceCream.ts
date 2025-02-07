export interface IceCream {
    flavor: string;
    scoops: number;
}

// Now, let us implement this interface.
let myIceCream: IceCream = {
    flavor: "vanilla",
    scoops: 2
}

// Let's create a function called tooManyScoops that uses the IceCream interface as a parameter type.
function tooManyScoops(dessert: IceCream) {
    if (dessert.scoops >= 4) {
        return dessert.scoops + " is too many scoops!";
    } else {
        return "Your order will be ready soon!";
    }
}

console.log(myIceCream.flavor);
// Pass an object as a parameter.
console.log(tooManyScoops({flavor: "vanilla", scoops: 5}));
