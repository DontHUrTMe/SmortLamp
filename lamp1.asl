// Agent sample_agent in project smortLamp
/* Initial beliefs and rules */
red.
carNum(2).

/* Initial goals */

/* Plans */
+!red: car <- -red;
+green.

+!green:nocar <- -green;
+red.

@c1 +itsa(Id)[source(A)] : true
<- ?carNum(C); 
+proposal(Id, C); // remember my proposal
.send(A,tell,propose(Id,C)).

@r1 +accept_proposal(Id)
: proposal(Id,Offer)
<- .print("My proposal ’",Offer,"’ won ",Id,"!");
+green;
-red.

@r2 +reject_proposal(Id)
<- .print("I lost timeframe",Id, ".");
-proposal(Id,_); // clear memory
-green;
+red. 
