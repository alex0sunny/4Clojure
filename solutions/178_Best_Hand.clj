;; Best Hand
;; Difficulty:	Hard
;; Topics:	strings game


;; Following on from Recognize Playing Cards, determine the best poker hand
;; that can be made with five cards. The hand rankings are listed below for your convenience.

;;     Straight flush: All cards in the same suit, and in sequence
;;     Four of a kind: Four of the cards have the same rank
;;     Full House: Three cards of one rank, the other two of another rank
;;     Flush: All cards in the same suit
;;     Straight: All cards in sequence (aces can be high or low, but not both at once)
;;     Three of a kind: Three of the cards have the same rank
;;     Two pair: Two pairs of cards have the same rank
;;     Pair: Two cards have the same rank
;;     High card: None of the above conditions are met

#(let [rks (map second %)
       fs (sort (vals (frequencies rks)))
       s ((set (map set (partition 5 1 "A23456789TJQKA"))) 
          (set rks))
       f (= 1 (count (set (map first %))))
       res (condp = fs [1 4] :four-of-a-kind
                       [2 3] :full-house
                       [1 1 3] :three-of-a-kind
                       [1 2 2] :two-pair
                       [1 1 1 2] :pair
                       :high-card)]
   (cond (and s f) :straight-flush
         (= 2 (count fs)) res
         s :straight
         f :flush
         :else res))
