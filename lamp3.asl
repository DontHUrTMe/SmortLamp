// Agent sample_agent in project smortLamp
/* Initial beliefs and rules */
red.

/* Initial goals */

/* Plans */
+!red: car <- -red;
+green.

+!green:nocar <- -green;
+red.

@c1 +itsa(Id)[source(A)] : true
<- ?carNum(C); 
+proposal(Id, C); // remember my proposal
.my_name(ME);
.send(A,tell,propose(Id,C,ME)).

@r1 +accept_proposal(Id)[source(A)]
: proposal(Id,Offer)
<- .print("My proposal ’",Offer,"’ won ",Id,"!");
.my_name(ME);
.send(A,untell,propose(Id,C,ME));
-proposal(Id,_); // clear memory
-red;
green;
+green.

@r2 +reject_proposal(Id)[source(A)]
<- .print("I lost timeframe",Id, ".");
.my_name(ME);
.send(A,untell,propose(Id,C,ME));
-proposal(Id,_); // clear memory
-green;
red;
+red. 
