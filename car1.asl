// Agent sample_agent in project smortLamp

/* Initial beliefs and rules */
/* Initial goals */

!go.

/* Plans */

+!go : lamp0 & not red & not green<- .send(lamp0,askOne,red);.send(lamp0,askOne,green);!wait.
+!go : lamp1 & not red & not green<- .send(lamp1,askOne,red);.send(lamp1,askOne,green);!wait.
+!go : lamp2 & not red & not green<- .send(lamp2,askOne,red);.send(lamp2,askOne,green);!wait.
+!go : lamp3 & not red & not green<- .send(lamp3,askOne,red);.send(lamp3,askOne,green);!wait.

+!wait: green <- !go.
+!wait : lamp0  <- .send(lamp0,askOne,red);.send(lamp0,askOne,green);!wait.
+!wait : lamp1  <- .send(lamp1,askOne,red);.send(lamp1,askOne,green);!wait.
+!wait : lamp2  <- .send(lamp2,askOne,red);.send(lamp2,askOne,green);!wait.
+!wait : lamp3  <- .send(lamp3,askOne,red);.send(lamp3,askOne,green);!wait.

+!go : red <- !wait.

+!go : lamp0 & red <- !wait.
+!go : lamp1 & red <- !wait.
+!go : lamp2 & red <- !wait.
+!go : lamp3 & red <- !wait.

+!go : lamp0 & green <- move; -green; !go.
+!go : lamp1 & green <- move; -green; !go.
+!go : lamp2 & green <- move; -green; !go.
+!go : lamp3 & green <- move; -green; !go.


+!go : true <-
  	move;
    !go.

+!wait: red <- !wait.
