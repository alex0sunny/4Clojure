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
