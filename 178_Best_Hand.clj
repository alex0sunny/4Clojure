(fn [cs] 
  (let [rks (map #((into {} (map vector "23456789TJQKA" (range))) (second %)) cs)
        fs (sort > (vals (frequencies rks)))
        straight ((set (map set (partition 5 1 (cons 12 (range 13))))) (set rks))
        flush_ (not (second (set (map first cs))))]
    (cond (and straight flush_) :straight-flush
          (= fs [4 1]) :four-of-a-kind
          (= fs [3 2]) :full-house
          flush_ :flush
          straight :straight
          (= fs [3 1 1]) :three-of-a-kind
          (= fs [2 2 1]) :two-pair
          (= fs [2 1 1 1]) :pair
          :else :high-card)))
