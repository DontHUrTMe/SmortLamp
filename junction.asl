// Agent junction in project SmortLamp.mas2j

/* Initial beliefs and rules */
all_proposals_received(CNPId) :-
.count(propose(Id,_,_), NO) & // number of proposes received
4 = NO.

/* Initial goals */
!startITSA(1).

/* plans */
+!startITSA(Id)      
<- new;
.wait(10);
+itsa_state(Id,propose);
.send([lamp0, lamp1, lamp2, lamp3],tell,itsa(Id)).

 +propose(Id, Offer,Name)
: itsa_state(Id,propose) & all_proposals_received(Id)
<- !contract(Id).

@lc1[atomic]
+!contract(Id)
: itsa_state(Id,propose)
<- -+itsa_state(Id,contract);
.findall(offer(O,A),propose(Id,O,_)[source(A)],L);
.print("Offers are ",L);
L \== []; // constraint the plan execution to at least one offer
.max(L,offer(WOf,WAg)); // sort offers, the first is the best
.print("Winner is ",WAg," with ",WOf);
!announce_result(Id,L,WAg);
!startITSA(Id+1);
-itsa_state(Id,contract).

// nothing todo, the current phase is not ’propose’
@lc2 +!contract(Id).

+!announce_result(_,[],_).
// announce to the winner
+!announce_result(Id,[offer(O,WAg)|T],WAg)
<- .send(WAg,tell,accept_proposal(Id));
!announce_result(Id,T,WAg).
// announce to others
+!announce_result(Id,[offer(O,LAg)|T],WAg)
<- .send(LAg,tell,reject_proposal(Id));
!announce_result(Id,T,WAg).
