// Agent sample_agent in project smortLamp
/* Initial beliefs and rules */
red.
carNum(0).

/* Initial goals */

/* Plans */
+!red: car <- -red;
+green.

+!green:nocar <- -green;
+red.

@c1 +itsa(Id)[source(A)] : true
<- +proposal(Id, carNum); // remember my proposal
.send(A,tell,propose(Id,carNum)).
