(fn [cs] 
  (let [fcs (fn [[s r]] 
              {:suit ({\S :spade \H :heart \D :diamond \C :club} s)
               :rank ((into {} (map vector "23456789TJQKA" (range))) r)})
        ms (map fcs cs)
        rks (map :rank ms)
        fs (vals (frequencies rks))
        [pair two-pair] (filter #(= 2 %) fs)
        straight ((into #{[0 1 2 3 12]} (partition 5 1 (range 13)))
                    (sort rks))
        flush_ (not (second (set (map :suit ms))))]
    (cond (and straight flush_) :straight-flush
          ((set fs) 4) :four-of-a-kind
          (= 2 (count fs)) :full-house
          flush_ :flush
          straight :straight
          ((set fs) 3) :three-of-a-kind
          two-pair :two-pair
          pair :pair
          :else :high-card)))
