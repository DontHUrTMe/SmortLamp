// Agent sample_agent in project smortLamp
/* Initial beliefs and rules */
red.
nocar.

/* Initial goals */

/* Plans */
+!red: car <- -red;
+green.

+!green:nocar <- -green;
+red.
