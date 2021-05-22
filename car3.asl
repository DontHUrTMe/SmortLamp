// Agent sample_agent in project smortLamp

/* Initial beliefs and rules */
/* Initial goals */

!go.

/* Plans */

+!go : lamp0 & not red & not green<- .send(lamp0,askOne,red);.send(lamp0,askOne,green);!go.
+!go : lamp1 & not red & not green<- .send(lamp1,askOne,red);.send(lamp1,askOne,green);!go.
+!go : lamp2 & not red & not green<- .send(lamp2,askOne,red);.send(lamp2,askOne,green);!go.
+!go : lamp3 & not red & not green<- .send(lamp3,askOne,red);.send(lamp3,askOne,green);!go.

+!go : red <- !wait.

+!go : true <-
  	move;
    !go.

+!wait: red <- !wait.

+!wait: green <- !go.
