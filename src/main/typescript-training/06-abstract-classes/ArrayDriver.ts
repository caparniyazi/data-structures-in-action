import {Circle} from "./Circle";
import {Rectangle} from "./Rectangle";
import {Shape} from "./Shape";

let myCircle = new Circle(5, 10, 20);
let myRectangle = new Rectangle(0, 0, 3, 7);

let theShapes: Shape[] = [];
theShapes.push(myCircle);
theShapes.push(myRectangle);

for (const theShape of theShapes) {
    console.log(theShape.getInfo());
    console.log("Area= " + theShape.calculateArea());
    console.log();
}