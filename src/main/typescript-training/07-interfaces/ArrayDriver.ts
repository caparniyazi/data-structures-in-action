import {Coach} from "./Coach";
import {CricketCoach} from "./CricketCoach";
import {GolfCoach} from "./GolfCoach";

let myCricketCoach = new CricketCoach();
let myGolfCoach = new GolfCoach();

let theCoaches: Coach   [] = [];
theCoaches.push(myCricketCoach);
theCoaches.push(myGolfCoach);

for (const theCoach of theCoaches) {
    console.log(theCoach.getDailyWorkout());
}