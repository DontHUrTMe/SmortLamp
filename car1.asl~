// Agent sample_agent in project smortLamp

/* Initial beliefs and rules */
cars_in_line(0).
car_in_front(car2).
car_behind(car3).

/* Initial goals */

!go.

/* Plans */

+!go : ~green & ~red <- .send(lamp0, askIf, red).

+!go : red <- !wait.

+!go : true <-
    move(car1);
    !go.

+!wait:red <- !wait.

+!wait:~red <- !go.

+!in_line(Size)[source(Ag)]:true <-
