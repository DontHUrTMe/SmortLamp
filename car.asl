// Agent sample_agent in project smortLamp

/* Initial beliefs and rules */

~red.

/* Initial goals */

!go.

/* Plans */

+!go : true <- .look(car,lamp0).

+!go : ~red <- !go.

+!go : red <- !wait.

+!wait:red <- !wait.

+!wait:~red <- !go.
